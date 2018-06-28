package com.gmail.therealkingvictoria;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.therealkingvictoria.crops.CropManager;
import com.gmail.therealkingvictoria.crops.CropTypeManager;
import com.gmail.therealkingvictoria.listeners.CropGrowthListener;
import com.gmail.therealkingvictoria.listeners.SeedPlantListener;

/**
 * The goal of this plugin is to allow for a greater variety of custom crops by hacking the wheat, carrot, potato, and beetroot crops
 * to create 10 potential options for custom crops. It also allows for configuration across biomes and worlds.This is mainly a proof-
 * of-concept, and may be expanded in the future to both melon and pumpkin stems, cocoa beans, and netherwart as well as handling 
 * other growing things such as trees.
 * @author KingVictoria
 */
public class CivClimate extends JavaPlugin {
    
	public void onEnable() {
		getDataFolder().mkdirs();													// Ensures the data folder exists
		CropTypeManager.initialize(new File(getDataFolder(), "croptypes.yaml"));	// Loads CropTypes from file and manages them
		CropManager.initialize(new File(getDataFolder(), "crops.yaml"));			// Loads Crops from file and manages them
		
		getServer().getPluginManager().registerEvents(new CropGrowthListener(), this);	// Prevents normal growth
		getServer().getPluginManager().registerEvents(new SeedPlantListener(), this);	// Handles planting custom crops
	} // onEnable
	
	public void onDisable() {
		CropManager.terminate();
	} // onDisable
	
} // class
