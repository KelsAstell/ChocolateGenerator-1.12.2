package io.github.mosadie.ExponentialPower;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeDouble;

@Config(modid = ExponentialPower.MODID, category = "")
public class ConfigHandler {

	@Config.LangKey("config.endergenerator")
	public static ConfigEnderGenerator ender_generator = new ConfigEnderGenerator();


	@Config.LangKey("config.enderstorage")
	public static ConfigEnderStorage ender_storage = new ConfigEnderStorage();

	public static class ConfigEnderGenerator {

		public static class AdvancedGenerator {
			@Config.LangKey("config.acc.per_chocolate")
			@Comment({"Set accumulation per chocolate."})
			public int ACCUMULATION = 5;
		}

		public static class RegularGenerator {
			@Config.LangKey("config.acc.per_chocolate")
			@Comment({"Set accumulation per chocolate."})
			public int ACCUMULATION = 2;

			@Config.LangKey("config.output")
			@Comment({"Set max output."})
			@RangeDouble(min = 1, max = (double)Long.MAX_VALUE)
			public double MAX_OUTPUT = Long.MAX_VALUE;

		}

		public AdvancedGenerator Advanced = new AdvancedGenerator();
		public RegularGenerator Regular = new RegularGenerator();
	}

	public static class ConfigEnderStorage {

		public static class RegularStorage {
			@Config.LangKey("config.max_energy")
			@Comment({"The maximum amount of power that can be stored in a single Ender Storage block."})
			@RangeDouble(min = 1, max = (double)Long.MAX_VALUE)
			@Config.RequiresMcRestart
			public double MAXENERGY = Long.MAX_VALUE;
		}

		public static class AdvancedStorage {
			@Config.LangKey("config.max_energy")
			@Comment({"The maximum amount of power that can be stored in a single Advanced Ender Storage block."})
			@RangeDouble(min = 1, max = Double.MAX_VALUE)
			@Config.RequiresMcRestart
			public double MAXENERGY = Double.MAX_VALUE;
		}

		public AdvancedStorage Advanced = new AdvancedStorage();
		public RegularStorage Regular = new RegularStorage();
	}
}
