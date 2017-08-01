package thepurplepoe.api.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import thepurplepoe.creepertech.common.CreeperTech;

public class ItemWrapper extends Item {
	
	public String itemName;
	
	public ItemWrapper(String name) {
		this.itemName = name;
		this.setUnlocalizedName(itemName);
		this.setRegistryName(itemName);
	}
	
	public ItemWrapper setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	public void registerModel() {
		CreeperTech.proxy.registerItemRenderer(this, 0, itemName);
	}
}
