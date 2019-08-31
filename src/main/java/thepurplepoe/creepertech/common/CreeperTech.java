package thepurplepoe.creepertech.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.DimensionManager;
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
import thepurplepoe.creepertech.common.entity.EntityCTCreeper;
import thepurplepoe.creepertech.common.entity.EntityNuclearCreeper;
import thepurplepoe.creepertech.common.entity.EntityProjectileBase;
import thepurplepoe.creepertech.common.item.Items;
import thepurplepoe.creepertech.common.proxy.CommonProxy;
import thepurplepoe.creepertech.common.util.ModRef;
import thepurplepoe.creepertech.common.world.CTWorldProvider;
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
		
		proxy.registerEntityRenderers();
		
		proxy.registerClientEvents();
		
		// Initialize SoundHandler
		//SoundHandler.init();

	}
	
	/**
	 * Handles initialization
	 * i.e crafting recipes, world provider, renderers
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		// Register Entity Renderers

		BiomeEntry b = new BiomeEntry(CreeperForest, 1);
		BiomeManager.addBiome(BiomeType.COOL, b);
		BiomeManager.addSpawnBiome(CreeperForest);
		BiomeProvider.allowedBiomes.add(CreeperForest);
		// Register Biomes
		
		// Register Creeper Dimension and WorldProvider
		creeperDimensionType = DimensionType.register("CreeperDimension", "creeperdim", ModRef.creeperDimensionID, CTWorldProvider.class, false);
		DimensionManager.registerDimension(ModRef.creeperDimensionID, creeperDimensionType);
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
		public static void registerBiomes(RegistryEvent.Register<Biome> event) {
			//Biome.registerBiome(220, "CreeperForest", CreeperForest);
			event.getRegistry().register(CreeperForest);
		}
		
		@SubscribeEvent
		public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
			
			EntityEntry e = new EntityEntry(EntityProjectileBase.class, "ProjectileBase");
			EntityEntry creeper = new EntityEntry(EntityCTCreeper.class, "CreeperTechCreeper");
			EntityEntry nuclearcreeper = new EntityEntry(EntityNuclearCreeper.class, "NuclearCreeper");
			
			creeper.setRegistryName(ModRef.modId, "CreeperTechCreeper");
			nuclearcreeper.setRegistryName(ModRef.modId, "NuclearCreeper");
			e.setRegistryName(ModRef.modId, "ProjectileBase");
			
			EntityRegistry.registerModEntity(e.getRegistryName(), EntityProjectileBase.class, e.getName(), 5, CreeperTech.instance, 64, 1, true);
			EntityRegistry.registerModEntity(creeper.getRegistryName(), EntityCTCreeper.class, creeper.getName(), 3, CreeperTech.instance, 64, 1, true, 1, 0);
			EntityRegistry.registerModEntity(nuclearcreeper.getRegistryName(), EntityNuclearCreeper.class, nuclearcreeper.getName(), 3, CreeperTech.instance, 64, 1, true, 2, 0);
		}
		
	}
}