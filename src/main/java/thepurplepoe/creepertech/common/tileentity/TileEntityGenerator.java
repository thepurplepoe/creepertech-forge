package thepurplepoe.creepertech.common.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import thepurplepoe.creepertech.common.blocks.Blocks;
import thepurplepoe.creepertech.common.tileentity.machines.BaseMachine;

public class TileEntityGenerator extends BaseMachine {
	World world;
	IBlockState block;
	
	public TileEntityGenerator() {
		super();
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
	}
	
	@Override
	public void update() {	
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return null;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return false;
	}

	@Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
		if (stack != null) {
			 return (stack.getItem().getUnlocalizedName() == Item.getItemFromBlock(Blocks.generator).getUnlocalizedName());
		}
		return false;
    }

	@Override
	public String getMachineName() {
		return "generator";
	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

}
