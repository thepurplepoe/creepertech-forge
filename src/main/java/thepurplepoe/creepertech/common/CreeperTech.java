package thepurplepoe.creepertech.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import thepurplepoe.creepertech.CreeperTechGUIHandler;
import thepurplepoe.creepertech.common.blocks.Blocks;
import thepurplepoe.creepertech.common.entity.EntityProjectileBase;
import thepurplepoe.creepertech.common.item.Items;
import thepurplepoe.creepertech.common.proxy.CommonProxy;
import thepurplepoe.creepertech.common.util.ModRef;
import thepurplepoe.creepertech.common.world.biome.BiomeCreeperForest;

@Mod(modid = ModRef.modId, name = ModRef.name, version = ModRef.version, acceptedMinecraftVersions = ModRef.acceptedMinecraftVersions)
public class CreeperTech {
	/** DimensionType for the Creeper Dimension */
	public static DimensionType creeperDimensionType;
	
	/** Biomes for the Creeper Dimension */
	public static final Biome CreeperForest = new BiomeCreeperForest(new BiomeProperties("CreeperForest").setBaseHeight(0.0F).setHeightVariation(0.2F));
	
	/** Items */

	
	/** Creative Tab */
	public static CreativeTabs creativeTab = new CreeperTechTab("Creeper Tech");

	
	/** The current instance of the mod */
	@Instance(ModRef.modId)
	public static CreeperTech instance;
	
	/** The proxy */
	@SidedProxy(clientSide = ModRef.clientProxy, serverSide = ModRef.commonProxy)
	public static CommonProxy proxy;
	
	/**
	 * Handles preinitialization 
	 * i.e Registering blocks, items, entities
	 */
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {		
		OBJLoader.INSTANCE.addDomain(ModRef.modId);
		
		// Initialize Items
		Items.setup();
		Blocks.setup();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new CreeperTechGUIHandler());
		
		//Potion.REGISTRY.register(150, new ResourceLocation("air_control"), new PotionAirControl(false, 2445989).setPotionName("effect.aircontrol").setBeneficial());
		
		proxy.registerClientEvents();
		
		// Initialize SoundHandler
		//SoundHandler.init();
		
		// Register Entities
		//EntityRegistry.registerModEntity(EntityCTCreeper.class, "CreeperTechCreeper", 3, this, 64, 1, true, 1, 0);
		//EntityRegistry.registerModEntity(EntityNuclearCreeper.class, "NuclearCreeper", 4, this, 64, 1, true, 2, 0);

	}
	
	/**
	 * Handles initialization
	 * i.e crafting recipes, world provider, renderers
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		// Register Entity Renderers
		proxy.registerEntityRenderers();

		
		// Register Biomes
		//Biome.registerBiome(220, "CreeperForest", CreeperForest);
		
		// Register Creeper Dimension and WorldProvider
		//creeperDimensionType = DimensionType.register("CreeperDimension", "creeperdim", ModRef.creeperDimensionID, CTWorldProvider.class, false);
		//DimensionManager.registerDimension(ModRef.creeperDimensionID, creeperDimensionType);
	}
	
	/**
	 * Handles postinitialization
	 * i.e. inter mod stuff
	 */
	@EventHandler 
	public void postinit(FMLPostInitializationEvent event) {
	}
	
	@EventBusSubscriber
	public static class Registration {
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			Items.register(event.getRegistry());
			Blocks.registerItemBlock(event.getRegistry());
		}
		
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			Blocks.register(event.getRegistry());
		}
		
		@SubscribeEvent
		public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
			EntityEntry e = new EntityEntry(EntityProjectileBase.class, "ProjectileBase3");
			e.setRegistryName("ProjectileBase2");
			EntityRegistry.registerModEntity(e.getRegistryName(), EntityProjectileBase.class, "ProjectileBase", 5, CreeperTech.instance, 64, 1, true);
			//event.getRegistry().registerAll(e);
		}
		
	}
}