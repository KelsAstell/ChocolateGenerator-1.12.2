package wolf.astell.choco.PoweredByChoco.items;

import wolf.astell.choco.PoweredByChoco.blocks.BlockList;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;

import java.util.Objects;

public class ItemList {
	public static void createItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(BlockList.itemChocoGenerator, BlockList.itemChocoCapacitor, BlockList.itemEnforcedChocoGenerator);
	}
	public static void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(BlockList.itemChocoGenerator, 0, new ModelResourceLocation(Objects.requireNonNull(BlockList.chocoGenerator.getRegistryName()), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BlockList.itemChocoCapacitor, 0, new ModelResourceLocation(Objects.requireNonNull(BlockList.chocoCapacitor.getRegistryName()), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BlockList.itemEnforcedChocoGenerator, 0, new ModelResourceLocation(Objects.requireNonNull(BlockList.enforcedChocoGenerator.getRegistryName()), "inventory"));
	}
}
