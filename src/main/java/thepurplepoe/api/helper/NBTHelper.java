package thepurplepoe.api.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {
	public static int getInteger(ItemStack itemstack, String s)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        if (!itemstack.getTagCompound().hasKey(s))
        {
            setInteger(itemstack, s, 0);
        }

        return itemstack.getTagCompound().getInteger(s);
    }
	
	public static int getInteger(NBTTagCompound tag, String s)
    {
        if (tag == null)
        {
            tag = new NBTTagCompound();
        }

        if (!tag.hasKey(s))
        {
            setInteger(tag, s, 0);
        }

        return tag.getInteger(s);
    }
	
	public static float getFloat(ItemStack itemstack, String s)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        if (!itemstack.getTagCompound().hasKey(s))
        {
            setFloat(itemstack, s, 0);
        }

        return itemstack.getTagCompound().getFloat(s);
    }
	
	public static double getDouble(ItemStack itemstack, String s)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        if (!itemstack.getTagCompound().hasKey(s))
        {
            setDouble(itemstack, s, 0);
        }

        return itemstack.getTagCompound().getDouble(s);
    }
    
    public static void setInteger(ItemStack itemstack, String s, int i)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        itemstack.getTagCompound().setInteger(s, i);
    }
    
    public static void setInteger(NBTTagCompound tag, String s, int i)
    {
        if (tag == null)
        {
            tag = new NBTTagCompound();
        }

        tag.setInteger(s, i);
    }
    
    public static void setFloat(ItemStack itemstack, String s, float i)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        itemstack.getTagCompound().setFloat(s, i);
    }
    
    public static void setDouble(ItemStack itemstack, String s, double i)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        itemstack.getTagCompound().setDouble(s, i);
    }
    
    public static void incrementInteger(ItemStack itemstack, String s, int increment) {
    	setInteger(itemstack, s, getInteger(itemstack, s) + increment);
    }
    
    public static void incrementFloat(ItemStack itemstack, String s, float increment) {
    	setFloat(itemstack, s, getFloat(itemstack, s) + increment);
    }
    
    public static void incrementDouble(ItemStack itemstack, String s, double increment) {
    	setDouble(itemstack, s, getDouble(itemstack, s) + increment);
    }
    
    public static boolean getBoolean(ItemStack itemstack, String s)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        if (!itemstack.getTagCompound().hasKey(s))
        {
            setBoolean(itemstack, s, false);
        }

        return itemstack.getTagCompound().getBoolean(s);
    }

    public static void setBoolean(ItemStack itemstack, String s, boolean flag)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        itemstack.getTagCompound().setBoolean(s, flag);
    }
    
    public static String getString(ItemStack itemstack, String s)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        if (!itemstack.getTagCompound().hasKey(s))
        {
            setString(itemstack, s, "");
        } 

        return itemstack.getTagCompound().getString(s);
    }

    public static void setString(ItemStack itemstack, String s, String flag)
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
            setTagCompound(itemstack, s, new NBTTagCompound());
        }

        NBTTagCompound t = itemstack.getTagCompound().getCompoundTag(s);
        return t;
    }

    public static void setTagCompound(ItemStack itemstack, String s, NBTTagCompound flag)
    {
        if (itemstack.getTagCompound() == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        itemstack.getTagCompound().setTag(s, flag);
    }
}
