package com.gmail.therealkingvictoria.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gmail.therealkingvictoria.crops.CropManager;
import com.gmail.therealkingvictoria.crops.CropType;
import com.gmail.therealkingvictoria.crops.CropTypeManager;

public class SeedPlantListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void seedPlantListener(PlayerInteractEvent event) {
		if(event.isCancelled()) return;
		if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;			// Ensures the action is a right click on a block
		if(!event.getClickedBlock().getType().equals(Material.SOIL)) return;	// Ensures the block clicked is soil
		if(event.getItem() == null) return;
		
		if(event.getItem().hasItemMeta()) {
			CropType cropType = CropTypeManager.getBySeedName(event.getItem().getItemMeta().getDisplayName());
			
			if(cropType != null) { // THIS PLANTS THE CROP AS THE TYPE HAS BEEN FOUND
				Block block = event.getClickedBlock().getRelative(BlockFace.UP);
				block.setType(cropType.getBlockMaterial());
				block.setData(cropType.getGrowingState().getData());
				event.getItem().setAmount(event.getItem().getAmount() - 1);
				CropManager.add(block.getLocation(), cropType);
				
				return;
			} // if
		} // if
		
		/* IF YOU GET PAST THIS THE EVENT WILL BE CANCELLED */
		
		Material type = event.getItem().getType();
		if(type == Material.SEEDS || type.equals(Material.BEETROOT_SEEDS) || type.equals(Material.POTATO_ITEM) || type.equals(Material.CARROT_ITEM)) {
			event.setCancelled(true);
		} // if
	} // seedPlantListener

} // class
