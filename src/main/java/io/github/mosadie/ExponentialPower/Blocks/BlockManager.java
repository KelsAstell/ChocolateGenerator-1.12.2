package io.github.mosadie.ExponentialPower.Blocks;

import io.github.mosadie.ExponentialPower.Items.EnderStorageItem;
import io.github.mosadie.ExponentialPower.TileEntitys.BaseClasses.StorageTE.StorageTier;
import io.github.mosadie.ExponentialPower.TileEntitys.EnderGeneratorTE;
import io.github.mosadie.ExponentialPower.TileEntitys.EnderStorageTE;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockManager {
	public static Block chocoGenerator;
	public static Item itemChocoGenerator;
	public static Block chocoCapacitor;
	public static Item itemChocoCapacitor;

	public static final Material CommonMaterial = new Material(MapColor.BLUE);

	public static void createBlocks(RegistryEvent.Register<Block> event) {
		chocoGenerator = new EnderGenerator();
		chocoCapacitor = new EnderStorage();
		event.getRegistry().register(chocoGenerator.setRegistryName("choco_generator"));
		event.getRegistry().register(chocoCapacitor.setRegistryName("choco_capacitor"));
		GameRegistry.registerTileEntity(EnderGeneratorTE.class, new ResourceLocation("poweredbychoco:choco_generator_tile_entity"));
		GameRegistry.registerTileEntity(EnderStorageTE.class, new ResourceLocation("poweredbychoco:choco_capacitor_tile_entity"));
		itemChocoGenerator = new ItemBlock(chocoGenerator).setRegistryName("choco_generator");
		itemChocoCapacitor = new EnderStorageItem(chocoCapacitor, StorageTier.REGULAR).setRegistryName("choco_capacitor");
	}
}
