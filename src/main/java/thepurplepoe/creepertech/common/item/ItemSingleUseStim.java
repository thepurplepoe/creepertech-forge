package thepurplepoe.creepertech.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thepurplepoe.api.items.ItemWrapper;
import thepurplepoe.creepertech.common.potion.PotionAirControl;

public class ItemSingleUseStim extends ItemWrapper {
	public static Potion airControl = new PotionAirControl(false, 2445989).setPotionName("effect.aircontrol").setBeneficial();
	
	PotionEffect effect;
	
	public ItemSingleUseStim(String name, String effectName) {
		super(name);
		setMaxStackSize(8);
		effect = new PotionEffect(airControl, 400, 15);
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;

        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode) {
            stack.setCount(stack.getCount() - 1);
        }

        if (!worldIn.isRemote) {
        	entityLiving.addPotionEffect(new PotionEffect(effect));
        	entityLiving.addPotionEffect(new PotionEffect(MobEffects.SPEED, 400, 10));
        	entityLiving.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 400, 10));
        }

        return stack;
    }

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
    
    public int getMaxItemUseDuration(ItemStack stack) {
        return 10;
    }
    
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BLOCK;
    }
}
