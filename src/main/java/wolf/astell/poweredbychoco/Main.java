package wolf.astell.poweredbychoco;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import wolf.astell.poweredbychoco.handler.InitHandler;
import wolf.astell.poweredbychoco.libs.libModInfo;
import wolf.astell.poweredbychoco.proxy.commonProxy;

@Mod(modid = "poweredbychocolate", name = "Powered by Choco", version = "1.0.0", dependencies = "required-after:choco@[1.0.0,)")
public class Main {
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide= libModInfo.CLIENT_PROXY, serverSide= libModInfo.SERVER_PROXY)
	public static commonProxy proxy;

	public Main() {
		MinecraftForge.EVENT_BUS.register(new InitHandler());
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);
	}
	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
}
