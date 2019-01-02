package com.besuper.minechatchecker.fr.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.besuper.minechatchecker.fr.MineChatChecker;
import com.besuper.minechatchecker.fr.players.MPlayer;
import com.besuper.minechatchecker.fr.type.AppType;
import com.besuper.minechatchecker.fr.utilitaire.Configuration;

public class PlayerConnectionEvent implements Listener{
	
	@EventHandler
	public void j(PlayerJoinEvent e){
		MineChatChecker.players.put(e.getPlayer(), new MPlayer(e.getPlayer()));
		
		Player p = e.getPlayer();
		MPlayer mp = MineChatChecker.get(p);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MineChatChecker.getPlugin(MineChatChecker.class), new Runnable() {
		     public void run() {
		    	Location l = p.getLocation();
		 		p.setVelocity(p.getLocation().getDirection().multiply(0.01));
		 		
		 		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MineChatChecker.getPlugin(MineChatChecker.class), new Runnable() {
		 		     @SuppressWarnings("deprecation")
					public void run() {
		 		          Location s = p.getLocation();
		 		          if(s.equals(l)){
		 		        	 if(!p.hasPermission(Configuration.permissionBypass)){
		 		        		mp.setDetected(true);
		 		        		mp.setApp(AppType.MineChat);
		 		        		if(Configuration.alert){
		 		        			for(Player v : Bukkit.getOnlinePlayers()){
			 		        			if(v.hasPermission(Configuration.permissionAlert)){
			 		        				v.sendMessage(Configuration.messageAlert.replace("&", "§").replace("%player%", p.getName()));
			 		        			}
			 		        		}
		 		        		}
		 		        		if(Configuration.kick){
		 		        			p.kickPlayer(Configuration.messageKick.replace("&", "§"));
		 		        		}
		 		        	 }
		 		          }
		 		     }
		 		}, (1 * 20));
		     }
 		}, (2 * 20));
	}

}
