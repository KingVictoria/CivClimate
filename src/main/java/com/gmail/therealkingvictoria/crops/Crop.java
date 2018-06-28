package com.gmail.therealkingvictoria.crops;

import org.bukkit.Location;

/**
 * Stores location and type data for a given crop
 * @author KingVictoria
 */
public class Crop {
	
	Location location;
	CropType cropType;
	
	/**
	 * Creates a crop
	 * @param loc Location of the crop block
	 * @param type CropType type of crop
	 */
	Crop(Location loc, CropType type) {
		location = loc;
		cropType = type;
	} // Crop
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Crop)) return false;
		
		Crop crop = (Crop) obj;
	
		if(location.equals(crop.location) && cropType.equals(crop.cropType)) return true;
		return false;
	} // equals
} // class
