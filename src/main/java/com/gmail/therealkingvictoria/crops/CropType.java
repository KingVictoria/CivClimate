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
	
	/**
	 * Creates a CropType, used to store the types of crops into memory
	 * @param id String id used to differentiate crops in the config (ex: wheat_0)
	 * @param seedName String name of the seeds of this crop
	 * @param cropName String name of the crops (the output item) of this crop
	 * @param seedMaterial Material the material of the seeds of this crop
	 * @param cropMaterial Material the material of the crops (output item) of this crop
	 * @param blockMaterial Material the material of the crop block
	 * @param growingState CropState growth state of the crop block when the crop should be still growing
	 * @param grownState CropState growth state of the crop block when the crop should be fully grown
	 */
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
	
	/**
	 * Equals for determining whether a given material/state combo is this type of crop (useful for determining if a given crop block is this crop)
	 * @param material Material of the crop block
	 * @param state CropState growth state of the crop block
	 * @return true of the material is the same and the state is either the growing or grown state of this CropType
	 */
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
