package thepurplepoe.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import thepurplepoe.api.blocks.BlockWrapper;
import thepurplepoe.api.helper.ModelHelper;
import thepurplepoe.api.items.ItemWrapper;

public class API {
	public static ModelHelper ModelHelper = new ModelHelper();
	
	public static <T extends ItemWrapper> T registerItem(T item, IForgeRegistry<Item> registry) {
		registry.register(item);
		item.registerModel();
		return item;
	}
	
	public static <T extends BlockWrapper> T registerBlock(T block, IForgeRegistry<Block> registry) {
		registry.register(block);
		return block;
	}
	
	public static <T extends BlockWrapper> T registerItemBlock(T block, Item itemBlock, IForgeRegistry<Item> registry) {
		registry.register(itemBlock);
		block.registerModel(itemBlock);
		return block;
	}
}
