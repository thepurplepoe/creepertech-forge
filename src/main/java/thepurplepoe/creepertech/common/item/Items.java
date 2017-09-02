package thepurplepoe.creepertech.common.item;

import net.minecraft.item.Item;
import net.minecraft.util.registry.RegistrySimple;
import net.minecraftforge.registries.IForgeRegistry;
import thepurplepoe.api.API;
import thepurplepoe.api.items.ItemWrapper;
import thepurplepoe.creepertech.common.CreeperTech;

public class Items {
	public static RegistrySimple<String, ItemWrapper> Registry = new RegistrySimple<String, ItemWrapper>();
	
	public static ItemWrapper detoniteCrystal;
	public static ItemSingleUseStim speedStim;
	public static ItemGunBase m4a4;
	
	public static void setup() {
		detoniteCrystal = new ItemWrapper("detoniteCrystal");
		speedStim = new ItemSingleUseStim("speedStim", "air_control");
		m4a4 = new ItemGunBase("m4a4");
		
		Registry.putObject(detoniteCrystal.itemName, detoniteCrystal);
		Registry.putObject(speedStim.itemName, speedStim);
		Registry.putObject(m4a4.itemName, m4a4);
	}
	
	public static void register(IForgeRegistry<Item> registry) {
		for (ItemWrapper item : Registry) {
			item.setCreativeTab(CreeperTech.creativeTab);
			API.registerItem(item, registry);
		}
	}
}
