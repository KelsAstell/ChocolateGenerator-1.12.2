package wolf.astell.poweredbychoco.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wolf.astell.poweredbychoco.items.IChocoCapacitor;
import wolf.astell.poweredbychoco.libs.libModInfo;
import wolf.astell.poweredbychoco.tile_entities.chocoCapacitorTE;
import wolf.astell.poweredbychoco.tile_entities.chocoGeneratorTE;

public class blockManager {
	public static Block chocoGenerator;
	public static Item itemChocoGenerator;
	public static Block chocoStorage;
	public static Item itemChocoCapacitor;

	public static final Material CommonMaterial = new Material(MapColor.BLUE);

	public static void createBlocks(RegistryEvent.Register<Block> event) {
		chocoGenerator = new chocoGenerator(libModInfo.CHOCO_GENERATOR);
		chocoStorage = new chocoCapacitor(libModInfo.CHOCO_CAPACITOR);
		GameRegistry.registerTileEntity(chocoGeneratorTE.class, new ResourceLocation(libModInfo.MODID + ":generator_tile_entity"));
		GameRegistry.registerTileEntity(chocoCapacitorTE.class, new ResourceLocation(libModInfo.MODID + ":storage_tile_entity"));
		itemChocoGenerator = new ItemBlock(chocoGenerator).setRegistryName(libModInfo.CHOCO_GENERATOR);
		itemChocoCapacitor = new IChocoCapacitor(chocoStorage).setRegistryName(libModInfo.CHOCO_CAPACITOR);
	}
}
