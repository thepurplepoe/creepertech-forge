
package thepurplepoe.creepertech.common.world;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thepurplepoe.creepertech.common.CreeperTech;

public class CTWorldProvider extends WorldProvider {

	@Override
    public void init()
    {
        this.biomeProvider = new BiomeProviderSingle(CreeperTech.CreeperForest);
        this.nether = false;
        this.hasSkyLight = true;
    }
		
	
	/**
     * Return Vec3D with biome specific fog color
     */
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
    {
        return new Vec3d(1, 1, 1);
    }

    /**
     * Creates the light to brightness table
     */
    protected void generateLightBrightnessTable()
    {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * 0.9F + 0.1F;
        }
    }

    public IChunkGenerator createChunkGenerator()
    {
    	return new ChunkGeneratorOverworld(this.world, this.world.getSeed(), this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getWorldInfo().getGeneratorOptions());
    }

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
    public boolean isSurfaceWorld()
    {
        return false;
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return false;
    }


    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return false;
    }

    public WorldBorder createWorldBorder()
    {
        return new WorldBorder()
        {
            public double getCenterX()
            {
                return super.getCenterX() / 8.0D;
            }
            public double getCenterZ()
            {
                return super.getCenterZ() / 8.0D;
            }
        };
    }

    public DimensionType getDimensionType()
    {
        return CreeperTech.creeperDimensionType;
    }
}
