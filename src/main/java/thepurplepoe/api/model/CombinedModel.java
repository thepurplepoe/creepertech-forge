package thepurplepoe.api.model;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public class CombinedModel implements IBakedModel {
	IBakedModel parentModel;
	IBakedModel childModel;

	public CombinedModel(IBakedModel parent, IBakedModel child) {
		parentModel = parent;
		childModel = child;
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		ArrayList<BakedQuad> quads = new ArrayList<BakedQuad>();
		quads.addAll(parentModel.getQuads(state, side, rand));
		quads.addAll(childModel.getQuads(state, side, rand));
		return quads;
	}

	@Override
	public boolean isAmbientOcclusion() {
		return parentModel.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return parentModel.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		return parentModel.isBuiltInRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return parentModel.getParticleTexture();
	}

	@SuppressWarnings("deprecation")
	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return parentModel.getItemCameraTransforms();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return parentModel.getOverrides();
	}

}
