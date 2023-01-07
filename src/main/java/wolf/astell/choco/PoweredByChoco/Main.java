package wolf.astell.choco.PoweredByChoco;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import wolf.astell.choco.PoweredByChoco.proxy.CommonProxy;
import wolf.astell.choco.init.ItemList;

@Mod(modid = "poweredbychoco", name = "Powered by Choco", version = "1.0.0", dependencies = "required-after:choco@[1.0.0,)")
public class Main {
	public static final String MODID = "poweredbychoco";
	@Instance
	public static Main instance;
	public static CreativeTabs PoweredbyChoco = new CreativeTabs("PoweredbyChoco") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemList.flightChocolate);
		}
	};

	
	@SidedProxy(clientSide="wolf.astell.choco.PoweredByChoco.proxy.ClientProxy", serverSide="wolf.astell.choco.PoweredByChoco.proxy.ServerProxy")
	public static CommonProxy proxy;

	public Main() {
		instance = this;
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
}
