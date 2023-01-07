package wolf.astell.choco.PoweredByChoco.blocks;

import wolf.astell.choco.PoweredByChoco.items.IChocoStorage;
import wolf.astell.choco.PoweredByChoco.Main;
import wolf.astell.choco.PoweredByChoco.tile.EnforcedChocoGeneratorTE;
import wolf.astell.choco.PoweredByChoco.tile.ChocoGeneratorTE;
import wolf.astell.choco.PoweredByChoco.tile.ChocoStorageTE;
import wolf.astell.choco.PoweredByChoco.tile.base.StorageTE.StorageTier;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockList {
	public static Block chocoGenerator;
	public static Item itemChocoGenerator;
	public static Block enforcedChocoGenerator;
	public static Item itemEnforcedChocoGenerator;
	public static Block chocoCapacitor;
	public static Item itemChocoCapacitor;

	public static final Material CommonMaterial = new Material(MapColor.BLUE);

	public static void createBlocks(RegistryEvent.Register<Block> event) {
		chocoGenerator = new ChocoGenerator();
		enforcedChocoGenerator = new EnforcedChocoGenerator();
		chocoCapacitor = new ChocoCapacitor();
		event.getRegistry().register(chocoGenerator.setRegistryName("choco_generator"));
		event.getRegistry().register(enforcedChocoGenerator.setRegistryName("enforced_choco_generator"));
		event.getRegistry().register(chocoCapacitor.setRegistryName("choco_capacitor"));
		GameRegistry.registerTileEntity(ChocoGeneratorTE.class, new ResourceLocation("poweredbychoco" + ":choco_generator_tile_entity"));
		GameRegistry.registerTileEntity(EnforcedChocoGeneratorTE.class, new ResourceLocation("poweredbychoco" + ":enforced_choco_generator_tile_entity"));
		GameRegistry.registerTileEntity(ChocoStorageTE.class, new ResourceLocation("poweredbychoco" + ":choco_capacitor_tile_entity"));
		itemChocoGenerator = new ItemBlock(chocoGenerator).setRegistryName(chocoGenerator.getRegistryName());
		itemEnforcedChocoGenerator = new ItemBlock(enforcedChocoGenerator).setRegistryName(enforcedChocoGenerator.getRegistryName());
		itemChocoCapacitor = new IChocoStorage(chocoCapacitor, StorageTier.REGULAR).setRegistryName(chocoCapacitor.getRegistryName());
	}
}
