package thepurplepoe.creepertech.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thepurplepoe.api.items.ItemWrapper;
import thepurplepoe.creepertech.common.CreeperTech;

public class ItemTeleporter extends ItemWrapper {

	public ItemTeleporter(String name) {
		super(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (!worldIn.isRemote) {
			if (playerIn.world.provider.getDimension() != CreeperTech.creeperDimensionType.getId()) {
				playerIn.changeDimension(CreeperTech.creeperDimensionType.getId());
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
