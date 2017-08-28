package thepurplepoe.creepertech.client.model;

import codechicken.lib.texture.TextureUtils;
import codechicken.lib.util.TransformUtils;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thepurplepoe.api.items.ICustomModelledItem;
import thepurplepoe.api.items.ItemWrapper;
import thepurplepoe.creepertech.common.item.Items;
import thepurplepoe.creepertech.common.util.ModRef;

@SideOnly(Side.CLIENT)
public class ModelBaker {
	@SubscribeEvent
	public void onModelBakeEvent(ModelBakeEvent event) {	
		for (ItemWrapper item : Items.Registry) {
			if (!(item instanceof ICustomModelledItem)) {
				continue;
			}
			
			ICustomModelledItem modelledItem = (ICustomModelledItem)item;
			ModelResourceLocation modelLocation = new ModelResourceLocation(ModRef.modId + ":" + modelledItem.getItemName(), "inventory");
			Object model2D = event.getModelRegistry().getObject(modelLocation);
			if (model2D != null) {
				try {
					IBakedModel model3D = OBJLoader.INSTANCE.loadModel(new ResourceLocation(ModRef.modId + ":models/item/" + modelledItem.getItemName() + "3d.obj")).bake(TransformUtils.DEFAULT_ITEM, DefaultVertexFormats.ITEM, TextureUtils.bakedTextureGetter);
					IBakedModel modelSilencer = OBJLoader.INSTANCE.loadModel(new ResourceLocation(ModRef.modId + ":models/item/m4a4silencer.obj")).bake(TransformUtils.DEFAULT_ITEM, DefaultVertexFormats.ITEM, TextureUtils.bakedTextureGetter);
					PerspectiveModel finalModel = new PerspectiveModel((IBakedModel)model2D, model3D, modelledItem.getItemName(), modelSilencer);
					event.getModelRegistry().putObject(modelLocation, finalModel);
				} catch (Exception e) {
					e.printStackTrace();
				}
						
			}
		}
	}
}
