package io.github.mosadie.ExponentialPower.TileEntitys.BaseClasses;

import io.github.mosadie.ExponentialPower.ModConfig;
import io.github.mosadie.ExponentialPower.energy.generator.ForgeEnergyConnection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import wolf.astell.choco.init.ItemList;

import javax.annotation.Nullable;


public class GeneratorTE extends TileEntity implements ITickable, IInventory, ICapabilityProvider {
	public double limit = Double.MAX_VALUE - ModConfig.GENERATOR_CONF.BASIC_ACCUMULATE - 1;
	public enum GeneratorTier {
		REGULAR,
	}
	public final GeneratorTier tier;

	public double currentOutput = 0;
	public double energy = 0;

	public NonNullList<ItemStack> Inventory = NonNullList.withSize(1, ItemStack.EMPTY);
	public String customName;

	private ForgeEnergyConnection fec;

	public GeneratorTE(GeneratorTier tier) {
		this.tier = tier;
		fec = new ForgeEnergyConnection(this, true, false);
	}

	@Override
	public boolean hasCapability(Capability<?> cap, @Nullable EnumFacing f) {
		return cap == CapabilityEnergy.ENERGY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> cap, @Nullable EnumFacing f) {
		if (cap == CapabilityEnergy.ENERGY) return (T) fec;
		return null;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

		ResourceLocation resourcelocation = new ResourceLocation("poweredbychoco:choco_generator_tile_entity");
		nbt.setString("id", resourcelocation.toString());
		nbt.setInteger("x", this.pos.getX());
		nbt.setInteger("y", this.pos.getY());
		nbt.setInteger("z", this.pos.getZ());
		nbt.setTag("ForgeData", this.getTileData());


		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.getSizeInventory(); ++i) {
			if (this.getStackInSlot(i) != null) {
				NBTTagCompound stackTag = new NBTTagCompound();
				stackTag.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(stackTag);
				list.appendTag(stackTag);
			}
		}
		nbt.setTag("Items", list);
		nbt.setDouble("currentOutput", currentOutput);

		if (this.hasCustomName()) {
			nbt.setString("CustomName", this.getCustomName());
		}

		return nbt;
	}


	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		NBTTagList list = nbt.getTagList("Items", 10);
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound stackTag = list.getCompoundTagAt(i);
			int slot = stackTag.getByte("Slot") & 255;
			this.setInventorySlotContents(slot, new ItemStack(stackTag));
		}
		currentOutput = nbt.getDouble("currentOutput");

		if (nbt.hasKey("CustomName", 8)) {
			this.setCustomName(nbt.getString("CustomName"));
		}
	}

	@Override
	public void update() {
		if (Inventory != null && Inventory.get(0).getItem() == ItemList.foodChocolate) {
			energy = currentOutput;
			if(world.getWorldTime() % 10 == 0){
				Inventory.get(0).shrink(1);
				currentOutput = calculateEnergy();
			}
			if (currentOutput == 0) currentOutput = 1;
		}
		else{
			if (currentOutput > 0 && world.getWorldTime() % 10 == 0){
				currentOutput --;
				energy = currentOutput;
			}
		}
		handleSendingEnergy();
	}

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.poweredbychoco_choco_generator_tile_entity";
	}

	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.equals("");
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory())
			return ItemStack.EMPTY;
		return this.Inventory.get(0);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.getStackInSlot(index) != null) {
			ItemStack itemstack;

			if (this.getStackInSlot(index).getCount() <= count) { //Inventory has less or same as amount asked for
				itemstack = this.getStackInSlot(index);
				//itemstack.shrink(1);
				this.setInventorySlotContents(index, ItemStack.EMPTY);
				this.markDirty();
				if (itemstack.getCount() > 0) return itemstack;
				else return ItemStack.EMPTY;
			} else { //More in slot than asked for
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).getCount() <= 0) {
					this.setInventorySlotContents(index, ItemStack.EMPTY);
				} else {
					//Just to show that changes happened
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return ItemStack.EMPTY;
		}
	}


	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= this.getSizeInventory())
			return;

		if (stack.getCount() > this.getInventoryStackLimit())
			stack.setCount(this.getInventoryStackLimit());

		if (stack.getCount() == 0)
			stack = ItemStack.EMPTY;

		this.Inventory.set(0, stack);
		this.markDirty();
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory())
			return ItemStack.EMPTY;
		ItemStack whatWasThere = this.Inventory.get(0);
		this.Inventory.set(0,ItemStack.EMPTY);
		this.markDirty();
		return whatWasThere;
	}

	@Override
	public int getInventoryStackLimit() {
		return getMaxStack();
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
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
		for (int i = 0; i < this.getSizeInventory(); i++)
			this.setInventorySlotContents(i, ItemStack.EMPTY);
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	private void handleSendingEnergy() {
		if (!world.isRemote) {
			if (energy <= 0) {
				return;
			}
			for (EnumFacing dir : EnumFacing.values()) {
				BlockPos targetBlock = getPos().add(dir.getDirectionVec());

				TileEntity tile = world.getTileEntity(targetBlock);
				if (tile != null) {
					if (tile instanceof StorageTE) {
						StorageTE storage = (StorageTE) tile;
						energy -= storage.acceptEnergy(energy);
					}
					else {
						if (tile.hasCapability(CapabilityEnergy.ENERGY, dir.getOpposite())) {
							IEnergyStorage other = tile.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite());
							assert other != null;
							if (other.canReceive()) {
								energy -= other.receiveEnergy((int) (energy > Integer.MAX_VALUE ? Integer.MAX_VALUE : energy), false);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public boolean isEmpty() {
		return Inventory.get(0).getCount() == 0;
	}

	public int getMaxStack() {
		return 64;
	}

	public double calculateEnergy() {
		int i;
		i = ModConfig.GENERATOR_CONF.BASIC_ACCUMULATE;
		if (ModConfig.GENERATOR_CONF.MAX_GEN < energy){
			return ModConfig.GENERATOR_CONF.MAX_GEN;
		}
		if (limit < energy){
			return energy;
		}
		return energy + i;
	}

}
