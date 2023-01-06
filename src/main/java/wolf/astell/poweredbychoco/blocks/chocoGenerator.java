package wolf.astell.poweredbychoco.blocks;

import wolf.astell.poweredbychoco.Main;
import wolf.astell.poweredbychoco.items.itemList;
import wolf.astell.poweredbychoco.libs.libModInfo;
import wolf.astell.poweredbychoco.tile_entities.base.generatorTE;
import wolf.astell.poweredbychoco.handler.GUIHandler;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class chocoGenerator extends Block implements ITileEntityProvider {
	public chocoGenerator(String name) {
		super(blockManager.CommonMaterial);
		this.setUnlocalizedName(name);
        this.setRegistryName(name);
		this.setCreativeTab(itemList.CreativeTab);
		this.setHardness(2.5F);
		this.setResistance(6000f);
		this.hasTileEntity = true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new generatorTE();
    }
    
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
	        playerIn.openGui(Main.instance, libModInfo.MOD_GENERATOR_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
	    }
	    return true;
	}
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            ((generatorTE) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
    	generatorTE te = (generatorTE) world.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(world, pos, te);
        super.breakBlock(world, pos, state);
    }
}
