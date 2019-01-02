package com.besuper.minechatchecker.fr;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.besuper.minechatchecker.fr.api.string.Converter;
import com.besuper.minechatchecker.fr.cache.MessageCache;
import com.besuper.minechatchecker.fr.commands.MineChatCommand;
import com.besuper.minechatchecker.fr.event.AsyncPlayerChat;
import com.besuper.minechatchecker.fr.event.PlayerConnectionEvent;
import com.besuper.minechatchecker.fr.players.MPlayer;
import com.besuper.minechatchecker.fr.runnable.MineChatTester;
import com.besuper.minechatchecker.fr.utilitaire.Configuration;
import com.besuper.minechatchecker.fr.utilitaire.updater;

public class MineChatChecker extends JavaPlugin{

	public static HashMap<Player, MPlayer> players = new HashMap<Player, MPlayer>();
	public static MineChatChecker mine;
	
	@Override
	public void onEnable() {
		mine = this;
		
		getCommand(Converter.a(MessageCache.minechat.name())).setExecutor(new MineChatCommand());
		
		Bukkit.getPluginManager().registerEvents(new PlayerConnectionEvent(), this);
		Bukkit.getPluginManager().registerEvents(new AsyncPlayerChat(), this);
		Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new MineChatTester(), 60*20, 60*20);
		
		updater.updates();
		Configuration.setConfig();
		
		for(Player p : Bukkit.getOnlinePlayers()){
			players.put(p, new MPlayer(p));
		}
		
	}
	
	public static MPlayer get(Player p){
		return players.get(p);
	}
	
	@Override
	public void onDisable() {
		players.clear();
	}
	
}
