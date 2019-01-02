package com.besuper.minechatchecker.fr.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.besuper.minechatchecker.fr.MineChatChecker;

public class PlayerDisconnectEvent implements Listener{
	
	@EventHandler
	public void disconnect(PlayerQuitEvent e){
		MineChatChecker.players.remove(e.getPlayer());
	}

}
