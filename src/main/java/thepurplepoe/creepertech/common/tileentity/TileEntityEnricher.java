package thepurplepoe.creepertech.common.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import thepurplepoe.creepertech.common.tileentity.machines.BaseMachine;
import thepurplepoe.creepertech.common.util.Helper;
import thepurplepoe.api.helper.NBTHelper;

public class TileEntityEnricher extends BaseMachine {
	
	public TileEntityEnricher() {
		super();
		this.setMaxEnergy(300000);
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
        if (this.hasWorld()) {
        	if (!this.getWorld().isRemote) {
        		this.receiveEnergy(200);
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
