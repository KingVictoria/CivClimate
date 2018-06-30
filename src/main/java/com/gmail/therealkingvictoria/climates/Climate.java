package com.gmail.therealkingvictoria.climates;

import java.util.HashMap;
import java.util.Map;

import com.gmail.therealkingvictoria.crops.CropType;

public class Climate {
	
	public class ClimateData {
		public int timeToGrow;
		public int dropMin, dropMax;
		
		ClimateData(int timeToGrow, int dropMin, int dropMax) {
			this.timeToGrow = timeToGrow;
			this.dropMin = dropMin;
			this.dropMax = dropMax;
		} // ClimateData
	} // class
	
	private Map<CropType, ClimateData> climateData;

	public Climate() {
		climateData = new HashMap<>();
	} // Climate
	
	public void addData(CropType cropType, int timeToGrow, int dropMin, int dropMax) {
		climateData.put(cropType, new ClimateData(timeToGrow, dropMin, dropMax));
	} // addData
	
	public ClimateData getData(CropType cropType) {
		return climateData.get(cropType);
	} // getData

} // class
