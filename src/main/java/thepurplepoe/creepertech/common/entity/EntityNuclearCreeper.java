package thepurplepoe.creepertech.common.entity;

import net.minecraft.world.World;

public class EntityNuclearCreeper extends EntityCTCreeper {

	public EntityNuclearCreeper(World worldIn) {
		super(worldIn);
		fuseTime = 120;
		explosionRadius = 50;
		fuseDistance = 5;
		defuseDistance = 100;
		this.setSize(1.8F, 5.1F);
	}

}
