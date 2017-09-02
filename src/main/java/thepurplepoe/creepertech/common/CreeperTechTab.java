package thepurplepoe.creepertech.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreeperTechTab extends CreativeTabs {

	public CreeperTechTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Items.BAKED_POTATO);
	}

}
