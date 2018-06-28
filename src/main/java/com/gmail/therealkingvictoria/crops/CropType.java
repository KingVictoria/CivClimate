package com.gmail.therealkingvictoria.crops;

import org.bukkit.CropState;
import org.bukkit.Material;

/**
 * A custom crop type
 * @author KingVictoria
 */
public class CropType {
	
	private String id;
	private String seedName;
	private String cropName;
	
	private Material seedMaterial;
	private Material cropMaterial;
	private Material blockMaterial;
	
	private CropState growingState;
	private CropState grownState;
	
	public CropType(String id, String seedName, String cropName, Material seedMaterial, Material cropMaterial, Material blockMaterial, CropState growingState, CropState grownState) {
		this.id 			= id;
		this.seedName 		= seedName;
		this.cropName 		= cropName;
		this.seedMaterial 	= seedMaterial;
		this.cropMaterial 	= cropMaterial;
		this.blockMaterial 	= blockMaterial;
		this.growingState 	= growingState;
		this.grownState 	= grownState;
	} // Crop
	
	public String getID()				{ return id; }
	public String getSeedName() 		{ return seedName; }
	public String getCropName() 		{ return cropName; }
	public Material getSeedMaterial() 	{ return seedMaterial; }
	public Material getCropMaterial()	{ return cropMaterial; }
	public Material getBlockMaterial()	{ return blockMaterial; }
	
	public boolean equals(Material material, CropState state) {
		if(material.equals(blockMaterial) && (state.equals(growingState) || state.equals(grownState))) return true;
		return false;
	} // equals(Material, CropState)
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof CropType)) return false;
		
		CropType type = (CropType) obj;
		
		if(id.equals(type.id)) return true;
		return false;
	} // equals(CropType)

} // class
