package com.omfgp.explodr;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin {
	
	Logger theLog;
	private Player thePlayer = null;
	
	public void onEnable() {
		this.theLog = this.getLogger();
		this.theLog.info("Explodr enabled.");		
	}
 
	public void onDisable() {
		this.theLog.info("Explodr disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		//check if the sender is a player
		if(sender instanceof Player) {
			this.thePlayer = (Player)sender;
		}
		
		//if the /explodr command is sent
		if(cmd.getName().equalsIgnoreCase("Explodr")) {
			//get players location
			Location pLoc = this.thePlayer.getLocation();
			
			//make a radius
			int radius = 3;
			int nradius = radius*(-1);
			
			//make an explosion in that radius
			for(int x=nradius; x<=radius; x++) {
				for(int y=nradius; y<=radius; y++) {
					for(int z=nradius; z<=0; z++) {
						this.thePlayer.getWorld().createExplosion(pLoc.getX()+x, pLoc.getY()+y, pLoc.getZ()+z, 4.0F, true);
					}
				}
			}
			this.thePlayer.setVelocity(new Vector(0,0,0));
		}
		
		//if the /findr command is sent
		if(cmd.getName().equalsIgnoreCase("Findr")) {
			this.thePlayer.sendMessage(ChatColor.GREEN + "Your location is: [ "
													   + this.thePlayer.getLocation().getX() + ", "
													   + this.thePlayer.getLocation().getY() + ", "
													   + this.thePlayer.getLocation().getZ() + " ]");
		}
		
		//if the /testr command is sent
		if(cmd.getName().equalsIgnoreCase("Testr")) {
			for(int i=0; i<args.length; i++) {
				this.thePlayer.sendMessage(ChatColor.GREEN + "args["+ i +"]: " + args[i]);
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("Drillr")) {
			Location bLoc = this.thePlayer.getLocation();
			for(int i=bLoc.getBlockZ()-2; i==bLoc.getZ()-(Integer.valueOf(args[0])+2); i--) {
				this.thePlayer.getWorld().createExplosion(bLoc.getX(), bLoc.getY(), bLoc.getZ(), 4.0F, true);
			}
			this.thePlayer.setVelocity(new Vector(0,0,0));
		}
		
		if(cmd.getName().equalsIgnoreCase("Flamr")) {
			Block targetBlock = this.thePlayer.getTargetBlock(null, 50);
			Location bLoc = targetBlock.getLocation();
			for(int i=bLoc.getBlockZ(); i>bLoc.getZ()+2; i++) {
				this.thePlayer.getWorld().createExplosion(bLoc.getX(), bLoc.getY(), bLoc.getZ(), 4.0F, true);
			}
		}
		
		return false;
	}

}
