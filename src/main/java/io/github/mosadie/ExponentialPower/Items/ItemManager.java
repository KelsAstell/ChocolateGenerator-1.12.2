package io.github.mosadie.ExponentialPower.Items;

import io.github.mosadie.ExponentialPower.Blocks.BlockManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import wolf.astell.choco.init.ItemList;

public class ItemManager {
	
	public static final CreativeTabs CreativeTab = new CreativeTabs("PoweredbyChoco") {
	    @Override public ItemStack getTabIconItem() {
	        return new ItemStack(ItemList.flightChocolate);
	    }
	};
	public static void createItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(BlockManager.itemChocoGenerator, BlockManager.itemChocoCapacitor);
    }
	
	public static void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(BlockManager.itemChocoGenerator, 0, new ModelResourceLocation("choco_generator", "inventory"));
		ModelLoader.setCustomModelResourceLocation(BlockManager.itemChocoCapacitor, 0, new ModelResourceLocation("choco_capacitor", "inventory"));
	}
}
