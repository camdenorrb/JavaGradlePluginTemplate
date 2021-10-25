package dev.twelveoclock.plugintemplate.module.impl;

import dev.twelveoclock.plugintemplate.commands.MeowCommand;
import dev.twelveoclock.plugintemplate.config.PluginConfig;
import dev.twelveoclock.plugintemplate.module.PluginModule;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

/**
 * A module that represents the cat portion of the plugin
 */
public final class CatModule extends PluginModule {

    private final MeowCommand meowCommand;

    private final PluginConfig config;


    public CatModule(final JavaPlugin plugin, final PluginConfig config) {
        super(plugin);
        this.meowCommand = new MeowCommand(config.catName());
        this.config = config;
    }


    @Override
    protected void onEnable() {
        plugin.getCommand("meow").setExecutor(meowCommand);
    }

    @Override
    protected void onDisable() {
        plugin.getCommand("meow").setExecutor(null);
    }


    @EventHandler
    private void onSpawn(final EntitySpawnEvent event) {

        System.out.println(event.getEntity().getType());
        System.out.println(event.getEntity().getCustomName());

        // Delay the task by 1 tick in order to wait for the name change
        plugin.getServer().getScheduler().runTask(plugin, () -> {

            if (
                !(event.getEntity() instanceof final Cat cat) ||
                cat.getCustomName() == null ||
                !cat.getCustomName().equals(config.catName())
            ) {
                return;
            }

            System.out.println("Called");

            cat.launchProjectile(Firework.class, new Vector(0, 5, 0));
        });

    }

}