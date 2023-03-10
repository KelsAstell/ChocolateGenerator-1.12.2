package io.github.mosadie.ExponentialPower.GUIContainer;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class CustomStackLimitSlot extends Slot {

	private final int stackLimit;
	
	public CustomStackLimitSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.stackLimit = 64;
	}

	@Override
	public int getSlotStackLimit() {
		return stackLimit;
		
	}
}
