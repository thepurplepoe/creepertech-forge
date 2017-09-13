package thepurplepoe.creepertech.common.proxy;

import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;

public class CommonProxy {
	public void registerEntityRenderers() {		
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
