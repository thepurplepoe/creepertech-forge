package creepertech.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;

public class BiomeCreeperForest extends Biome
{
	public BiomeCreeperForest(BiomeProperties properties)
	{
		super(properties);
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.topBlock = Blocks.GRASS.getDefaultState();
		this.fillerBlock = Blocks.DIRT.getDefaultState();
	}
}
