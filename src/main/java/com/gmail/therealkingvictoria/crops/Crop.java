package com.gmail.therealkingvictoria.crops;

import org.bukkit.Location;

public class Crop {
	
	Location location;
	CropType cropType;
	
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
