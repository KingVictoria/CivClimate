package com.gmail.therealkingvictoria.climates;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.gmail.therealkingvictoria.climates.Climate.ClimateData;
import com.gmail.therealkingvictoria.crops.CropType;
import com.gmail.therealkingvictoria.crops.CropTypeManager;

/**
 * Loads the biomes/worlds config
 * @author KingVictoria
 */
public class ClimateManager {
	
	private static Map<String, Climate> climates;

	private static File file;					// File of crop config
	private static FileConfiguration config;	// Crop configuration data
	
	public static void initialize(File configFile) {
		try {
			file = configFile;
			file.createNewFile();
			config = YamlConfiguration.loadConfiguration(configFile);
			setDefaults();
			climates = loadClimates();
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
					if(!config.contains(path+"timetogrow")) config.set(path+"timetogrow", 1);
					if(!config.contains(path+"dropminimum")) config.set(path+"dropminimum", 1);
					if(!config.contains(path+"dropmaximum")) config.set(path+"dropmaximum", 3);
				} // for
			} // for
		} // for
		
		try {
			config.save(file);
		} catch (IOException e) {
			Bukkit.getLogger().severe("Unable to generate climates config");
			e.printStackTrace();
		} // try/catch
	} // setDefaults
	
	private static Map<String, Climate> loadClimates() {
		Map<String, Climate> climates = new HashMap<>();
		
		for(World world: Bukkit.getWorlds()) {
			for(Biome biome: Biome.values()) {
				String key = world.getName()+"_"+biome.toString();
				
				Climate climate = new Climate();
				for(CropType cropType: CropTypeManager.getCropTypes()) {
					String path = world.getName()+"."+biome.toString()+"."+cropType.getID()+".";
					int timeToGrow = config.getInt(path+"timetogrow");
					int dropMin = config.getInt(path+"dropminimum");
					int dropMax = config.getInt(path+"dropmaximum");
					climate.addData(cropType, timeToGrow, dropMin, dropMax);
				} // for
				
				climates.put(key, climate);
			} // for
		} // for
		
		return climates;
	} // loadClimates
	
	/**
	 * Gets the ClimateData for a given CropType at a given Location
	 * @param cropType
	 * @param location
	 * @return ClimateData
	 */
	public static ClimateData getData(CropType cropType, Location location) {
		return climates.get(location.getWorld().getName()+"_"+location.getBlock().getBiome().toString()).getData(cropType);
	} // getData

} // class
