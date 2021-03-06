package thepurplepoe.creepertech.client.audio;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import thepurplepoe.creepertech.common.util.ModRef;

public class SoundHandler {
	
	private static int size = 0;
	
	public static SoundEvent CENASTART;
	public static SoundEvent CENAEND;
	
	public static void init() {
		size = SoundEvent.REGISTRY.getKeys().size();
		
		CENASTART = register("creeper.cenastart");
		CENAEND = register("creeper.cenaend");
	}
	
	public static SoundEvent register(String name) {
		ResourceLocation location = new ResourceLocation(ModRef.modId, name);
		SoundEvent e = new SoundEvent(location);
		
		SoundEvent.REGISTRY.register(size, location, e);
		size++;
		//Utils.getLogger().info("Registered sound: " + name);
		return e;
	}

}