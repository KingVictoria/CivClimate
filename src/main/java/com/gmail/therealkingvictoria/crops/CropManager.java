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

public class CropManager {
	
	private static Map<Crop, Integer> crops;
	
	private static File file;					// File of crop config
	private static FileConfiguration config;	// Crop configuration data
	
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
	
	public static void add(Location loc, CropType type) {
		crops.put(new Crop(loc, type), 0);
	} // add
	
	public static void remove(Location loc, CropType type) {
		Crop crop = new Crop(loc, type);
		crops.remove(crop);
	} // remove

} // class
