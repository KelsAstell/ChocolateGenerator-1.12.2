package wolf.astell.choco.PoweredByChoco.blocks;

import net.minecraft.item.Item;
import wolf.astell.choco.PoweredByChoco.Main;
import wolf.astell.choco.PoweredByChoco.network.GUIHandler;
import wolf.astell.choco.PoweredByChoco.tile.base.GeneratorTE;
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
import wolf.astell.choco.PoweredByChoco.items.ItemList;


public class ChocoGenerator extends Block implements ITileEntityProvider {
	public ChocoGenerator() {
		super(BlockList.CommonMaterial);
		this.setUnlocalizedName("choco_generator");
        this.setCreativeTab(Main.PoweredbyChoco);
		this.setHardness(2.5F);
		this.setResistance(15f);
		this.hasTileEntity = true;



    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new GeneratorTE(GeneratorTE.GeneratorTier.REGULAR);
    }
    
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
	        playerIn.openGui(Main.instance, GUIHandler.GENERATOR_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
	    }
	    return true;
	}
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            ((GeneratorTE) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
    	GeneratorTE te = (GeneratorTE) world.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(world, pos, te);
        super.breakBlock(world, pos, state);
    }
}
