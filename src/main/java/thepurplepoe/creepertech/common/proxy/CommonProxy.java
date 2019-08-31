package thepurplepoe.creepertech.common.proxy;

import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;
import thepurplepoe.api.helper.Helper;
import thepurplepoe.creepertech.common.util.ModRef;

public class CommonProxy {
	public void registerEntityRenderers() {		
		Helper.WriteModMessage(ModRef.modId, "Bad Renderer Call");
	}

	public void registerItemRenderer(Item item, int meta, String name) {
	}
	
	public void registerItemRenderer(Item item, int meta, String name, String variant) {
	}
	
	public void registerClientEvents() {
	}

	public String localize(String unlocalized, Object... args) {
		return I18n.translateToLocalFormatted(unlocalized, args);
	}
}
