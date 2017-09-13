package thepurplepoe.creepertech;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import thepurplepoe.creepertech.client.gui.GUIEnricher;
import thepurplepoe.creepertech.client.gui.GUIGenerator;
import thepurplepoe.creepertech.common.inventory.ContainerEnricher;
import thepurplepoe.creepertech.common.inventory.ContainerGenerator;
import thepurplepoe.creepertech.common.tileentity.TileEntityEnricher;
import thepurplepoe.creepertech.common.tileentity.TileEntityGenerator;

public class CreeperTechGUIHandler implements IGuiHandler {
	public static final int ENRICHER = 0;
	public static final int GENERATOR = 1;

	public CreeperTechGUIHandler() {
	}

	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case ENRICHER:
				return new ContainerEnricher(player.inventory, (TileEntityEnricher)world.getTileEntity(new BlockPos(x, y, z)));
			case GENERATOR:
				return new ContainerGenerator(player.inventory, (TileEntityGenerator)world.getTileEntity(new BlockPos(x, y, z)));
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case ENRICHER:
				return new GUIEnricher(getServerGuiElement(ID, player, world, x, y, z), player.inventory, (TileEntityEnricher)world.getTileEntity((new BlockPos(x,y,z))));
			case GENERATOR:
				return new GUIGenerator(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
			default:
				return null;
		}
	}

}
