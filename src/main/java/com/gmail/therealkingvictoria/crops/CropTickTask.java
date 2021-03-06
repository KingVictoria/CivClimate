package com.gmail.therealkingvictoria.crops;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.therealkingvictoria.climates.ClimateManager;

public class CropTickTask extends BukkitRunnable {
	
	Map<Crop, Integer> crops;
	
	public CropTickTask(Map<Crop, Integer> crops) {
		this.crops = crops;
	} // CropTickTask

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		List<Crop> toFinish = new ArrayList<>();
		
		for(Crop crop: crops.keySet()) {
			crops.put(crop, crops.get(crop).intValue() + 1);
			if(isFinished(crop, crops.get(crop).intValue())) toFinish.add(crop);
		} // for
		
		for(Crop crop: toFinish) {
			crops.remove(crop);
			crop.location.getBlock().setData(crop.cropType.getGrownState().getData());
		} // for
	} // run
	
	private boolean isFinished(Crop crop, int minutes) {
		if(ClimateManager.getData(crop.cropType, crop.location).timeToGrow <= minutes) return true;
		return false;
	} // isFinished

} // class