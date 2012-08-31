package com.omfgp.explodr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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
			
			//does this make you invulnerable?
			this.thePlayer.setGameMode(GameMode.CREATIVE);
			
			//make a radius
			int radius = 6;
			int nradius = radius*(-1);
			
			//make an explosion in that radius
			for(int x=nradius; x<=radius; x++) {
				for(int z=nradius; z<=radius; z++) {
					for(int y=nradius; y<=0; y++) {
						this.thePlayer.getWorld().createExplosion(pLoc.getX()+x, pLoc.getY()+y, pLoc.getZ()+z, 3.0F, false);
					}
				}
			}
			this.thePlayer.setVelocity(new Vector(0,0,0));
			this.thePlayer.setGameMode(GameMode.SURVIVAL);
		}
		
		//if the /findr command is sent
		if(cmd.getName().equalsIgnoreCase("Findr")) {
			this.thePlayer.sendMessage(ChatColor.GREEN + "Your location is: [ "
													   + this.thePlayer.getLocation().getBlockX() + ", "
													   + this.thePlayer.getLocation().getBlockZ() + ", "
													   + this.thePlayer.getLocation().getBlockY() + " ]");
		}
		
		//if the /testr command is sent
		if(cmd.getName().equalsIgnoreCase("Testr")) {
			for(int i=0; i<args.length; i++) {
				this.thePlayer.sendMessage(ChatColor.GREEN + "args["+ i +"]: " + args[i]);
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("Flamr")) {
			List<Entity> entList = this.thePlayer.getNearbyEntities(5, 2, 5);
			for(Entity ent:entList) {
				Location eLoc = ent.getLocation();
				this.thePlayer.getWorld().createExplosion(eLoc, 4.0F, true);
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("Powr")) {
			ArrayList<Block> blockList = new ArrayList<Block>();
			Location pLoc = this.thePlayer.getLocation();
			for(int x=-5; x<=5; x++) {
				for(int z=-5; z<=5; z++) {
					blockList.add(this.thePlayer.getWorld().getBlockAt(pLoc.getBlockX()+x, pLoc.getBlockY()-3, pLoc.getBlockZ()+z));
					blockList.add(this.thePlayer.getWorld().getBlockAt(pLoc.getBlockX()+x, pLoc.getBlockY()-1, pLoc.getBlockZ()+z));
					blockList.add(this.thePlayer.getWorld().getBlockAt(pLoc.getBlockX()+x, pLoc.getBlockY()-2, pLoc.getBlockZ()+z));
				}
			}
			
			for(Block blk:blockList) {
				blk.breakNaturally();
			}
			
			List<Entity> entList = this.thePlayer.getNearbyEntities(15, 3, 15);
			for(int i=0; i<=10000; i++) {
				for(Entity ent:entList) {
					double rand = new Random().nextInt(6)/10.0 + i/10000;
					ent.setVelocity(new Vector(rand/5,rand,rand/5));
				}
			}
		}
		
		return false;
	}

}
