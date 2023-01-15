package io.github.mosadie.ExponentialPower.TileEntitys.BaseClasses;

import io.github.mosadie.ExponentialPower.ExponentialPower;
import io.github.mosadie.ExponentialPower.ModConfig;
import io.github.mosadie.ExponentialPower.energy.storage.ForgeEnergyConnection;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.EnumMap;

public class StorageTE extends TileEntity implements ITickable {

	public static enum StorageTier {
		REGULAR,
	}
	public final StorageTier tier;

	public double energy = 0;
	public EnumMap<EnumFacing,Boolean> freezeExpend;

	private EnumMap<EnumFacing,ForgeEnergyConnection> fec;

	public StorageTE(StorageTier storageTier) {
		tier = storageTier;
		freezeExpend = new EnumMap<EnumFacing,Boolean>(EnumFacing.class);
		fec = new EnumMap<EnumFacing,ForgeEnergyConnection>(EnumFacing.class);
		for(EnumFacing dir : EnumFacing.values()) {
			fec.put(dir, new ForgeEnergyConnection(this, true, true, dir));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

		//super.writeToNBT recreated and modified here.
		ResourceLocation resourcelocation = new ResourceLocation(ExponentialPower.MODID + (tier == StorageTier.REGULAR ? ":choco_capacitor_tile_entity" : ":advancedchoco_capacitor_tile_entity"));
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
		if (cap == CapabilityEnergy.ENERGY) return (T) fec.get((dir != null) ? dir : EnumFacing.UP);
		return null;
	}

	@Override
	public void update() {
		if (world.getWorldTime() % 20 == 0){
			if (ModConfig.CAPACITOR_CONF.DO_EXPLODE && this.getWorld().canSeeSky(this.getPos()) && this.getWorld().getLight(this.getPos())>=11){//1000%!
				if (world.getWorldTime() % 600 == 0){
					this.getWorld().createExplosion(null, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 2.8F, true);
				}
				if (world.getWorldTime() % 20 == 0){
					this.getWorld().spawnParticle(EnumParticleTypes.SMOKE_LARGE,this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 0,1,0);
				}
			}
		}
		if (energy > 0) handleSendingEnergy();
	}

	private void handleSendingEnergy() {
		if (!world.isRemote) {
			if (energy <= 0) {
				return;
			} 
			for (EnumFacing dir : EnumFacing.values()) {
				if (!freezeExpend.containsKey(dir))
					freezeExpend.put(dir, false);

				if (freezeExpend.get(dir)) {
					freezeExpend.put(dir, false);
					continue;
				}
				BlockPos targetBlock = getPos().add(dir.getDirectionVec());
				TileEntity tile = world.getTileEntity(targetBlock);
				if (tile != null) {
					if (tile instanceof StorageTE) {
						StorageTE storage = (StorageTE) tile;
						double difference = storage.acceptEnergy(energy);
						energy -= difference;
						if (difference > 0) {
							freezeExpend.put(dir, true);
						}
					}
					else {
						if (tile.hasCapability(CapabilityEnergy.ENERGY, dir.getOpposite())) {
							IEnergyStorage other = tile.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite());
							if (other.canReceive()) {
								int change = other.receiveEnergy((int) (energy > Integer.MAX_VALUE ? Integer.MAX_VALUE : energy), false);
								if (change > 0) {
									energy -= change;
									freezeExpend.put(dir, true);
								}
							}
						}
					}
				}
			}
		}
	}

	public double getMaxEnergy() {
		return ModConfig.CAPACITOR_CONF.MAX_STORE;
	}

	public double acceptEnergy(double energyOffered) {
		if (energy >= getMaxEnergy() || energyOffered <= 0) {
			return 0;
		}

		if (energy + energyOffered > getMaxEnergy()) {
			double amountAccepted = getMaxEnergy() - energy;
			energy = getMaxEnergy();
			return amountAccepted;
		}

		if (energy + energyOffered < 0) {
			double amountAccepted = Double.MAX_VALUE - energy;
			energy = Double.MAX_VALUE;
			return amountAccepted;
		}

		energy += energyOffered;
		return energyOffered;
	}
}
