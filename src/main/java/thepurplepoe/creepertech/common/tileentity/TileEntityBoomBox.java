package thepurplepoe.creepertech.common.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityBoomBox extends TileEntity {
	protected int boompower = 0;
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("boompower", boompower);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		boompower = compound.getInteger("boompower");
		super.readFromNBT(compound);
	}
	
	public int getBoomPower() {
		return boompower;
	}
	
	public void incrementBoomPower() {
		boompower++;
		markDirty();
	}
	
	public void decrementBoomPower() {
		boompower--;
		markDirty();
	}
	
	public void boom(World world, EntityPlayer player, BlockPos pos, IBlockState state) {
		if (!world.isRemote) {
			world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), boompower, true);
		}

	}
}
