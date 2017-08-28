package thepurplepoe.creepertech.client.model;

import java.util.ArrayList;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thepurplepoe.api.model.CombinedModel;
import thepurplepoe.creepertech.common.item.ItemGunBase;

public class M4A4OverrideList extends ItemOverrideList {
	IBakedModel silencerModel;

	public M4A4OverrideList(IBakedModel silencer) {
		super(new ArrayList<>());
		silencerModel = silencer;
	}

	
	@Override
	public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
		if (stack.getItem() instanceof ItemGunBase) {
			if ((((ItemGunBase)stack.getItem())).Silenced) {
				return new CombinedModel(originalModel, silencerModel);
			}
		} 
		return originalModel;
	}
}
