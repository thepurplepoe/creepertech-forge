package thepurplepoe.api;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thepurplepoe.api.blocks.BlockWrapper;
import thepurplepoe.api.helper.ModelHelper;
import thepurplepoe.api.items.ItemWrapper;

public class API {
	public static ModelHelper ModelHelper = new ModelHelper();
	
	public static <T extends ItemWrapper> T registerItem(T item) {
		GameRegistry.register(item);
		item.registerModel();
		return item;
	}
	
	public static <T extends BlockWrapper> T registerBlock(T block, ItemBlock itemBlock) {
		GameRegistry.register(block);
		GameRegistry.register(itemBlock);
		block.registerModel(itemBlock);
		return block;
	}
	
	public static <T extends BlockWrapper> T registerBlock(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return registerBlock(block, itemBlock);
	}
}
