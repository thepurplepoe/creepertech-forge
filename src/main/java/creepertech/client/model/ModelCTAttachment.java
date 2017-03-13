package creepertech.client.model;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.base.Function;

import creepertech.CreeperTech;
import creepertech.util.Reference;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;

public class ModelCTAttachment implements IModel {

	@Override
	public Collection<ResourceLocation> getDependencies() {
		ModelResourceLocation meme = new ModelResourceLocation(Reference.modID + ":" + CreeperTech.ItemCTTest.name + "meme");
		ArrayList<ResourceLocation> c = new ArrayList<ResourceLocation>();
		c.add(meme);
		return c;
	}

	@Override
	public Collection<ResourceLocation> getTextures() {
		ResourceLocation meme = new ResourceLocation(Reference.modID + ":" + CreeperTech.ItemCTTest.name + "meme");
		ArrayList<ResourceLocation> c = new ArrayList<ResourceLocation>();
		c.add(meme);
		return c;
	}

	@Override
	public IBakedModel bake(IModelState state, VertexFormat format,
			Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		return null;
	}

	@Override
	public IModelState getDefaultState() {
		return null;
	}

}
