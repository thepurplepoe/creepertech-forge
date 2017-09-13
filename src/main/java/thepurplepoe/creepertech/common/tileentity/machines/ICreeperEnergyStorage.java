package thepurplepoe.creepertech.common.tileentity.machines;

import net.minecraftforge.energy.IEnergyStorage;

public interface ICreeperEnergyStorage extends IEnergyStorage {
	public void setEnergyStored(int energy);
	public void setMaxEnergy(int energy);
	int receiveEnergy(int maxReceive);
	int extractEnergy(int maxExtract);
}

