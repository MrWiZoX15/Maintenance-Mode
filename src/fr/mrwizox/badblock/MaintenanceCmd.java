package fr.mrwizox.badblock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.mrwizox.badblock.config.Config;

public class MaintenanceCmd implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String string, String[] strings) {
                if (!sender.hasPermission("maintenanceserver.toggle")) {
                        sender.sendMessage("§cVous n'avez pas la permission.");
                        return true;
                }

                if (strings.length == 0) return false;
                if (strings.length > 1) return false;
                if (strings[0].equalsIgnoreCase("on")) {
                        Config.MAINTENANCE.set(true);
                        sender.sendMessage(ChatColor.GREEN + "La maintenance est maintenant activé.");
                        if (Config.KICK.getBoolean()) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                        if (p.hasPermission("maintenanceserver.bypass")) {
                                                continue;
                                        }   
                                        Bukkit.broadcastMessage("§cLa maintenance s'active dans 5 Seconds");
                                        Main.INSTANCE.getServer().getScheduler().scheduleSyncDelayedTask(Main.INSTANCE, new Runnable() {
											
											@Override
											public void run() {
												p.kickPlayer(Config.KICKMESSAGE.getString());	
											}
										},20*5);
                                }
                        }
                        return true;
                } else if (strings[0].equalsIgnoreCase("off")) {
                        Config.MAINTENANCE.set(false);
                        sender.sendMessage(ChatColor.GREEN + "La maintenance est maintenant désactivé.");
                        return true;
                } else {
                        sender.sendMessage("§cMauvais usage ! /maintenanceserver <on> ou <off>");
                        return true;
                }
        }

}
