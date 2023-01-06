package wolf.astell.poweredbychoco.energy;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.IEnergyStorage;
import wolf.astell.poweredbychoco.tile_entities.base.capacitorTE;

public class capacitorFE implements IEnergyStorage{
	
	private capacitorTE owner;
	private final boolean canExtract;
	private final boolean canReceive;
	private final EnumFacing direction;
	
	public capacitorFE(capacitorTE owner, boolean canExtract, boolean canReceive, EnumFacing dir) {
		this.owner = owner;
		this.canExtract = canExtract;
		this.canReceive = canReceive;
		direction = dir;
	}

	@Override
	public int receiveEnergy(int maxRecv, boolean simulate) {
		if (owner.energy >= owner.getMaxEnergy()) {
			return 0;
		}
		if ((owner.energy + maxRecv) < owner.energy ) {
			int temp = (int) (Double.MAX_VALUE - owner.energy);
			owner.energy = Double.MAX_VALUE;
			owner.markDirty();
			owner.canTransfer.put(direction, true);
			return temp;
		} else {
			owner.energy += maxRecv;
			owner.canTransfer.put(direction, true);
			owner.markDirty();
			return maxRecv;
		}
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		if (owner.energy <= 0) return 0;
		if (owner.energy <= maxExtract) {
			int temp = (int) owner.energy;
			if (!simulate) owner.energy = 0;
			owner.markDirty();
			return temp;
		}
		else {
			if (!simulate) owner.energy -= maxExtract;
			owner.markDirty();
			return maxExtract;
		}

	}

	@Override
	public int getEnergyStored() {
		return (int) (owner.energy > Integer.MAX_VALUE ? Integer.MAX_VALUE : owner.energy);
	}

	@Override
	public int getMaxEnergyStored() {
		return (owner.getMaxEnergy() > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)owner.getMaxEnergy());
	}

	@Override
	public boolean canExtract() {
		return canExtract;
	}

	@Override
	public boolean canReceive() {
		return canReceive;
	}
}
