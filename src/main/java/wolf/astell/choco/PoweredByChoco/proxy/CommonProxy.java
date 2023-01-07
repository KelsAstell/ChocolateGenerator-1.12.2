package wolf.astell.choco.PoweredByChoco.proxy;

import wolf.astell.choco.PoweredByChoco.Main;
import wolf.astell.choco.PoweredByChoco.network.GUIHandler;
import wolf.astell.choco.PoweredByChoco.blocks.BlockList;
import wolf.astell.choco.PoweredByChoco.items.ItemList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GUIHandler());
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
    
    public void registerBlocks(RegistryEvent.Register<Block> event) {
    	BlockList.createBlocks(event);
    }
    
    public void registerItems(RegistryEvent.Register<Item> event) {
    	ItemList.createItems(event);
    }
    
    public void registerModels(ModelRegistryEvent event) {
      ItemList.registerModels(event);
    }
}
