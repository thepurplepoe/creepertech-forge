package thepurplepoe.creepertech.common.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class CTTeleporter extends Teleporter {

	public CTTeleporter(WorldServer worldIn) {
		super(worldIn);
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {
		 int i = MathHelper.floor(entityIn.posX);
         int j = MathHelper.floor(entityIn.posY) - 1;
         int k = MathHelper.floor(entityIn.posZ);
         
         
         // Unless you need an obsidian plate for whatever reason
         /*
         int l = 1;
         int i1 = 0;
         for (int j1 = -2; j1 <= 2; ++j1)
         {
             for (int k1 = -2; k1 <= 2; ++k1)
             {
                 for (int l1 = -1; l1 < 3; ++l1)
                 {
                     int i2 = i + k1 * 1 + j1 * 0;
                     int j2 = j + l1;
                     int k2 = k + k1 * 0 - j1 * 1;
                     boolean flag = l1 < 0;
                     this.world.setBlockState(new BlockPos(i2, j2, k2), flag ? Blocks.OBSIDIAN.getDefaultState() : Blocks.AIR.getDefaultState());
                 }
             }
         }
         */

         entityIn.setLocationAndAngles((double)i, (double)j, (double)k, entityIn.rotationYaw, 0.0F);
         entityIn.motionX = 0.0D;
         entityIn.motionY = 0.0D;
         entityIn.motionZ = 0.0D;
	}
	 
}
