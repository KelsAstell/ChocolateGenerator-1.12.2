/*
Licenced under the [Choco Licence] https://emowolf.fun/choco
So let's build something awesome from this!
Author: Kels_Astell
GitHub: https://github.com/KelsAstell
*/
package wolf.astell.poweredbychoco.api;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import wolf.astell.poweredbychoco.items.itemList;

import java.util.Objects;

@Mod.EventBusSubscriber
public class InWorldCrafting {
    @SubscribeEvent
    public static void playerLeftClickedBlock(PlayerInteractEvent.LeftClickBlock event) {
        ItemStack stack_mh = event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);
        ItemStack stack_oh = event.getEntityPlayer().getHeldItem(EnumHand.OFF_HAND);
        if(Objects.equals(stack_oh.getItem().getRegistryName(), new ResourceLocation("choco", "pickaxe_chocolate")) && Objects.equals(stack_mh.getItem().getRegistryName(), new ResourceLocation("choco", "enchanted_chocolate")) && isBlock("minecraft:redstone_lamp", event.getWorld().getBlockState(event.getPos()).getBlock())) {
            if(event.getSide() == Side.SERVER) {
                stack_mh.shrink(1);
                craftChocoCore(event);
                BlockPos pos = event.getPos();
                event.getWorld().playSound(null, pos, SoundEvents.BLOCK_METAL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                event.getWorld().playEvent(2001, pos, Block.getStateId(Blocks.REDSTONE_LAMP.getDefaultState()));
                event.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    private static boolean isBlock(String unlocalizedPath, Block block) {
        ResourceLocation loc = block.getRegistryName();
        String fullPath = loc.getResourceDomain() + ":" + loc.getResourcePath();
        return fullPath.equals(unlocalizedPath);
    }

    private static void craftChocoCore(PlayerInteractEvent.LeftClickBlock event) {
        Vec3d vector = event.getHitVec();
        EntityItem item = new EntityItem(event.getWorld(), vector.x, vector.y + 0.5D, vector.z, new ItemStack(itemList.chocoCore, 1));
        item.setDefaultPickupDelay();
        event.getWorld().spawnEntity(item);
    }
}