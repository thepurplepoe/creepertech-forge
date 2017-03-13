package creepertech.item;

import creepertech.CreeperTech;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CTItem extends Item {

	public String name;

	public CTItem(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	public void registerItemModel() {
		CreeperTech.proxy.registerItemRenderer(this, name);
	}

	@Override
	public CTItem setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}
