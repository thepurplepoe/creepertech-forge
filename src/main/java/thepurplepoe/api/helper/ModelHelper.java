package thepurplepoe.api.helper;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ModelHelper {
	public ModelHelper() {
	}
	
	public void setModelResourceLocation(Item item, int meta, String modID, String name, String variant) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(modID + ":" + name, variant));
	}
	
	public void setModelResourceLocation(Item item, int meta, String modID, String name) {
		setModelResourceLocation(item, meta, modID, name, "inventory");
	}
}
