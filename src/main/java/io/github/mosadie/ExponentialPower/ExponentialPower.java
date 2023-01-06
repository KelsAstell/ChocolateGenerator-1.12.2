package io.github.mosadie.ExponentialPower;

import org.apache.logging.log4j.Logger;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "poweredbychoco", name = "Powered by Choco", version = "1.0.0", dependencies = "required-after:choco@[1.0.0,)")
public class ExponentialPower {
	public static final String MODID = "poweredbychoco";

	@Instance
	public static ExponentialPower instance;
	
	public static Logger LOGGER;
	
	@SidedProxy(clientSide="io.github.mosadie.ExponentialPower.ClientProxy", serverSide="io.github.mosadie.ExponentialPower.ServerProxy")
	public static CommonProxy proxy;

	public ExponentialPower() {
		MinecraftForge.EVENT_BUS.register(new EventReceiver());
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		LOGGER = e.getModLog();
		proxy.preInit(e);
		//Register GUI
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
		//Crafting
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
		//Don't Think I need this...
	}
}
