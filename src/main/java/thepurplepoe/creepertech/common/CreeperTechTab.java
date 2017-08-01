package thepurplepoe.creepertech.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreeperTechTab extends CreativeTabs {

	public CreeperTechTab(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return Items.BAKED_POTATO;
	}

}
