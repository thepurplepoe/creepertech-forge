package creepertech.item;

import creepertech.CreeperTech;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CTItem extends Item {

	public String name;
	public static double rot;

	public CTItem(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		rot = 0;
	}

	public void registerItemModel() {
		CreeperTech.proxy.registerItemRenderer(this, name);
	}

	@Override
	public CTItem setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		rot++;
		if (rot > 360) {
			rot = 0;
		}
    }

}
