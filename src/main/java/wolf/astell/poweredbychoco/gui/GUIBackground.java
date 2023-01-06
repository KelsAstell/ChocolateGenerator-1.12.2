package wolf.astell.poweredbychoco.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import wolf.astell.poweredbychoco.tile_entities.base.generatorTE;

public class GUIBackground extends GuiContainer {
	private generatorTE te;
	public GUIBackground(IInventory playerInv, generatorTE te) {
        super(new GUISlot(playerInv, te));
        this.te = te;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    	this.mc.getTextureManager().bindTexture(new ResourceLocation("poweredbychoco:textures/gui/gui.png"));
    	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    	drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    	this.fontRenderer.drawString(String.valueOf(new TextComponentTranslation("choco.gui.current_energy")), guiLeft+5, guiTop+60, 1, false);
    	this.fontRenderer.drawString(te.energy + " RF", guiLeft+5, guiTop+70, 1, false);
    }
}
