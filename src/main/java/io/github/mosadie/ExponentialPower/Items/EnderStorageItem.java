package io.github.mosadie.ExponentialPower.Items;

import io.github.mosadie.ExponentialPower.ModConfig;
import io.github.mosadie.ExponentialPower.TileEntitys.BaseClasses.StorageTE;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class EnderStorageItem extends ItemBlock {

	public EnderStorageItem(Block block, StorageTE.StorageTier tier) {
		super(block);
		this.tier = tier;
		this.setCreativeTab(ItemManager.CreativeTab);
	}

	private final StorageTE.StorageTier tier;

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);

		double energy = 0;

		if (stack.hasTagCompound()) {
			NBTTagCompound blockEntityTag = stack.getSubCompound("BlockEntityTag");
			if (blockEntityTag.hasKey("energy")) {
				energy = blockEntityTag.getDouble("energy");
			}
		}
		
		tooltip.add("Current Energy Stored:");
		tooltip.add(energy + "/" + getMaxEnergy());
		double percent = ((int)(energy/getMaxEnergy() * 10000.00)) / 100.00;
		tooltip.add("(" + percent + "%)");
	}

	public double getMaxEnergy() {
		return ModConfig.CAPACITOR_CONF.MAX_STORE;
	}
}