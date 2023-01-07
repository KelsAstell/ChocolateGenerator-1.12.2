package wolf.astell.choco.PoweredByChoco.network;

import wolf.astell.choco.PoweredByChoco.GUI.GUIChocoGenerator;
import wolf.astell.choco.PoweredByChoco.GUI.ContainerChocoGenerator;
import wolf.astell.choco.PoweredByChoco.tile.base.GeneratorTE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {

	public static final int GENERATOR_GUI = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	if (ID == GENERATOR_GUI)
        	return new ContainerChocoGenerator(player.inventory, (GeneratorTE) world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GENERATOR_GUI)
        	return new GUIChocoGenerator(player.inventory, (GeneratorTE) world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }
}