package com.besuper.minechatchecker.fr.utilitaire;

import java.io.File;
import java.io.IOException;

import com.besuper.minechatchecker.fr.MineChatChecker;

public class Configuration {

	public static String messageKick, permissionBypass, permissionAlert, messageAlert;
	public static boolean kick, alert;
	
	public static void setConfig(){
		loadconfig();
		
		messageKick = MineChatChecker.getPlugin(MineChatChecker.class).getConfig().getString("messageKick");
		messageAlert = MineChatChecker.getPlugin(MineChatChecker.class).getConfig().getString("messageAlert");
		permissionBypass = MineChatChecker.getPlugin(MineChatChecker.class).getConfig().getString("bypassPermission");
		permissionAlert = MineChatChecker.getPlugin(MineChatChecker.class).getConfig().getString("alertPermission");
		
		kick = MineChatChecker.getPlugin(MineChatChecker.class).getConfig().getBoolean("kick");
		alert = MineChatChecker.getPlugin(MineChatChecker.class).getConfig().getBoolean("alertToAdmin");
	}
	
	public static void loadconfig(){
		if (!MineChatChecker.getPlugin(MineChatChecker.class).getDataFolder().exists())
			MineChatChecker.getPlugin(MineChatChecker.class).getDataFolder().mkdir();
        File file = new File(MineChatChecker.getPlugin(MineChatChecker.class).getDataFolder(), "config.yml");
        if (!file.exists()) {
            try{
            	java.nio.file.Files.copy(file.toPath(), file.toPath());
            } catch (IOException e) {}
        }
        MineChatChecker.getPlugin(MineChatChecker.class).getConfig().options().copyDefaults(true);
        MineChatChecker.getPlugin(MineChatChecker.class).saveConfig();
	}
	
}
