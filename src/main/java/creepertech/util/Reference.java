package creepertech.util;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;

public class Reference {
	public static final String modID = "creepertech";
	public static final String modName = "CreeperTech";
	public static final String modVersion = "1.0.0a";
	public static final String modMinecraftVersion = "1.10.2";
	
	public static final int creeperDimensionID = 9;
	
	public static final String commonProxy = "creepertech.proxy.CommonProxy";
	public static final String clientProxy = "creepertech.client.proxy.ClientProxy";
}
