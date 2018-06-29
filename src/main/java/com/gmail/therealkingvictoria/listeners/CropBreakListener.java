package com.gmail.therealkingvictoria.listeners;

import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.therealkingvictoria.crops.CropManager;
import com.gmail.therealkingvictoria.crops.CropType;
import com.gmail.therealkingvictoria.crops.CropTypeManager;

public class CropBreakListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCropBreak(BlockBreakEvent event) {
		if(event.isCancelled()) return;
		
		Material mat = event.getBlock().getType();
		
		if(mat == Material.CROPS || mat == Material.CARROT || mat == Material.POTATO || mat == Material.BEETROOT_BLOCK) {
			
			CropType cropType = CropTypeManager.getByCropState(mat, CropState.getByData(event.getBlock().getData()));
			
			if(cropType != null) {
				
				event.setCancelled(true);
				event.getBlock().setType(Material.AIR);
				
				/* DEMONSTRATION CODE -- WILL NEED TO RE-IMPLEMENT AFTER BIOMES/WORLDS CONFIG COMPLETE */
				Location loc = event.getBlock().getLocation();
				CropManager.remove(loc);
				loc = loc.add(0.5, 0.5, 0.5);
				
				ItemStack cropDrops = new ItemStack(cropType.getCropMaterial());
				ItemStack seedDrops = new ItemStack(cropType.getSeedMaterial());
				ItemMeta cropDropsMeta = cropDrops.getItemMeta();
				ItemMeta seedDropsMeta = seedDrops.getItemMeta();
				cropDropsMeta.setDisplayName(cropType.getCropName());
				seedDropsMeta.setDisplayName(cropType.getSeedName());
				cropDrops.setItemMeta(cropDropsMeta);
				seedDrops.setItemMeta(seedDropsMeta);
				cropDrops.setAmount((int) (Math.random() * 3) + 1);
				seedDrops.setAmount((int) (Math.random() * 3) + 1);
				
				loc.getWorld().dropItemNaturally(loc, cropDrops);
				loc.getWorld().dropItemNaturally(loc, seedDrops);
				/* END OF DEMONSTRATION CODE */
			} // if
		} // if
	} // onCropBreak

} // class
