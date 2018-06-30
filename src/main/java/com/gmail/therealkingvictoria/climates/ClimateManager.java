package com.gmail.therealkingvictoria.climates;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.gmail.therealkingvictoria.crops.CropType;
import com.gmail.therealkingvictoria.crops.CropTypeManager;

/**
 * Loads the biomes/worlds config
 * @author KingVictoria
 */
public class ClimateManager {
	
	

	private static File file;					// File of crop config
	private static FileConfiguration config;	// Crop configuration data
	
	public static void initialize(File configFile, Plugin plugin) {
		try {
			file = configFile;
			file.createNewFile();
			config = YamlConfiguration.loadConfiguration(configFile);
			setDefaults();
		} catch (IllegalArgumentException | IOException e) {
			Bukkit.getLogger().severe("Crops config not loaded");
			e.printStackTrace();
		} // try/catch
	} // initialize
	
	private static void setDefaults() {
		for(World world: Bukkit.getWorlds()) {
			for(Biome biome: Biome.values()) {
				for(CropType cropType: CropTypeManager.getCropTypes()) {
					String path = world.getName()+"."+biome.toString()+"."+cropType.getID()+".";
					if(!config.contains(path+"timetogrow")) config.set(path+"timetogrow", -1);
				} // for
			} // for
		} // for
	} // setDefaults

} // class
