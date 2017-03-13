package creepertech.client.model;

import java.util.List;

import com.google.common.base.Function;

import creepertech.CreeperTech;
import creepertech.util.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class BakedModelCTGun implements IBakedModel {
	public IBakedModel baseModel;
	public IBakedModel attachmentModel;
	public IModel attachment;
	public static ModelResourceLocation attachmentModelLocation = new ModelResourceLocation(Reference.modID + ":" + CreeperTech.ItemCTTest.name + "meme");
	public static ModelResourceLocation baseModelLocation = new ModelResourceLocation(Reference.modID + ":" + CreeperTech.ItemCTTest.name);
	
	public BakedModelCTGun(IBakedModel basemodel) {
		baseModel = basemodel;
		try {
			attachment = ModelLoaderRegistry.getModel(attachmentModelLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Function<ResourceLocation, TextureAtlasSprite> textureGetter;
		textureGetter = new Function<ResourceLocation, TextureAtlasSprite>()
        {
            public TextureAtlasSprite apply(ResourceLocation location)
            {
                return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
            }
        };
		attachmentModel = attachment.bake(attachment.getDefaultState(), new VertexFormat(), textureGetter);
	}
	
	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		//return baseModel.getQuads(state, side, rand);
		return attachmentModel.getQuads(state, side, rand);
	}

	@Override
	public boolean isAmbientOcclusion() {
		return baseModel.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return baseModel.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		return baseModel.isBuiltInRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return baseModel.getParticleTexture();
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return baseModel.getItemCameraTransforms();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return baseModel.getOverrides();
	}

}
