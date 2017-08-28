package thepurplepoe.api.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thepurplepoe.api.helper.NBTHelper;
import thepurplepoe.creepertech.common.CreeperTech;

public class ItemWrapper extends Item {
	
	public String itemName;
	
	public ItemWrapper(String name) {
		this.itemName = name;
		this.setUnlocalizedName(itemName);
		this.setRegistryName(itemName);
	}
	
	public ItemWrapper setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	public void registerModel() {
		CreeperTech.proxy.registerItemRenderer(this, 0, itemName);
	}
	
	public static void SetVariable(ItemStack stack, String variable, boolean flag) { 
		NBTHelper.setBoolean(stack, variable, flag);
	}
	
	public static void SetVariable(ItemStack stack, String variable, float flag) { 
		NBTHelper.setFloat(stack, variable, flag);
	}
	
	public static void SetVariable(ItemStack stack, String variable, int flag) { 
		NBTHelper.setInteger(stack, variable, flag);
	}
	
	public static void SetVariable(ItemStack stack, String variable, double flag) { 
		NBTHelper.setDouble(stack, variable, flag);
	}
	
	public static void SetVariable(ItemStack stack, String variable, String flag) { 
		NBTHelper.setString(stack, variable, flag);
	}
	
	public static void IncrementVariable(ItemStack stack, String variable, float flag) {
		NBTHelper.incrementFloat(stack, variable, flag);
	}	
	
	public static void IncrementVariable(ItemStack stack, String variable, double flag) {
		NBTHelper.incrementDouble(stack, variable, flag);
	}	
	
	public static void IncrementVariable(ItemStack stack, String variable, int flag) {
		NBTHelper.incrementInteger(stack, variable, flag);
	}
	
	public static boolean GetBooleanVariable(ItemStack stack, String variable) {
		return NBTHelper.getBoolean(stack, variable);
	}
	
	public static float GetFloatVariable(ItemStack stack, String variable) {
		return NBTHelper.getFloat(stack, variable);
	}
	
	public static double GetDoubleVariable(ItemStack stack, String variable) {
		return NBTHelper.getDouble(stack, variable);
	}
	
	public static int GetIntegerVariable(ItemStack stack, String variable) {
		return NBTHelper.getInteger(stack, variable);
	}
	
	public static String GetStringVariable(ItemStack stack, String variable) {
		return NBTHelper.getString(stack, variable);
	}
}
