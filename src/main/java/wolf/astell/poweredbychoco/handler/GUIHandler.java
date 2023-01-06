package wolf.astell.poweredbychoco.handler;

import wolf.astell.poweredbychoco.gui.GUISlot;
import wolf.astell.poweredbychoco.libs.libModInfo;
import wolf.astell.poweredbychoco.tile_entities.base.generatorTE;
import wolf.astell.poweredbychoco.gui.GUIBackground;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	if (ID == libModInfo.MOD_GENERATOR_GUI)
        	return new GUISlot(player.inventory, (generatorTE) world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == libModInfo.MOD_GENERATOR_GUI)
        	return new GUIBackground(player.inventory, (generatorTE) world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }
}