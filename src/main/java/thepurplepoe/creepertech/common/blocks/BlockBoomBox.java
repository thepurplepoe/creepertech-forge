package thepurplepoe.creepertech.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import thepurplepoe.api.tileentity.TileEntityWrapper;
import thepurplepoe.creepertech.common.tileentity.TileEntityBoomBox;
import thepurplepoe.creepertech.common.util.Helper;

public class BlockBoomBox extends TileEntityWrapper<TileEntityBoomBox> {

	public BlockBoomBox(Material material, String name) {
		super(material, name);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			TileEntityBoomBox tile = getTileEntity(world, pos);
			if (player.isSneaking()) {
				tile.incrementBoomPower();;
			} else {
				tile.boom(world, player, pos, state);
			}
			Helper.writeInChat("Count: " + tile.getBoomPower());
		}
		return true;
	}

	@Override
	public Class<TileEntityBoomBox> getTileEntityClass() {
		return TileEntityBoomBox.class;
	}

	@Override
	public TileEntityBoomBox createTileEntity(World world, IBlockState state) {
		return new TileEntityBoomBox();
	}
}
