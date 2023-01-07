package wolf.astell.choco.PoweredByChoco;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {
	public EventHandler() {
	}
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		Main.proxy.registerBlocks(event);
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		Main.proxy.registerItems(event);
	}
	
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		Main.proxy.registerModels(event);
	}

}
