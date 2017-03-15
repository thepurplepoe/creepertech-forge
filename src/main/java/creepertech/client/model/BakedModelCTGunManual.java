package creepertech.client.model;

import java.util.ArrayList;
import java.util.List;

import com.google.common.primitives.Ints;

import creepertech.client.model.cubes.Cube;
import creepertech.item.CTItem;
import creepertech.util.QuadHelper;
import creepertech.util.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.pipeline.LightUtil;

public class BakedModelCTGunManual implements IBakedModel {
	
    private TextureAtlasSprite sprite;
	
	public BakedModelCTGunManual() {
        sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(Reference.modID + ":items/cttest");
	}	
	
	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		List<BakedQuad> quads = new ArrayList<BakedQuad>();

        Cube cube = new Cube(new Vec3d(0.25, 0.25, 0.5), new Vec3d(0.5,0.5,-1)); 
        cube.addChild(new Cube(cube, new Vec3d(0.25, -0.25, -0.5), new Vec3d(0.5, 0.5, -0.25)));
        
        cube.startTransform();
        
        quads = cube.addQuads(quads, sprite);
        
        cube.endTransform();
        
        return quads;
	}
	

	@Override
	public boolean isAmbientOcclusion() {
		return false;
	}

	@Override
	public boolean isGui3d() {
		return true;
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return sprite;
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return ItemCameraTransforms.DEFAULT;
	}

	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.NONE;
	}
}
