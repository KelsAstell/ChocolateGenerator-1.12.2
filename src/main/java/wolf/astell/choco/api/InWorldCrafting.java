package wolf.astell.choco.api;

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
import wolf.astell.choco.init.ItemList;
import wolf.astell.choco.init.ModConfig;

import java.util.Objects;

/**
 * Created by xt9 on 2018-05-11.
 */
@Mod.EventBusSubscriber
public class InWorldCrafting {
    @SubscribeEvent
    public static void playerLeftClickedBlock(PlayerInteractEvent.LeftClickBlock event) {
        ItemStack stack = event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);
        if(Objects.equals(stack.getItem().getRegistryName(), new ResourceLocation("choco", "chocolate")) && isBlock("minecraft:bed", event.getWorld().getBlockState(event.getPos()).getBlock())) {
            if(event.getSide() == Side.SERVER) {
                craftRecallChocolate(event);
                stack.shrink(1);
            }
        }
        if(Objects.equals(stack.getItem().getRegistryName(), new ResourceLocation("choco", "chocolate")) && isBlock("minecraft:gold_block", event.getWorld().getBlockState(event.getPos()).getBlock())) {
            if(event.getSide() == Side.SERVER) {
                craftGoldenChocolate(event);
                stack.shrink(1);
                if (shouldBreak(ModConfig.IN_WORLD_CRAFTING.GOLD_BREAK_CHANCE)){
                    BlockPos pos = event.getPos();
                    event.getWorld().playSound(null, pos, SoundEvents.BLOCK_METAL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    event.getWorld().playEvent(2001, pos, Block.getStateId(Blocks.GOLD_BLOCK.getDefaultState()));
                    event.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            }
        }
        if(Objects.equals(stack.getItem().getRegistryName(), new ResourceLocation("choco", "golden_chocolate")) && isBlock("minecraft:enchanting_table", event.getWorld().getBlockState(event.getPos()).getBlock())) {
            if(event.getSide() == Side.SERVER) {
                craftGoldenChocolate(event);
                stack.shrink(1);
                if (shouldBreak(ModConfig.IN_WORLD_CRAFTING.ENCHANT_BREAK_CHANCE)){
                    BlockPos pos = event.getPos();
                    event.getWorld().playSound(null, pos, SoundEvents.BLOCK_METAL_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    event.getWorld().playEvent(2001, pos, Block.getStateId(Blocks.ENCHANTING_TABLE.getDefaultState()));
                    event.getWorld().setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            }
        }
    }

    private static boolean shouldBreak(double chance) {
        double ran = Math.random();
        return ran < chance;
    }

    @SuppressWarnings("ConstantConditions")
    private static boolean isBlock(String unlocalizedPath, Block block) {
        ResourceLocation loc = block.getRegistryName();
        String fullPath = loc.getResourceDomain() + ":" + loc.getResourcePath();
        return fullPath.equals(unlocalizedPath);
    }

    private static void craftRecallChocolate(PlayerInteractEvent.LeftClickBlock event) {
        Vec3d vector = event.getHitVec();
        event.getWorld().playSound(null,vector.x, vector.y + 0.5D, vector.z, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS,1.0F,1.0F);
        EntityItem item = new EntityItem(event.getWorld(), vector.x, vector.y + 0.5D, vector.z, new ItemStack(ItemList.recallChocolate, 1));
        item.setDefaultPickupDelay();
        event.getWorld().spawnEntity(item);
    }

    private static void craftGoldenChocolate(PlayerInteractEvent.LeftClickBlock event) {
        Vec3d vector = event.getHitVec();
        EntityItem item = new EntityItem(event.getWorld(), vector.x, vector.y + 0.5D, vector.z, new ItemStack(ItemList.foodGoldenChocolate, 1));
        item.setDefaultPickupDelay();
        event.getWorld().spawnEntity(item);
    }
}