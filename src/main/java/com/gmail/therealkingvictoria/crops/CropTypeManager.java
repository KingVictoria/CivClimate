package com.gmail.therealkingvictoria.crops;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CropTypeManager {
	
	private static List<CropType> types;		// Availible types of crops
	
	private static File file;					// File of crop config
	private static FileConfiguration config;	// Crop configuration data
	
	/**
	 * Sets default config, loads growing crop locations, how long each growing crop has been growing, and all types of custom crops
	 * @param configFile File of crop config
	 */
	public static void initialize(File configFile) {
		try {
			file = configFile;
			file.createNewFile();
			config = YamlConfiguration.loadConfiguration(configFile);
			setDefaults();
			types = loadCropTypes();
		} catch (IllegalArgumentException e) {
			Bukkit.getLogger().severe("Crop type config not loaded");
			e.printStackTrace();
		} catch (IOException e) {
			Bukkit.getLogger().severe("Unable to create crop type config");
			e.printStackTrace();
		} catch (SecurityException e) {
			Bukkit.getLogger().severe("Unable to create crop type config due to security restrictions on this system");
			e.printStackTrace();
		} // try/catch/catch/catch
	} // initialize
	
	/**
	 * Sets default config for all possible pairs of growing states
	 */
	private static void setDefaults() {
		for(int i = 0; i < 4; i++) {
			if(!config.contains("wheat_"+i+".seedName")) config.set("wheat_"+i+".seedName", ChatColor.YELLOW+"WHEAT_"+i+" SEEDS");
			if(!config.contains("wheat_"+i+".cropName")) config.set("wheat_"+i+".cropName", ChatColor.YELLOW+"WHEAT_"+i+" CROP");
			
			if(!config.contains("wheat_"+i+".seedMaterial")) config.set("wheat_"+i+".seedMaterial", Material.SEEDS.toString());
			if(!config.contains("wheat_"+i+".cropMaterial")) config.set("wheat_"+i+".cropMaterial", Material.WHEAT.toString());
		} // for
		
		for(int i = 0; i < 2; i++) {
			if(!config.contains("carrot_"+i+".seedName")) config.set("carrot_"+i+".seedName", ChatColor.YELLOW+"CARROT_"+i+" SEEDS");
			if(!config.contains("carrot_"+i+".cropName")) config.set("carrot_"+i+".cropName", ChatColor.YELLOW+"CARROT_"+i+" CROP");
			
			if(!config.contains("carrot_"+i+".seedMaterial")) config.set("carrot_"+i+".seedMaterial", Material.SEEDS.toString());
			if(!config.contains("carrot_"+i+".cropMaterial")) config.set("carrot_"+i+".cropMaterial", Material.WHEAT.toString());
		} // for
		
		for(int i = 0; i < 2; i++) {
			if(!config.contains("potato_"+i+".seedName")) config.set("potato_"+i+".seedName", ChatColor.YELLOW+"POTATO_"+i+" SEEDS");
			if(!config.contains("potato_"+i+".cropName")) config.set("potato_"+i+".cropName", ChatColor.YELLOW+"POTATO_"+i+" CROP");
			
			if(!config.contains("potato_"+i+".seedMaterial")) config.set("potato_"+i+".seedMaterial", Material.SEEDS.toString());
			if(!config.contains("potato_"+i+".cropMaterial")) config.set("potato_"+i+".cropMaterial", Material.WHEAT.toString());
		} // for
		
		for(int i = 0; i < 2; i++) {
			if(!config.contains("beetroot_"+i+".seedName")) config.set("beetroot_"+i+".seedName", ChatColor.YELLOW+"BEETROOT_"+i+" SEEDS");
			if(!config.contains("beetroot_"+i+".cropName")) config.set("beetroot_"+i+".cropName", ChatColor.YELLOW+"BEETROOT_"+i+" CROP");
			
			if(!config.contains("beetroot_"+i+".seedMaterial")) config.set("beetroot_"+i+".seedMaterial", Material.SEEDS.toString());
			if(!config.contains("beetroot_"+i+".cropMaterial")) config.set("beetroot_"+i+".cropMaterial", Material.WHEAT.toString());
		} // for
		
		try {
			config.save(file);
		} catch (IOException e) {
			Bukkit.getLogger().severe("Unable to save croptypes config");
			e.printStackTrace();
		} // try/catch
	} // setDefaults
	
	/**
	 * Goes through all possible pairs of growing states and gets their names and materials from the config
	 * @return List of CropTypes
	 */
	@SuppressWarnings("deprecation")
	private static List<CropType> loadCropTypes() {
		List<CropType> types = new ArrayList<>();
		
		for(int i = 0; i < 4; i++) {
			String seedName = config.getString("wheat_"+i+".seedName");
			String cropName = config.getString("wheat_"+i+".cropName");
			
			Material seedMaterial = Material.valueOf(config.getString("wheat_"+i+".seedMaterial"));
			Material cropMaterial = Material.valueOf(config.getString("wheat_"+i+".cropMaterial"));
			Material blockMaterial = Material.WHEAT;
			
			CropState growingState = CropState.getByData((byte) (i*2));
			CropState grownState = CropState.getByData((byte) (i*2 + 1));
			
			types.add(new CropType("wheat_"+i, seedName, cropName, seedMaterial, cropMaterial, blockMaterial, growingState, grownState));
		} // for
		
		for(int i = 0; i < 2; i++) {
			String seedName = config.getString("carrot_"+i+".seedName");
			String cropName = config.getString("carrot_"+i+".cropName");
			
			Material seedMaterial = Material.valueOf(config.getString("carrot_"+i+".seedMaterial"));
			Material cropMaterial = Material.valueOf(config.getString("carrot_"+i+".cropMaterial"));
			Material blockMaterial = Material.CARROT;
			
			CropState growingState, grownState;
			
			if(i == 0) {
				growingState = CropState.getByData((byte) 0);
				grownState = CropState.getByData((byte) 2);
			} else {
				growingState = CropState.getByData((byte) 5);
				grownState = CropState.getByData((byte) 7);
			} // if/else
			
			types.add(new CropType("carrot_"+i, seedName, cropName, seedMaterial, cropMaterial, blockMaterial, growingState, grownState));
		} // for
		
		for(int i = 0; i < 2; i++) {
			String seedName = config.getString("potato_"+i+".seedName");
			String cropName = config.getString("potato_"+i+".cropName");
			
			Material seedMaterial = Material.valueOf(config.getString("potato_"+i+".seedMaterial"));
			Material cropMaterial = Material.valueOf(config.getString("potato_"+i+".cropMaterial"));
			Material blockMaterial = Material.POTATO;
			
			CropState growingState, grownState;
			
			if(i == 0) {
				growingState = CropState.getByData((byte) 0);
				grownState = CropState.getByData((byte) 2);
			} else {
				growingState = CropState.getByData((byte) 5);
				grownState = CropState.getByData((byte) 7);
			} // if/else
			
			types.add(new CropType("potato_"+i, seedName, cropName, seedMaterial, cropMaterial, blockMaterial, growingState, grownState));
		} // for
		
		for(int i = 0; i < 2; i++) {
			String seedName = config.getString("beetroot_"+i+".seedName");
			String cropName = config.getString("beetroot_"+i+".cropName");
			
			Material seedMaterial = Material.valueOf(config.getString("beetroot_"+i+".seedMaterial"));
			Material cropMaterial = Material.valueOf(config.getString("beetroot_"+i+".cropMaterial"));
			Material blockMaterial = Material.BEETROOT;
			
			CropState growingState, grownState;
			
			if(i == 0) {
				growingState = CropState.getByData((byte) 0);
				grownState = CropState.getByData((byte) 1);
			} else {
				growingState = CropState.getByData((byte) 2);
				grownState = CropState.getByData((byte) 3);
			} // if/else
			
			types.add(new CropType("beetroot_"+i, seedName, cropName, seedMaterial, cropMaterial, blockMaterial, growingState, grownState));
		} // for
		
		return types;
	} // loadTypes
	
	public static CropType getByID(String id) {
		for(CropType type: types) {
			if(type.getID().equals(id)) return type;
		} // for
		
		return null;
	} // getByID
	
} // class
