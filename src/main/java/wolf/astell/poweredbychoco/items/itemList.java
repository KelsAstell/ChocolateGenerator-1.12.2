package wolf.astell.poweredbychoco.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import wolf.astell.poweredbychoco.blocks.blockManager;
import wolf.astell.poweredbychoco.libs.libModInfo;

public class itemList {
	public static Item chocoCore;
	
	public static final CreativeTabs CreativeTab = new CreativeTabs("poweredbychoco") {
	    @Override public ItemStack getTabIconItem() {
	        return new ItemStack(chocoCore);
	    }
	};
	public static void createItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(chocoCore = new chocoCore(libModInfo.CHOCO_CORE));
		event.getRegistry().registerAll(blockManager.itemChocoGenerator, blockManager.itemChocoCapacitor);
    }
	
	public static void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(chocoCore, 0, new ModelResourceLocation(libModInfo.CHOCO_CORE, "inventory"));
		ModelLoader.setCustomModelResourceLocation(blockManager.itemChocoGenerator, 0, new ModelResourceLocation(libModInfo.CHOCO_GENERATOR, "inventory"));
		ModelLoader.setCustomModelResourceLocation(blockManager.itemChocoCapacitor, 0, new ModelResourceLocation(libModInfo.CHOCO_CAPACITOR, "inventory"));
	}
}
