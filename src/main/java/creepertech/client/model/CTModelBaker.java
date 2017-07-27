package creepertech.client.model;

import creepertech.CreeperTech;
import creepertech.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CTModelBaker {
	@SubscribeEvent
	public void onModelBakeEvent(ModelBakeEvent event) {
		ModelResourceLocation model = new ModelResourceLocation(Reference.modID + ":" + CreeperTech.ItemCTTest.name);
		Object object = event.getModelRegistry().getObject(model);
		if (object != null) {
			BakedModelCTGunManual customModel = new BakedModelCTGunManual();
			event.getModelRegistry().putObject(model, customModel);
		}
	}
}
