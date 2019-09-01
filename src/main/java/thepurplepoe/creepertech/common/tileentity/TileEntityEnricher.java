package thepurplepoe.creepertech.common.tileentity;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import thepurplepoe.creepertech.common.tileentity.machines.BaseMachine;

public class TileEntityEnricher extends BaseMachine {
	boolean working = false;
	public int processtime = 60;
	int energypertick = 800;
	public int currentoperationtime = 0;
	
	public TileEntityEnricher() {
		super();
		this.setMaxEnergy(300000);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("currentoptime", currentoperationtime);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		currentoperationtime = compound.getInteger("currentoptime");
		super.readFromNBT(compound);
	}
	
	@Override
	public void update() {	
        if (this.hasWorld()) {
        	if (!this.getWorld().isRemote) {
        		this.receiveEnergy(200);
        		
        		if ((this.getStackInSlot(0).getItem() == Items.DIAMOND) && 
        				(this.getStackInSlot(1).getItem() == Items.GUNPOWDER) && 
        				(this.getStackInSlot(1).getCount() >= 8)) {
        			working = true;
        		} else {
        			working = false;
        			currentoperationtime = 0;
        		}
        		
        		if (working && this.getEnergyStored() >= energypertick) {
        			currentoperationtime++;
        			this.extractEnergy(energypertick);
        		}
        		
        		if (currentoperationtime >= processtime) {
        			this.getStackInSlot(0).shrink(1);
        			this.getStackInSlot(1).shrink(8);
        			if (this.getStackInSlot(2).getItem() == thepurplepoe.creepertech.common.item.Items.detoniteCrystal) {
        				this.getStackInSlot(2).grow(1);
        			} else {
        				this.setInventorySlotContents(2, new ItemStack(thepurplepoe.creepertech.common.item.Items.detoniteCrystal, 1));
        			}
        			currentoperationtime = 0;
        		}
        		
        		sendUpdates();
        		this.markDirty();
        	}
        }
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return null;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	@Override
	public String getMachineName() {
		return "encricher";
	}

	@Override
	public int getSizeInventory() {
		return 3;
	}
}
