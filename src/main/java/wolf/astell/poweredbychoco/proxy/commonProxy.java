package wolf.astell.poweredbychoco.proxy;

import wolf.astell.poweredbychoco.Main;
import wolf.astell.poweredbychoco.blocks.blockManager;
import wolf.astell.poweredbychoco.items.itemList;
import wolf.astell.poweredbychoco.handler.GUIHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class commonProxy {
	public void preInit(FMLPreInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GUIHandler());
    }
    public void init(FMLInitializationEvent e) {
    }
    public void postInit(FMLPostInitializationEvent e) {

    }
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        blockManager.createBlocks(event);
    }
    
    public void registerItems(RegistryEvent.Register<Item> event) {
        itemList.createItems(event);
    }
    
    public void registerModels(ModelRegistryEvent event) {
        itemList.registerModels(event);
    }
}
