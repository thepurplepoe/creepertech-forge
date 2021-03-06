package thepurplepoe.creepertech.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.registry.RegistrySimple;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import thepurplepoe.api.API;
import thepurplepoe.api.blocks.BlockWrapper;
import thepurplepoe.creepertech.common.CreeperTech;

public class Blocks {
	public static RegistrySimple<String, BlockWrapper> Registry = new RegistrySimple<String, BlockWrapper>();
	
	public static BlockWrapper creeperiteBlock;
	public static BlockBoomBox boomBox;
	public static BlockEnricher enricher;
	public static BlockGenerator generator;

	public static void setup() {
		creeperiteBlock = new BlockWrapper(Material.ROCK, "creeperiteBlock");
		boomBox = new BlockBoomBox(Material.CARPET, "boomBox");
		enricher = new BlockEnricher(Material.ANVIL, "enricher");
		generator = new BlockGenerator(Material.ANVIL, "generator");
		
		Registry.putObject(creeperiteBlock.itemName, creeperiteBlock);
		Registry.putObject(boomBox.itemName, boomBox);
		Registry.putObject(enricher.itemName, enricher);
		Registry.putObject(generator.itemName, generator);
	}
	
	public static void register(IForgeRegistry<Block> registry) {
		for (BlockWrapper block : Registry) {
			block.setCreativeTab(CreeperTech.creativeTab);
			API.registerBlock(block, registry);
		}
		
		GameRegistry.registerTileEntity(boomBox.getTileEntityClass(), boomBox.getRegistryName().toString());
		GameRegistry.registerTileEntity(enricher.getTileEntityClass(), enricher.getRegistryName().toString());
		GameRegistry.registerTileEntity(generator.getTileEntityClass(), generator.getRegistryName().toString());
	}
	
	public static void registerItemBlock(IForgeRegistry<Item> registry) {
		for (BlockWrapper block : Registry) {
			API.registerItemBlock(block, block.createItemBlock().setRegistryName(block.getRegistryName()), registry);
		}
	}
}
