package io.github.mosadie.ExponentialPower.client.gui;

import io.github.mosadie.ExponentialPower.GUIContainer.ContainerEnderGeneratorTE;
import io.github.mosadie.ExponentialPower.TileEntitys.BaseClasses.GeneratorTE;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GUIEnderGeneratorTE extends GuiContainer {
	private GeneratorTE te;
	ResourceLocation rl = new ResourceLocation("poweredbychoco:textures/gui.png");
	public GUIEnderGeneratorTE(IInventory playerInv, GeneratorTE te) {
        super(new ContainerEnderGeneratorTE(playerInv, te));
        
        this.te = te;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    	this.mc.getTextureManager().bindTexture(rl);
    	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    	drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        this.fontRenderer.drawString("Current Efficiency: ", guiLeft+9, guiTop+65, 0xffae00, false);
        this.fontRenderer.drawString(te.energy + " RF", guiLeft+9, guiTop+75, 0xffae00, false);
    }
}
