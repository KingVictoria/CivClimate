package com.gmail.therealkingvictoria;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.therealkingvictoria.crops.CropManager;
import com.gmail.therealkingvictoria.crops.CropTypeManager;
import com.gmail.therealkingvictoria.listeners.CropGrowthListener;

public class CivClimate extends JavaPlugin {
    
	public void onEnable() {
		getDataFolder().mkdirs();
		CropTypeManager.initialize(new File(getDataFolder(), "croptypes.yaml"));
		CropManager.initialize(new File(getDataFolder(), "crops.yaml"));
		
		getServer().getPluginManager().registerEvents(new CropGrowthListener(), this);
	} // onEnable
	
	public void onDisable() {
		CropManager.terminate();
	} // onDisable
	
} // class
