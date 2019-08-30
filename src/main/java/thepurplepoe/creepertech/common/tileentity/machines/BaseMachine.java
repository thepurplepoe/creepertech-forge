package thepurplepoe.creepertech.common.tileentity.machines;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public abstract class BaseMachine extends TileEntity implements ISidedInventory, ITickable, ICreeperEnergyStorage {
	private int energyStored = 0;
	private int energyMax = 0;
	
	public IBlockState block;
	
	private String machineCustomName;
	
	private NonNullList<ItemStack> machineItemStacks;
	
	public BaseMachine() {
		machineItemStacks = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);
	}
	
	public abstract String getMachineName();

	public abstract int getSizeInventory();

	@Override
	public boolean isEmpty() {
		   for (ItemStack itemstack : this.machineItemStacks)
	        {
	            if (!itemstack.isEmpty())
	            {
	                return false;
	            }
	        }

	        return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return machineItemStacks.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(machineItemStacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(machineItemStacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
        machineItemStacks.set(index, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		machineItemStacks.clear();
	}

	@Override
	public String getName() {
		return this.hasCustomName() ? this.machineCustomName : "container." + getMachineName();
	}

	@Override
	public boolean hasCustomName() {
		return this.machineCustomName != null && !this.machineCustomName.isEmpty();
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.machineItemStacks);
        compound.setInteger("energy", energyStored);
        compound.setInteger("maxenergy", energyMax);
		return compound;
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound nbt) {
		super.handleUpdateTag(nbt);
		readFromNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
        this.machineItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.machineItemStacks);
        energyStored = compound.getInteger("energy");
        energyMax = compound.getInteger("maxenergy");
		
	}
	
	public void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		world.scheduleBlockUpdate(pos, this.getBlockType(), 0, 0);
		markDirty();
	}
	
	@Override 
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		handleUpdateTag(pkt.getNbtCompound());
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
       if (!canReceive())
            return 0;

        int energyReceived = Math.min(energyMax - energyStored, maxReceive);
        if (!simulate)
            energyStored += energyReceived;
        return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
        if (!canExtract())
            return 0;

        int energyExtracted = Math.min(energyStored, maxExtract);
        if (!simulate)
            energyStored -= energyExtracted;
        return energyExtracted;
	}

	@Override
	public int getEnergyStored() {
		return energyStored;
	}

	@Override
	public int getMaxEnergyStored() {
		return energyMax;
	}

	@Override
	public boolean canExtract() {
		return energyStored > 0;
	}

	@Override
	public boolean canReceive() {
		return energyStored < energyMax;
	}

	@Override
	public void setEnergyStored(int energy) {
		energyStored = (energy > energyMax) ? energyMax : energy;
	}
	
	public void setMaxEnergy(int max) {
		energyMax = max;
	}

	@Override
	public int receiveEnergy(int maxReceive) {
		return receiveEnergy(maxReceive, false);
	}

	@Override
	public int extractEnergy(int maxExtract) {
		return extractEnergy(maxExtract, false);
	}
}
