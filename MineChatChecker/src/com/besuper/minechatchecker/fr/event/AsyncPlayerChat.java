package com.besuper.minechatchecker.fr.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.besuper.minechatchecker.fr.MineChatChecker;
import com.besuper.minechatchecker.fr.players.MPlayer;
import com.besuper.minechatchecker.fr.type.AppType;
import com.besuper.minechatchecker.fr.type.PhoneType;
import com.besuper.minechatchecker.fr.utilitaire.Configuration;

public class AsyncPlayerChat implements Listener{
	
	@EventHandler
	public void chat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String message = e.getMessage();
		MPlayer mp = MineChatChecker.get(p);
		
		if(mp.getDetected()){
			if(message.contains("Android")){
				mp.setPhone(PhoneType.Android);
			}else {
				mp.setPhone(PhoneType.IOS);
			}
		}
		
		if(message.contains("PickaxeChat")){
			Location l = p.getLocation();
	 		p.setVelocity(p.getLocation().getDirection().multiply(0.01));
	 		
	 		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MineChatChecker.getPlugin(MineChatChecker.class), new Runnable() {
				@SuppressWarnings("deprecation")
				public void run() {
	 		          Location s = p.getLocation();
	 		          if(s.equals(l)){
	 		        	 if(!p.hasPermission(Configuration.permissionBypass)){
	 		        		mp.setDetected(true);
	 		        		mp.setApp(AppType.PickaxeChat);
	 		        		if(Configuration.alert){
	 		        			for(Player v : Bukkit.getOnlinePlayers()){
		 		        			if(v.hasPermission(Configuration.permissionAlert)){
		 		        				if(message.contains("Android")){
		 		        					mp.setPhone(PhoneType.Android);
		 		        					v.sendMessage(Configuration.messageAlert.replace("&", "§").replace("%player%", p.getName()) + " or PickaxeChat for Android");
		 		        				}else {
		 		        					mp.setPhone(PhoneType.IOS);
		 		        					v.sendMessage(Configuration.messageAlert.replace("&", "§").replace("%player%", p.getName()) + " or PickaxeChat for IOS");
		 		        				}
		 		        			}
		 		        		}
	 		        		}
	 		        	 }
	 		          }
	 		     }
	 		}, (1 * 20));
		}
	}

}
