package thepurplepoe.creepertech.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thepurplepoe.api.helper.Helper;
import thepurplepoe.api.items.ItemWrapper;
import thepurplepoe.creepertech.common.CreeperTech;
import thepurplepoe.creepertech.common.util.NBTHelper;
import thepurplepoe.creepertech.common.world.CTTeleporter;

public class ItemTeleporter extends ItemWrapper {

	public ItemTeleporter(String name) {
		super(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (!worldIn.isRemote) {
			ItemStack i = playerIn.getHeldItem(handIn);		
			boolean toorfrom = NBTHelper.GetBoolean(i, "toorfrom");
			if (playerIn.isSneaking()) {
				toorfrom = NBTHelper.ReturnBooleanAndToggle(i, "toorfrom");
				Helper.writeInChat("Going to dimension: " + toorfrom);
			} else {
				if (playerIn.world.provider.getDimension() != CreeperTech.creeperDimensionType.getId() && toorfrom) {
					worldIn.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, CreeperTech.creeperDimensionType.getId(), new CTTeleporter(((EntityPlayerMP) playerIn).getServerWorld()));
				} else if (playerIn.world.provider.getDimension() == CreeperTech.creeperDimensionType.getId() && !toorfrom) {
					worldIn.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, 0, new CTTeleporter(((EntityPlayerMP) playerIn).getServerWorld()));
				}
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
