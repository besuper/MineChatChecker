package com.besuper.minechatchecker.fr.players;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.besuper.minechatchecker.fr.type.AppType;
import com.besuper.minechatchecker.fr.type.PhoneType;

public class MPlayer {

	Player p;
	PhoneType phone = PhoneType.Default;
	AppType app = AppType.No;
	boolean detected = false;
	
	public MPlayer(Player p) {
		this.p=p;
	}
	
	public Player getPlayer() {
		return this.p;
	}
	
	public PhoneType getPhone() {
		return phone;
	}
	
	public AppType getApp() {
		return app;
	}
	
	public boolean getDetected() {
		return detected;
	}
	
	public void setPhone(PhoneType phone) {
		this.phone = phone;
	}
	
	public void setApp(AppType app) {
		this.app = app;
	}
	
	public void setDetected(boolean detected) {
		this.detected = detected;
	}

	public Location getLocation() {
		return p.getLocation();
	}

	public void setVelocity(Vector multiply) {
		p.setVelocity(multiply);
	}

	public boolean hasPermission(String permissionBypass) {
		return p.hasPermission(permissionBypass);
	}
	
}
