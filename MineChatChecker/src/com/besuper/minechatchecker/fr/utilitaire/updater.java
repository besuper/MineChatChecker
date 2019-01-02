package com.besuper.minechatchecker.fr.utilitaire;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.besuper.minechatchecker.fr.api.string.Converter;
import com.besuper.minechatchecker.fr.cache.MessageCache;

public class updater {

private static String version = Converter.a(MessageCache._1ZZXZ21.name());
	
	public static void updates(){
		String update = getupdate();
		if(!(update.contains(version))){Bukkit.getConsoleSender().sendMessage((Object)ChatColor.GREEN + "[MineChatChecker] New update is available !");}
	}
	
	private static String getupdate(){
		try{
	        URL urlObj = new URL(Converter.a(MessageCache.httpsZZXZ3ZZXZ4ZZXZ4pastebinZZXZ2comZZXZ4rawZZXZ4SqRq77zC.name()));
	        URLConnection con = urlObj.openConnection();
	        con.setDoOutput(true);
	        con.connect();
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        StringBuilder response = new StringBuilder();
	        String inputLine;
	        String newLine = System.getProperty("line.separator");
	        while ((inputLine = in.readLine()) != null){
	            response.append(inputLine + newLine);
	        }
	        in.close();
	        return response.toString();
	    }catch (Exception e){
	        throw new RuntimeException(e);
	    }
	}
	
}
