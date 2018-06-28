package com.gmail.therealkingvictoria.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;

/**
 * Prevents crops from growing naturally (this plugin handles all crop growth internally)
 * @author KingVictoria
 */
public class CropGrowthListener implements Listener {
	
	@EventHandler
	public void cropGrow(BlockGrowEvent event) {
		event.setCancelled(true);
	} // cropGrow

} // class
