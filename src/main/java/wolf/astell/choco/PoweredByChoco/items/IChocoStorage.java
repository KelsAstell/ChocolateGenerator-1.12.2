package wolf.astell.choco.PoweredByChoco.items;

import java.util.List;

import javax.annotation.Nullable;

import wolf.astell.choco.PoweredByChoco.Main;
import wolf.astell.choco.PoweredByChoco.ModConfig;
import wolf.astell.choco.PoweredByChoco.tile.base.StorageTE;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class IChocoStorage extends ItemBlock {

	public IChocoStorage(Block block, StorageTE.StorageTier tier) {
		super(block);
		this.tier = tier;
		this.setCreativeTab(Main.PoweredbyChoco);
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
		
		tooltip.add(I18n.format("choco.gui.energy_stored"));
		tooltip.add(energy + "/" + getMaxEnergy());
		double percent = ((int)(energy/getMaxEnergy() * 10000.00)) / 100.00;
		tooltip.add("(" + percent + "%)");
	}

	public double getMaxEnergy() {
		return ModConfig.CAPACITOR_CONF.MAX_STORE;
	}
}