package wolf.astell.poweredbychoco.handler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wolf.astell.poweredbychoco.Main;
import wolf.astell.poweredbychoco.libs.libModInfo;

public class InitHandler {

	public InitHandler() {
	}
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		wolf.astell.poweredbychoco.Main.proxy.registerBlocks(event);
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		wolf.astell.poweredbychoco.Main.proxy.registerItems(event);
	}
	
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		Main.proxy.registerModels(event);
	}

	@SubscribeEvent
	public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(libModInfo.MODID)) {
			ConfigManager.sync(libModInfo.MODID, Config.Type.INSTANCE);
		}
	}
}
