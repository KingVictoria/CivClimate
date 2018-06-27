package com.gmail.therealkingvictoria;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.therealkingvictoria.listeners.CropGrowthListener;

public class CivClimate extends JavaPlugin {
    
	public void onEnable() {
		
		getServer().getPluginManager().registerEvents(new CropGrowthListener(), this);
		
	} // onEnable
	
	public void onDisable() {
		
	} // onDisable
	
} // class
