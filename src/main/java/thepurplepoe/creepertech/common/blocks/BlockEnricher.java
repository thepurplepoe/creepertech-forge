package thepurplepoe.creepertech.common.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thepurplepoe.api.tileentity.TileEntityWrapper;
import thepurplepoe.creepertech.CreeperTechGUIHandler;
import thepurplepoe.creepertech.common.CreeperTech;
import thepurplepoe.creepertech.common.tileentity.TileEntityEnricher;
import thepurplepoe.creepertech.common.util.Helper;

public class BlockEnricher extends TileEntityWrapper<TileEntityEnricher> {

	public BlockEnricher(Material material, String name) {
		super(material, name);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(CreeperTech.instance, CreeperTechGUIHandler.ENRICHER, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public Class<TileEntityEnricher> getTileEntityClass() {
		return TileEntityEnricher.class;
	}

	@Nullable
	@Override
	public TileEntityEnricher createTileEntity(World world, IBlockState state) {
		return new TileEntityEnricher();
	}
}
