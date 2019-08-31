package thepurplepoe.creepertech.client.proxy;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import thepurplepoe.api.API;
import thepurplepoe.creepertech.client.model.ModelBaker;
import thepurplepoe.creepertech.client.renderer.entity.RenderCTCreeper;
import thepurplepoe.creepertech.client.renderer.entity.RenderProjectileBase;
import thepurplepoe.creepertech.common.entity.EntityCTCreeper;
import thepurplepoe.creepertech.common.entity.EntityNuclearCreeper;
import thepurplepoe.creepertech.common.entity.EntityProjectileBase;
import thepurplepoe.creepertech.common.proxy.CommonProxy;
import thepurplepoe.creepertech.common.util.ModRef;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerEntityRenderers() {
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
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileBase.class, new IRenderFactory<EntityProjectileBase>() {
			@Override
			public Render<EntityProjectileBase> createRenderFor(RenderManager manager)
			{
				return new RenderProjectileBase<EntityProjectileBase>(manager);
			}
		});
	}
	
	@Override
	public void registerItemRenderer(Item item, int meta, String name) {
		API.ModelHelper.setModelResourceLocation(item, meta, ModRef.modId, name);
	}
	
	@Override
	public void registerItemRenderer(Item item, int meta, String name, String variant) {
		API.ModelHelper.setModelResourceLocation(item, meta, ModRef.modId, name, variant);
	}
	
	@Override
	public void registerClientEvents() {
		MinecraftForge.EVENT_BUS.register(new ModelBaker());
	}
	
	@Override
	public String localize(String unlocalized, Object... args) {
		return I18n.format(unlocalized, args);
	}
}
