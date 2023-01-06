package wolf.astell.poweredbychoco.items;

import net.minecraft.item.Item;

public class chocoCore extends Item {
	public chocoCore(String name) {
		super();
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(itemList.CreativeTab);
	}
}
