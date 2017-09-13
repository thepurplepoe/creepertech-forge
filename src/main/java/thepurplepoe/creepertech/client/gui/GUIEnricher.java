package thepurplepoe.creepertech.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import thepurplepoe.api.helper.Helper;
import thepurplepoe.creepertech.common.blocks.Blocks;
import thepurplepoe.creepertech.common.tileentity.TileEntityEnricher;
import thepurplepoe.creepertech.common.util.ModRef;


public class GUIEnricher extends GuiContainer {
	private InventoryPlayer playerInv;
	private TileEntityEnricher enricherInv;
	
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ModRef.modId, "textures/gui/enricher.png");

	public GUIEnricher(Container container, InventoryPlayer playerInv, TileEntityEnricher enricherinv) {
		super(container);
		this.playerInv = playerInv;
		enricherInv = enricherinv;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y - 10, 0, 0, xSize, ySize + 40);
		int energyheight = (int) ((float)enricherInv.getEnergyStored() / (float)enricherInv.getMaxEnergyStored() * 91f);
		Helper.writeInChat(enricherInv.getEnergyStored() + ":" + enricherInv.getMaxEnergyStored() + "=" + energyheight);
		drawTexturedModalRect(x + 6, y + 8 + (91 - energyheight), 176, 91 - energyheight, 21, energyheight);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = I18n.format(Blocks.enricher.getUnlocalizedName() + ".name");
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 0, 0x404040);
		fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 64, 0x404040);
	}
}
