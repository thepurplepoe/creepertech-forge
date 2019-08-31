package thepurplepoe.creepertech.common.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import thepurplepoe.creepertech.common.util.ModRef;

public class BiomeCreeperForest extends Biome
{
	public BiomeCreeperForest(BiomeProperties properties)
	{
		super(properties);
		this.setRegistryName(ModRef.modId, "creeperforest");
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.topBlock = Blocks.DIAMOND_BLOCK.getDefaultState();
		this.fillerBlock = Blocks.DIRT.getDefaultState();
	}
}
