package com.besuper.minechatchecker.fr.runnable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.besuper.minechatchecker.fr.MineChatChecker;
import com.besuper.minechatchecker.fr.players.MPlayer;
import com.besuper.minechatchecker.fr.utilitaire.Configuration;

public class MineChatTester implements Runnable{

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		for(Player p : Bukkit.getOnlinePlayers()){
			MPlayer mp = MineChatChecker.get(p);
			
			Location l = p.getLocation();
	 		p.setVelocity(p.getLocation().getDirection().multiply(0.01));
	 		
	 		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MineChatChecker.getPlugin(MineChatChecker.class), new Runnable() {
				public void run() {
	 		          Location s = p.getLocation();
	 		          if(s.equals(l)){
	 		        	 if(!p.hasPermission(Configuration.permissionBypass)){
	 		        		mp.setDetected(true);
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
	}

}
