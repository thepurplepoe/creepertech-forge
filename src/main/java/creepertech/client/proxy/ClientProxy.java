package creepertech.client.proxy;

import creepertech.client.model.CTModelBaker;
import creepertech.client.renderer.entity.RenderCTCreeper;
import creepertech.entity.EntityCTCreeper;
import creepertech.entity.EntityNuclearCreeper;
import creepertech.proxy.CommonProxy;
import creepertech.util.Helper;
import creepertech.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerEntityRenderers() {
		Helper.WriteModMessage("Actually Registering Entities");
		RenderingRegistry.registerEntityRenderingHandler(EntityCTCreeper.class, new IRenderFactory<EntityCTCreeper>() {
			@Override
			public Render<EntityCTCreeper> createRenderFor(RenderManager manager)
			{
				return new RenderCTCreeper(manager, 1);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityNuclearCreeper.class, new IRenderFactory<EntityNuclearCreeper>() {
			@Override
			public Render<? super EntityNuclearCreeper> createRenderFor(RenderManager manager)
			{
				return new RenderCTCreeper(manager, 3);
			}
		});
	}
	
	@Override
	public void registerItemRenderer(Item item, String id) {	
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Reference.modID + ":" + id));
	}
	
	@Override
	public void registerClientEvents() {
		Helper.WriteModMessage("Actually Registering Baker");
		MinecraftForge.EVENT_BUS.register(new CTModelBaker());
	}
}
