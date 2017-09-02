/*
package thepurplepoe.creepertech.common.world;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;
import thepurplepoe.creepertech.common.CreeperTech;
import thepurplepoe.creepertech.common.world.biome.CTBiomeProvider;
import thepurplepoe.creepertech.common.world.gen.CreeperTechChunkProvider;

public class CTWorldProvider extends WorldProvider {

	@Override
    protected void createBiomeProvider()
    {
        this.biomeProvider = new CTBiomeProvider(this.worldObj.getWorldInfo());
        this.isHellWorld = false;
        this.hasNoSky = false;
    }
	
	@Override
    public IChunkGenerator createChunkGenerator()
    {
        return new CreeperTechChunkProvider(this.worldObj, this.worldObj.getSeed(), true, this.worldObj.getWorldInfo().getGeneratorOptions());
    }

	@Override
    public boolean isSurfaceWorld()
    {
        return true;
    }
	
	@Override
    public boolean canRespawnHere()
    {
        return false;
    }

	@Override
	public DimensionType getDimensionType() {
		return CreeperTech.creeperDimensionType;
	}
}
*/