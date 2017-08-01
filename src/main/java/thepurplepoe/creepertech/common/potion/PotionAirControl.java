package thepurplepoe.creepertech.common.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.potion.Potion;

public class PotionAirControl extends Potion {

	public PotionAirControl(boolean isBadEffectIn, int liquidColorIn) {
		super(isBadEffectIn, liquidColorIn);
	}

    public void performEffect(EntityLivingBase entityLivingBaseIn, int p_76394_2_) {
    	entityLivingBaseIn.isAirBorne = false;
    	entityLivingBaseIn.onGround = true;
    	//entityLivingBaseIn.jumpMovementFactor = 1;
    	entityLivingBaseIn.setJumping(false);
    }
    
    public boolean isReady(int duration, int amplifier) {
    	return true;
    }
	
    public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier)
    {
        entityLivingBaseIn.setAbsorptionAmount(entityLivingBaseIn.getAbsorptionAmount() - (float)(4 * (amplifier + 1)));
        super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    public void applyAttributesModifiersToEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier)
    {
        entityLivingBaseIn.setAbsorptionAmount(entityLivingBaseIn.getAbsorptionAmount() + (float)(4 * (amplifier + 1)));
        super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }
}
