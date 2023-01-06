package wolf.astell.poweredbychoco.items;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wolf.astell.poweredbychoco.api.ModConfig;

import javax.annotation.Nullable;
import java.util.List;

public class IChocoCapacitor extends ItemBlock {

	public IChocoCapacitor(Block block) {
		super(block);
		this.setCreativeTab(itemList.CreativeTab);
	}


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
		if (ModConfig.CAPACITOR_CONFIG.MAXENERGY < Double.MAX_VALUE){
			return ModConfig.CAPACITOR_CONFIG.MAXENERGY;
		}
		return Double.MAX_VALUE;
	}
}