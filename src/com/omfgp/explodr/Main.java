package com.omfgp.explodr;

import java.util.logging.Logger;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	Logger theLog;
	private Player thePlayer = null;
	private World theWorld = null;
	
	public void onEnable() {
		this.theLog = this.getLogger();
		this.theLog.info("Explodr enabled.");		
	}
 
	public void onDisable() {
		this.theLog.info("Explodr disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		
	}

}
