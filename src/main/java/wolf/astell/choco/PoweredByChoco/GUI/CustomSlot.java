package wolf.astell.choco.PoweredByChoco.GUI;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class CustomSlot extends Slot {

	private final int stackLimit;
	
	public CustomSlot(int stackLimit, IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.stackLimit = stackLimit;
	}

	@Override
	public int getSlotStackLimit() {
		return stackLimit;
		
	}
}
