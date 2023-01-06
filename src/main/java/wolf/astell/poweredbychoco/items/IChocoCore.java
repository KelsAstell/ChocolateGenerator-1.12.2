package wolf.astell.poweredbychoco.items;

import net.minecraft.item.Item;

public class IChocoCore extends Item {
	public IChocoCore(String name) {
		super();
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(itemList.CreativeTab);
	}
}
