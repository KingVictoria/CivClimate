package com.gmail.therealkingvictoria.crops;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Loads all locations and types from file and handles each individual crop
 * @author KingVictoria
 */
public class CropManager {
	
	private static Map<Crop, Integer> crops;
	
	private static File file;					// File of crop config
	private static FileConfiguration config;	// Crop configuration data
	
	/**
	 * Initializes the CropManager, loading crops from file
	 * @param configFile the file of the crops config
	 */
	public static void initialize(File configFile) {
		try {
			file = configFile;
			file.createNewFile();
			config = YamlConfiguration.loadConfiguration(configFile);
			crops = loadCrops();
		} catch (IllegalArgumentException | IOException e) {
			Bukkit.getLogger().severe("Crops config not loaded");
			e.printStackTrace();
		} // try/catch
	} // initialize
	
	/**
	 * Terminates the CropManager, saving growing crops to file
	 */
	public static void terminate() {
		config = new YamlConfiguration();
		
		for(Crop crop: crops.keySet()) {
			String path = crop.location.getWorld().getName() + "" + crop.location.getBlockX() + "" + crop.location.getBlockY() + "" + crop.location.getBlockZ();
			config.set(path + ".x", crop.location.getBlockX());
			config.set(path + ".y", crop.location.getBlockY());
			config.set(path + ".z", crop.location.getBlockZ());
			config.set(path + ".world", crop.location.getWorld().getName());
			config.set(path + ".minutes", crops.get(crop.location).intValue());
			config.set(path + ".croptypeID", crop.cropType.getID());
		} // for
		
		try {
			config.save(file);
		} catch (IOException e) {
			Bukkit.getLogger().severe("Unable to save crop locations");
			e.printStackTrace();
		} // try/catch
	} // terminate
	
	/**
	 * Loads crops from FileConfiguration into memory
	 * @return Map of Crop to Integer, where Crop is the crop data and Integer is the number of minutes since planted
	 */
	private static Map<Crop, Integer> loadCrops() {
		Map<Crop, Integer> crops = new HashMap<>();
		
		for(String key: config.getKeys(false)) {
			double x = config.getDouble(key+".x");
			double y = config.getDouble(key+".y");
			double z = config.getDouble(key+".z");
			World world = Bukkit.getWorld(config.getString(key+".world"));
			int minutes = config.getInt(key+".minutes");
			String id = config.getString(key+".croptypeID");
			
			crops.put(new Crop(new Location(world, x, y, z), CropTypeManager.getByID(id)), minutes);
		} // for
		
		return crops;
	} // loadCrops
	
	/**
	 * Adds a crop to the manager
	 * @param loc Location of crop block
	 * @param type CropType of the crop
	 */
	public static void add(Location loc, CropType type) {
		crops.put(new Crop(loc, type), 0);
	} // add
	
	/**
	 * Removes a crop from the manager
	 * @param loc Location of the crop block
	 * @param type CropType of the crop
	 */
	public static void remove(Location loc, CropType type) {
		Crop crop = new Crop(loc, type);
		crops.remove(crop);
	} // remove

} // class
