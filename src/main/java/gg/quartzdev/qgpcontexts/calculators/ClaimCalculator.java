package gg.quartzdev.qgpcontexts.calculators;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.ClaimPermission;
import me.ryanhamshire.GriefPrevention.DataStore;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.PlayerData;
import net.luckperms.api.context.ContextCalculator;
import net.luckperms.api.context.ContextConsumer;
import net.luckperms.api.context.ContextSet;
import net.luckperms.api.context.ImmutableContextSet;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class ClaimCalculator implements ContextCalculator<Player>
{
    private static final String KEY = "griefprevention:claim-id";
    private static final String IN_CLAIM_KEY = "griefprevention:in-claim";
    private static final String IS_OWNER_KEY = "griefprevention:is-owner";
    private static final String TRUST_ACCESS_KEY = "griefprevention:trust-access";
    private static final String TRUST_BUILD_KEY = "griefprevention:trust-build";
    private static final String TRUST_EDIT_KEY = "griefprevention:trust-edit";
    private static final String TRUST_INVENTORY_KEY = "griefprevention:trust-inventory";
    private static final String TRUST_MANAGE_KEY = "griefprevention:trust-manage";

    private final DataStore dataStore = GriefPrevention.instance.dataStore;

    @Override
    public void calculate(@NonNull Player player, @NonNull ContextConsumer contextConsumer)
    {
        PlayerData playerData = dataStore.getPlayerData(player.getUniqueId());
        Claim currentClaim = dataStore.getClaimAt(player.getLocation(), true, playerData.lastClaim);

//        If they're in a claim
        if(currentClaim == null)
        {
            contextConsumer.accept(IN_CLAIM_KEY, "false");
            return;
        }
        contextConsumer.accept(IN_CLAIM_KEY, "true");
        currentClaim.permi

//        claim id of the claim
        contextConsumer.accept(KEY, "" + currentClaim.getID());

//        if the player is the owner of the claim
        contextConsumer.accept(IS_OWNER_KEY, "" + currentClaim.getOwnerID().equals(player.getUniqueId()));

//        if the player has a trust level (owners default to true for all)
        contextConsumer.accept(TRUST_ACCESS_KEY, "" + currentClaim.hasExplicitPermission(player.getUniqueId(), ClaimPermission.Access));
        contextConsumer.accept(TRUST_BUILD_KEY, "" + currentClaim.hasExplicitPermission(player.getUniqueId(), ClaimPermission.Build));
        contextConsumer.accept(TRUST_EDIT_KEY, "" + currentClaim.hasExplicitPermission(player.getUniqueId(), ClaimPermission.Edit));
        contextConsumer.accept(TRUST_INVENTORY_KEY, "" + currentClaim.hasExplicitPermission(player.getUniqueId(), ClaimPermission.Inventory));
        contextConsumer.accept(TRUST_MANAGE_KEY, "" + currentClaim.hasExplicitPermission(player.getUniqueId(), ClaimPermission.Manage));
    }

    @Override
    public @NonNull ContextSet estimatePotentialContexts()
    {
        ImmutableContextSet.Builder builder = ImmutableContextSet.builder();

        builder.add(IN_CLAIM_KEY, "true");
        builder.add(IN_CLAIM_KEY, "false");

        builder.add(TRUST_ACCESS_KEY, "true");
        builder.add(TRUST_ACCESS_KEY, "false");
        builder.add(TRUST_BUILD_KEY, "true");
        builder.add(TRUST_BUILD_KEY, "false");
        builder.add(TRUST_EDIT_KEY, "true");
        builder.add(TRUST_EDIT_KEY, "false");
        builder.add(TRUST_INVENTORY_KEY, "true");
        builder.add(TRUST_INVENTORY_KEY, "false");
        builder.add(TRUST_MANAGE_KEY, "true");
        builder.add(TRUST_MANAGE_KEY, "false");

        for(Claim claim : dataStore.getClaims())
        {
            builder.add(KEY, "" + claim.getID());
        }
        return builder.build();
    }
}
