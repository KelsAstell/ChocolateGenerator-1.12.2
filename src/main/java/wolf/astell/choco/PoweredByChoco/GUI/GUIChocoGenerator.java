package wolf.astell.choco.PoweredByChoco.GUI;

import net.minecraft.client.resources.I18n;
import wolf.astell.choco.PoweredByChoco.tile.base.GeneratorTE;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GUIChocoGenerator extends GuiContainer {
	private GeneratorTE te;
	ResourceLocation location = new ResourceLocation("poweredbychoco:textures/gui/gui.png");
	public GUIChocoGenerator(IInventory playerInv, GeneratorTE te) {
        super(new ContainerChocoGenerator(playerInv, te));
        
        this.te = te;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    	this.mc.getTextureManager().bindTexture(location);
    	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    	drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    	this.fontRenderer.drawString(I18n.format("choco.gui.current_efficiency"), guiLeft+8, guiTop+60, 1, false);
    	this.fontRenderer.drawString(te.energy + " RF", guiLeft+8, guiTop+70, 1, false);
    }
}
