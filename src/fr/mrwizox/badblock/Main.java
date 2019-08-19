// Créated by MrWiZoX
package fr.mrwizox.badblock;

import org.bukkit.plugin.java.JavaPlugin;

import fr.mrwizox.badblock.config.Config;
import fr.mrwizox.badblock.listeners.JoinListener;

public class Main extends JavaPlugin {

        public static Main INSTANCE;

         @Override
        public void onEnable() {
                INSTANCE = this;
                Config.load();
                getServer().getPluginManager().registerEvents(new JoinListener(), this);
                getCommand("maintenanceserver").setExecutor(new MaintenanceCmd());
        }

        @Override
        public void onDisable() {
                Config.save();
        }
}
