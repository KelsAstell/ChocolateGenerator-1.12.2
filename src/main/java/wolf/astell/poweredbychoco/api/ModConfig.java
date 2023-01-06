package wolf.astell.poweredbychoco.api;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RangeDouble;
import wolf.astell.poweredbychoco.libs.libModInfo;

@Config(modid = libModInfo.MODID, category = "")
public class ModConfig {
	@Config.LangKey("choco.capacitorConf")
	@Config.Comment("Chocolate Capacitor Settings")
	public static final CapacitorConfig CAPACITOR_CONFIG = new CapacitorConfig();

	public static class CapacitorConfig {
		@Config.LangKey("config.poweredbychoco.storage.max")
		@Comment({"The maximum power that can be stored in a capacitor."})
		@RangeDouble(min = 1, max = (double)Long.MAX_VALUE)
		@Config.RequiresMcRestart
		public double MAXENERGY = Long.MAX_VALUE;
	}

	@Config.LangKey("choco.generatorConf")
	@Config.Comment("Chocolate Generator Settings")
	public static final GeneratorConfig GENERATOR_CONFIG = new GeneratorConfig();

	public static class GeneratorConfig {
		@Config.LangKey("config.poweredbychoco.generate.max")
		@Comment({"The maximum power that can be generated in a generator."})
		@RangeDouble(min = 1, max = (double)Long.MAX_VALUE - 1)
		@Config.RequiresMcRestart
		public double MAXENERGY = Long.MAX_VALUE;

		@Config.LangKey("config.poweredbychoco.generate.per_chocolate")
		@Comment({"How much energy should one chocolate produce."})
		public int PER_CHOCOLATE = 2;
	}
}
