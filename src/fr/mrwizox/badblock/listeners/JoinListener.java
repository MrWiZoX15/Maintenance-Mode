package fr.mrwizox.badblock.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import fr.mrwizox.badblock.config.Config;

public class JoinListener implements Listener {

        @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
        public void onLogin(PlayerLoginEvent event) {
                if (!Config.MAINTENANCE.getBoolean()) {
                        return;
                }

                if (!event.getPlayer().hasPermission("maintenanceserver.bypass")) {
                        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, Config.KICKMESSAGE.getString());
                }
        }

        @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
        public void onJoin(PlayerJoinEvent event) {
                if (!Config.MAINTENANCE.getBoolean()) {
                        return;
                }

                if (!event.getPlayer().hasPermission("maintenanceserver.bypass")) {
                        event.getPlayer().kickPlayer(Config.KICKMESSAGE.getString());
                }

        }

}
