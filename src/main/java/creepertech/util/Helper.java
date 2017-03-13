package creepertech.util;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;

public class Helper {
	/**
	 * Converts the passed object to a string, and then prints it in chat
	 */
	public static void writeInChat(Object message) {
		Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(message.toString()));
	}
	
	/**
	 * Prints the passed object to the console with the mods name prefixed
	 */
    public static void WriteModMessage(String Message) {
    	System.out.print("[" + Reference.modID.toUpperCase() + "]: " + Message + "\n");
    }
	
    /** 
     * Uses FOV to zoom the game in an amount, i think 1 is normal
     */
    public static void Zoom(double amount) {
    	Minecraft.getMinecraft().gameSettings.fovSetting = (float)(amount * -1);
    }
    
	/** 
	 * Returns either 1 or -1
	 */
	public static int negativeOrPositive() {
		return 2 * (new Random().nextInt(2)) - 1;
	}
}
