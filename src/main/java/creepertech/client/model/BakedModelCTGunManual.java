package creepertech.client.model;

import java.util.ArrayList;
import java.util.List;

import creepertech.client.model.cubes.Cube;
import creepertech.util.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;

public class BakedModelCTGunManual implements IBakedModel {
	
    private TextureAtlasSprite sprite;
	
	public BakedModelCTGunManual() {
        sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(Reference.modID + ":items/cttest");
	}	
	
	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		List<BakedQuad> quads = new ArrayList<BakedQuad>();

        //Cube cube = new Cube(new Vec3d(0.25, 0.25, 0.5), new Vec3d(0.5,0.5,-1)); 
        //cube.addChild(new Cube(cube, new Vec3d(0.25, -0.25, -0.5), new Vec3d(0.5, 0.5, -0.25)));
		Cube cube = new Cube(v(0.05, 0.1, 0), v(0.25, 0.25, 0.5));
		cube.addChild(new Cube(v(0, 0, 0.5), v(0.35, 0.35, 0.3)));
		cube.addChild(new Cube(v(0.1, 0.1, 0.8), v(0.15, 0.15, 0.5)));
        
        cube.startTransform();
        
        
        cube.translate(v(0,0.5,0));
        cube.rotateY(180);
        
        quads = cube.addQuads(quads, sprite);
        
        cube.endTransform();
        
        return quads;
	}
	
	private Vec3d v(double x, double y, double z) {
		if (x == 0) {
			x = 0.01;
		}
		if (y == 0) {
			y = 0.01;
		}
		if (z == 0) {
			z = 0.01;
		}
		return new Vec3d(x, y, z);
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
