package thepurplepoe.api.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import thepurplepoe.creepertech.common.CreeperTech;

public class BlockWrapper extends Block {
	
	protected String itemName;
	
	public BlockWrapper(Material material, String name)  {
		super(material);
		
		this.itemName = name;
		this.setUnlocalizedName(itemName);
		this.setRegistryName(itemName);
	}
	
	public BlockWrapper setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	public void registerModel(ItemBlock itemBlock) {
		CreeperTech.proxy.registerItemRenderer(itemBlock, 0, itemName);
	}
}
