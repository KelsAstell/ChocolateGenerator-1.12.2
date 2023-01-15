/*
Licenced under the [Choco Licence] https://emowolf.fun/choco
So let's build something awesome from this!
Author: Kels_Astell
GitHub: https://github.com/KelsAstell
*/
package io.github.mosadie.ExponentialPower;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = "poweredbychoco", category = "")
public class ModConfig {
    @Mod.EventBusSubscriber(modid = "poweredbychoco")
    public static class ConfigSyncHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals("poweredbychoco")) {
                ConfigManager.sync("poweredbychoco", Config.Type.INSTANCE);
            }
        }
    }

    @Config.LangKey("choco.GeneratorConf")
    @Config.Comment("Choco Generator Settings")
    public static final GeneratorConf GENERATOR_CONF = new GeneratorConf();

    public static class GeneratorConf {
        @Config.LangKey("config.generator.max_output.name")
        @Config.Comment("Max output of the Annihilation Oven.")
        @Config.RangeDouble(min = 1, max = (double)Long.MAX_VALUE)
        public double MAX_GEN = Long.MAX_VALUE;

        @Config.LangKey("config.generator.basic_accumulate.name")
        @Config.Comment("Efficiency per chocolate for Choco Annihilation Oven, default 2")
        @Config.RangeInt(min = 0)
        public int BASIC_ACCUMULATE = 2;

    }
    @Config.LangKey("choco.CapacitorConf")
    @Config.Comment("Choco Capacitor Settings")
    public static final CapacitorConf CAPACITOR_CONF = new CapacitorConf();

    public static class CapacitorConf {
        @Config.LangKey("config.capacitor.max_storage.name")
        @Config.Comment("Max storage of the Capacitor")
        @Config.RangeDouble(min = 1, max = (double)Long.MAX_VALUE)
        @Config.RequiresMcRestart
        public double MAX_STORE = Long.MAX_VALUE;

        @Config.LangKey("config.capacitor.explosion.name")
        @Config.Comment("Will capacitor explode when it's too bright")
        @Config.RequiresMcRestart
        public boolean DO_EXPLODE = true;

    }
}

