package com.gmail.therealkingvictoria;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.therealkingvictoria.crops.CropTypeManager;
import com.gmail.therealkingvictoria.listeners.CropGrowthListener;

public class CivClimate extends JavaPlugin {
    
	public void onEnable() {
		
		getDataFolder().mkdirs();
		CropTypeManager.initialize(new File(getDataFolder(), "croptypes.yaml"));
		
		getServer().getPluginManager().registerEvents(new CropGrowthListener(), this);
		
	} // onEnable
	
	public void onDisable() {
		
	} // onDisable
	
} // class
