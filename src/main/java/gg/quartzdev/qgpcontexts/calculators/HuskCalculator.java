package gg.quartzdev.qgpcontexts.calculators;

import net.luckperms.api.context.ContextCalculator;
import net.luckperms.api.context.ContextConsumer;
import net.luckperms.api.context.ContextSet;
import net.luckperms.api.context.ImmutableContextSet;
import net.william278.huskclaims.api.HuskClaimsAPI;
import net.william278.huskclaims.trust.TrustLevel;
import net.william278.huskclaims.user.OnlineUser;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Optional;

public class HuskCalculator implements ContextCalculator<Player>
{
    private static final String PLUGIN_PREFIX = "huskclaims:";

    private static final String IN_CLAIM_KEY = PLUGIN_PREFIX + "in-claim";
    private static final String IN_ADMIN_CLAIM_KEY = PLUGIN_PREFIX + "in-admin-claim";
    private static final String IS_OWNER_KEY = PLUGIN_PREFIX + "is-owner";
    private static final String TRUST_LEVEL_PREFIX = PLUGIN_PREFIX + "trust-";

    private final HuskClaimsAPI claimsAPI = HuskClaimsAPI.getInstance();

    @Override
    public void calculate(@NonNull Player player, @NonNull ContextConsumer contextConsumer)
    {
        OnlineUser onlineUser = claimsAPI.getOnlineUser(player.getUniqueId());
        Optional<net.william278.huskclaims.claim.Claim> currentClaim = claimsAPI.getClaimAt(onlineUser);

//        If they're not in a claim
        if(!currentClaim.isPresent())
        {
            contextConsumer.accept(IN_CLAIM_KEY, "false");
            return;
        }
        contextConsumer.accept(IN_CLAIM_KEY, "true");

        net.william278.huskclaims.claim.Claim claim = currentClaim.get();

        boolean isOwner = false;

//        if in admin claim
        if(claim.isAdminClaim())
        {
            contextConsumer.accept(IN_ADMIN_CLAIM_KEY, "true");

//            admin claims don't have owners
            contextConsumer.accept(IS_OWNER_KEY, "false");
        } else
        {
            contextConsumer.accept(IN_ADMIN_CLAIM_KEY, "false");
//            if owner of the claim
            if(claim.getOwner().isPresent())
            {
                isOwner = claim.getOwner().get().equals(player.getUniqueId());
                contextConsumer.accept(IS_OWNER_KEY, "" + isOwner);
            }
        }

//        sets false for all trust levels
        for(TrustLevel trustLevel : claimsAPI.getTrustLevels())
        {
            contextConsumer.accept(TRUST_LEVEL_PREFIX + trustLevel.getDisplayName().toLowerCase(), "" + isOwner);
        }

//        sets true for the trust level the player has
        claimsAPI.getTrustLevelAt(onlineUser)
                .ifPresent(
                        trustLevel -> contextConsumer.accept(TRUST_LEVEL_PREFIX + trustLevel.getDisplayName().toLowerCase(), "true"));

    }


//    for tab-completion in commands/editor
    @Override
    public @NonNull ContextSet estimatePotentialContexts()
    {
        ImmutableContextSet.Builder builder = ImmutableContextSet.builder();

        builder.add(IN_CLAIM_KEY, "true");
        builder.add(IN_CLAIM_KEY, "false");

        builder.add(IN_ADMIN_CLAIM_KEY, "true");
        builder.add(IN_ADMIN_CLAIM_KEY, "false");

        builder.add(IS_OWNER_KEY, "true");
        builder.add(IS_OWNER_KEY, "false");

        for(TrustLevel trustLevel : claimsAPI.getTrustLevels())
        {
            builder.add(TRUST_LEVEL_PREFIX + trustLevel.getDisplayName().toLowerCase(), "true");
            builder.add(TRUST_LEVEL_PREFIX + trustLevel.getDisplayName().toLowerCase(), "false");
        }

        return builder.build();
    }
}
