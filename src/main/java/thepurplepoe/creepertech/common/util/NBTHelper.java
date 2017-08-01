package thepurplepoe.creepertech.common.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {
	public static int GetInteger(ItemStack itemstack, String s)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        if (!itemstack.getTagCompound().hasKey(s))
        {
            SetInteger(itemstack, s, 0);
        }

        return itemstack.getTagCompound().getInteger(s);
    }
	
	public static float GetFloat(ItemStack itemstack, String s)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        if (!itemstack.getTagCompound().hasKey(s))
        {
            SetFloat(itemstack, s, 0);
        }

        return itemstack.getTagCompound().getFloat(s);
    }
	
	public static double GetDouble(ItemStack itemstack, String s)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        if (!itemstack.getTagCompound().hasKey(s))
        {
            SetDouble(itemstack, s, 0);
        }

        return itemstack.getTagCompound().getDouble(s);
    }
    
    public static void SetInteger(ItemStack itemstack, String s, int i)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        itemstack.getTagCompound().setInteger(s, i);
    }
    
    public static void SetFloat(ItemStack itemstack, String s, float i)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        itemstack.getTagCompound().setFloat(s, i);
    }
    
    public static void SetDouble(ItemStack itemstack, String s, double i)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        itemstack.getTagCompound().setDouble(s, i);
    }
    
    public static void IncrementInteger(ItemStack itemstack, String s, int increment) {
    	SetInteger(itemstack, s, GetInteger(itemstack, s) + increment);
    }
    
    public static void IncrementFloat(ItemStack itemstack, String s, float increment) {
    	SetFloat(itemstack, s, GetFloat(itemstack, s) + increment);
    }
    
    public static void IncrementDouble(ItemStack itemstack, String s, double increment) {
    	SetDouble(itemstack, s, GetDouble(itemstack, s) + increment);
    }
    
    public static boolean GetBoolean(ItemStack itemstack, String s)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        if (!itemstack.getTagCompound().hasKey(s))
        {
            SetBoolean(itemstack, s, false);
        }

        return itemstack.getTagCompound().getBoolean(s);
    }

    public static void SetBoolean(ItemStack itemstack, String s, boolean flag)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        itemstack.getTagCompound().setBoolean(s, flag);
    }
    
    public static String GetString(ItemStack itemstack, String s)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        if (!itemstack.getTagCompound().hasKey(s))
        {
            SetString(itemstack, s, "");
        } 

        return itemstack.getTagCompound().getString(s);
    }

    public static void SetString(ItemStack itemstack, String s, String flag)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        itemstack.getTagCompound().setString(s, flag);
    }
    
    public static NBTTagCompound getTagCompound(ItemStack itemstack, String s)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        if (!itemstack.getTagCompound().hasKey(s))
        {
            SetTagCompound(itemstack, s, new NBTTagCompound());
        }

        NBTTagCompound t = itemstack.getTagCompound().getCompoundTag(s);
        return t;
    }

    public static void SetTagCompound(ItemStack itemstack, String s, NBTTagCompound flag)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        itemstack.getTagCompound().setTag(s, flag);
    }
}
