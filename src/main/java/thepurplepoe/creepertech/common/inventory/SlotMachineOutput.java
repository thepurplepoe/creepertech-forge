package thepurplepoe.creepertech.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMachineOutput extends Slot {

	public SlotMachineOutput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

    public boolean isItemValid(ItemStack stack)
    {
        return false;
    }
}
