package gg.quartzdev.qgpcontexts;

import gg.quartzdev.qgpcontexts.calculators.ClaimCalculator;
import net.kyori.adventure.text.Component;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.context.ContextCalculator;
import net.luckperms.api.context.ContextManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class QGPContexts extends JavaPlugin {

    private ContextManager contextManager;
    private final List<ContextCalculator<Player>> registeredCalculators = new ArrayList<>();

    @Override
    public void onEnable() {
        LuckPerms luckPerms = getServer().getServicesManager().load(LuckPerms.class);
        if (luckPerms == null)
        {
            throw new IllegalStateException("LuckPerms API not loaded");
        }
        contextManager = luckPerms.getContextManager();
        setup();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        unregisterAll();
    }

    public void setup() {
        this.register(ClaimCalculator::new);
    }

    private void register(Supplier<ContextCalculator<Player>> calculatorSupplier)
    {
        getComponentLogger().info(Component.text("Registering GriefPrevention contexts"));
        ContextCalculator<Player> calculator = calculatorSupplier.get();
        this.contextManager.registerCalculator(calculator);
        this.registeredCalculators.add(calculator);
    }

    private void unregisterAll()
    {
        this.registeredCalculators.forEach(contextManager::unregisterCalculator);
        this.registeredCalculators.clear();
    }
}