package wolf.astell.poweredbychoco.tile_entities.base;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import wolf.astell.poweredbychoco.api.ModConfig;
import wolf.astell.poweredbychoco.energy.capacitorFE;
import wolf.astell.poweredbychoco.libs.libModInfo;

import javax.annotation.Nullable;
import java.util.EnumMap;

public class capacitorTE extends TileEntity implements ITickable {

	public double energy = 0;
	public EnumMap<EnumFacing,Boolean> canTransfer;

	private EnumMap<EnumFacing, capacitorFE> map;

	public capacitorTE() {
		canTransfer = new EnumMap<>(EnumFacing.class);
		map = new EnumMap<>(EnumFacing.class);
		for(EnumFacing dir : EnumFacing.values()) {
			map.put(dir, new capacitorFE(this, true, true, dir));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

		ResourceLocation resourcelocation = new ResourceLocation(libModInfo.MODID + (":storage_tile_entity"));
		nbt.setString("id", resourcelocation.toString());
		nbt.setInteger("x", this.pos.getX());
		nbt.setInteger("y", this.pos.getY());
		nbt.setInteger("z", this.pos.getZ());
		nbt.setTag("ForgeData", this.getTileData());

		nbt.setDouble("energy", energy);
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		energy = nbt.getDouble("energy");
	}

	@Override
	public boolean hasCapability(Capability<?> cap, @Nullable EnumFacing f) {
		return cap == CapabilityEnergy.ENERGY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> cap, @Nullable EnumFacing dir) {
		if (cap == CapabilityEnergy.ENERGY) return (T) map.get((dir != null) ? dir : EnumFacing.UP);
		return null;
	}

	@Override
	public void update() {
		if (energy > 0) handleSendingEnergy();
	}

	private void handleSendingEnergy() {
		if (!world.isRemote) {
			if (energy <= 0) {
				return;
			} 
			for (EnumFacing dir : EnumFacing.values()) {
				if (!canTransfer.containsKey(dir))
					canTransfer.put(dir, false);

				if (canTransfer.get(dir)) {
					canTransfer.put(dir, false);
					continue;
				}
				BlockPos targetBlock = getPos().add(dir.getDirectionVec());
				TileEntity tile = world.getTileEntity(targetBlock);
				if (tile != null) {
					if (tile instanceof capacitorTE) {
						capacitorTE storage = (capacitorTE) tile;
						double difference = storage.acceptEnergy(energy);
						energy -= difference;
						if (difference > 0) {
							canTransfer.put(dir, true);
						}
					}
					else {
						if (tile.hasCapability(CapabilityEnergy.ENERGY, dir.getOpposite())) {
							IEnergyStorage other = tile.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite());
							assert other != null;
							if (other.canReceive()) {
								int change = other.receiveEnergy((int) (energy > Integer.MAX_VALUE ? Integer.MAX_VALUE : energy), false);
								if (change > 0) {
									energy -= change;
									canTransfer.put(dir, true);
								}
							}
						}
					}
				}
			}
		}
	}

	public double getMaxEnergy() {
		if (ModConfig.CAPACITOR_CONFIG.MAXENERGY < Double.MAX_VALUE){
			return ModConfig.CAPACITOR_CONFIG.MAXENERGY;
		}
		return Double.MAX_VALUE;
	}
	public double acceptEnergy(double input) {
		if (energy >= getMaxEnergy() || input <= 0) {
			return 0;
		}
		if (energy + input > getMaxEnergy()) {
			energy = getMaxEnergy();
			return getMaxEnergy() - energy;
		}
		if (energy + input < 0) {
			energy = Double.MAX_VALUE;
			return Double.MAX_VALUE - energy;
		}
		energy += input;
		return input;
	}
}
