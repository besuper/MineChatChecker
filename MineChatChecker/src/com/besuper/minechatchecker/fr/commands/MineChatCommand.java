package com.besuper.minechatchecker.fr.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.besuper.minechatchecker.fr.MineChatChecker;
import com.besuper.minechatchecker.fr.players.MPlayer;
import com.besuper.minechatchecker.fr.utilitaire.Configuration;

public class MineChatCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (args.length == 0 || args.length == 1) {
			if(sender.hasPermission("minechat.command.help")||sender.hasPermission("minechat.command.*")){
				sender.sendMessage("§a============[MineChatChecker]============");
				sender.sendMessage("§6/minechat info <player> §f§l- §f§aView info of player");
				sender.sendMessage("§6/minechat check <player> §f§l- §f§aLaunch a check for the player");
				sender.sendMessage("§a=========================================");
			}
			
		}
		if (args.length == 2) {
			if (args[0].equals("info")) {
				
				if(!sender.hasPermission("minechat.command.info") ||!sender.hasPermission("minechat.command.*"))return true;

				if (args[1] == null)return true;
				if (Bukkit.getPlayer(args[1])==null) {
					sender.sendMessage("§cThe player is not online!");
					return true;
				}

				MPlayer p = MineChatChecker.get(Bukkit.getPlayer(args[1]));

				sender.sendMessage("§a§l> " + p.getPlayer().getName());
				if (p.getDetected())sender.sendMessage("§6Use MineChat§f§l: §cYes");
				if (!p.getDetected())sender.sendMessage("§6Use MineChat§f§l: §aNo");
				sender.sendMessage("§6Application used§f§l: §a"+p.getApp().name());
				sender.sendMessage("§6Phone used§f§l: §a"+p.getPhone().name());

			} else if (args[0].equals("check")) {

				if(!sender.hasPermission("minechat.command.check") ||!sender.hasPermission("minechat.command.*"))return true;
				
				if (args[1] == null)return true;
				if (Bukkit.getPlayer(args[1])==null) {
					sender.sendMessage("§cThe player is not online!");
					return true;
				}

				sender.sendMessage("§6Sending check for MineChat to "+args[1]+"...");
				
				MPlayer p = MineChatChecker.get(Bukkit.getPlayer(args[1]));

				Location l = p.getLocation();
				p.setVelocity(p.getLocation().getDirection().multiply(0.01));

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MineChatChecker.getPlugin(MineChatChecker.class), new Runnable() {
				public void run() {
					Location s = p.getLocation();
						if (s.equals(l)) {
							if (!p.hasPermission(Configuration.permissionBypass)) {
								p.setDetected(true);
								if (Configuration.alert) {
									sender.sendMessage("§c" + p.getPlayer().getName() + " is detected for MineChat!");
								}
							}
						}
					}
				}, (1 * 20));
				
				if(!p.getDetected())sender.sendMessage("§a" + p.getPlayer().getName() + " don't use MineChat!");
				

			} else {
				if(sender.hasPermission("minechat.command.help")||sender.hasPermission("minechat.command.*")){
					sender.sendMessage("§a============[MineChatChecker]============");
					sender.sendMessage("§6/minechat info <player> §f§l- §f§aView info of player");
					sender.sendMessage("§6/minechat check <player> §f§l- §f§aLaunch a check for the player");
					sender.sendMessage("§a=========================================");
				}
				
			}
		}
		return false;
	}

}
