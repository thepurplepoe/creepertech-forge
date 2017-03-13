package creepertech;

import creepertech.client.audio.SoundHandler;
import creepertech.entity.EntityCTCreeper;
import creepertech.entity.EntityNuclearCreeper;
import creepertech.item.CTItem;
import creepertech.proxy.CommonProxy;
import creepertech.util.Reference;
import creepertech.world.CTWorldProvider;
import creepertech.world.biome.BiomeCreeperForest;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.modID, name = Reference.modName, version = Reference.modVersion, acceptedMinecraftVersions = Reference.modMinecraftVersion)
public class CreeperTech {
	/** DimensionType for the Creeper Dimension */
	public static DimensionType creeperDimensionType;
	
	/** Biomes for the Creeper Dimension */
	public static final Biome CreeperForest = new BiomeCreeperForest(new BiomeProperties("CreeperForest").setBaseHeight(0.0F).setHeightVariation(0.2F));
	
	/** Items */
	public static CTItem ItemCTTest;
	
	/** Creative Tab */
	public static CreativeTabs tabCT = new CreativeTabs("CreeperTech") {
        public Item getTabIconItem() {
                return Items.BAKED_POTATO;        }
    };
	
	/** The current instance of the mod */
	@Instance(Reference.modID)
	public static CreeperTech instance;
	
	/** The proxy */
	@SidedProxy(clientSide = "creepertech.client.proxy.ClientProxy", serverSide = "creepertech.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	/**
	 * Handles preinitialization 
	 * i.e Registering blocks, items, entities
	 */
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {		
		// Initialize Items
		ItemCTTest = new CTItem("cttest");
		ItemCTTest.setCreativeTab(tabCT);
		GameRegistry.register(ItemCTTest);
		ItemCTTest.registerItemModel();
		

		
		proxy.registerClientEvents();
		
		// Initialize SoundHandler
		SoundHandler.init();
		
		// Register Entities
		EntityRegistry.registerModEntity(EntityCTCreeper.class, "CreeperTechCreeper", 3, this, 64, 1, true, 1, 0);
		EntityRegistry.registerModEntity(EntityNuclearCreeper.class, "NuclearCreeper", 4, this, 64, 1, true, 2, 0);
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
		Biome.registerBiome(220, "CreeperForest", CreeperForest);
		
		// Register Creeper Dimension and WorldProvider
		creeperDimensionType = DimensionType.register("CreeperDimension", "creeperdim", Reference.creeperDimensionID, CTWorldProvider.class, false);
		DimensionManager.registerDimension(Reference.creeperDimensionID, creeperDimensionType);
	}
	
	/**
	 * Handles postinitialization
	 * i.e. inter mod stuff
	 */
	@EventHandler 
	public void postinit(FMLPostInitializationEvent event) {
	}
}