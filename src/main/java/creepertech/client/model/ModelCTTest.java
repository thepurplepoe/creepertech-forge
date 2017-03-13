package creepertech.client.model;

import java.util.List;

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
import net.minecraft.util.EnumFacing;

public class ModelCTTest implements IBakedModel {
	public IBakedModel base;
	
	/*
    private TextureAtlasSprite sprite;
    
	*/
	
	public ModelCTTest() {
        //sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(Reference.modID + ":items/blank");

		base = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(new ModelResourceLocation(Reference.modID + ":" + CreeperTech.ItemCTTest.name + "meme"));
	}
	
	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		return base.getQuads(state, side, rand);
	}
	
	/*
    private int[] vertexToInts(double x, double y, double z, float u, float v) {
        return new int[] {
                Float.floatToRawIntBits((float) x),
                Float.floatToRawIntBits((float) y),
                Float.floatToRawIntBits((float) z),
                -1,
                Float.floatToRawIntBits(sprite.getInterpolatedU(u)),
                Float.floatToRawIntBits(sprite.getInterpolatedV(v)),
                0
        };
    }
    */
	
	/*
    private BakedQuad createQuad(Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4) {
        Vec3d normal = v1.subtract(v2).crossProduct(v3.subtract(v2));
        EnumFacing side = EnumFacing.DOWN;//LightUtil.toSide((float) normal.xCoord, (float) normal.yCoord, (float) normal.zCoord);

        return new BakedQuad(Ints.concat(
                vertexToInts(v1.xCoord, v1.yCoord, v1.zCoord, 0, 0),
                vertexToInts(v2.xCoord, v2.yCoord, v2.zCoord, 0, 16),
                vertexToInts(v3.xCoord, v3.yCoord, v3.zCoord, 16, 16),
                vertexToInts(v4.xCoord, v4.yCoord, v4.zCoord, 16, 0)
        ), -1, side, sprite, false, new VertexFormat());
    }
    */
	
	/*
	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		List<BakedQuad> quads = new ArrayList<BakedQuad>();
        double o = .4;

        // For each side we either cap it off if there is no similar block adjacent on that side
        // or else we extend so that we touch the adjacent block:

        if (up) {
            quads.add(createQuad(new Vec3d(1-o, 1-o, o), new Vec3d(1-o, 1, o), new Vec3d(1-o, 1, 1-o), new Vec3d(1-o, 1-o, 1-o)));
            quads.add(createQuad(new Vec3d(o, 1-o, 1-o), new Vec3d(o, 1, 1-o), new Vec3d(o, 1, o), new Vec3d(o, 1-o, o)));
            quads.add(createQuad(new Vec3d(o, 1, o), new Vec3d(1-o, 1, o), new Vec3d(1-o, 1-o, o), new Vec3d(o, 1-o, o)));
            quads.add(createQuad(new Vec3d(o, 1-o, 1-o), new Vec3d(1-o, 1-o, 1-o), new Vec3d(1-o, 1, 1-o), new Vec3d(o, 1, 1-o)));
        } else {
            quads.add(createQuad(new Vec3d(o, 1-o, 1-o), new Vec3d(1-o, 1-o, 1-o), new Vec3d(1-o, 1-o, o), new Vec3d(o, 1-o, o)));
        }

        if (down) {
            quads.add(createQuad(new Vec3d(1-o, 0, o), new Vec3d(1-o, o, o), new Vec3d(1-o, o, 1-o), new Vec3d(1-o, 0, 1-o)));
            quads.add(createQuad(new Vec3d(o, 0, 1-o), new Vec3d(o, o, 1-o), new Vec3d(o, o, o), new Vec3d(o, 0, o)));
            quads.add(createQuad(new Vec3d(o, o, o), new Vec3d(1-o, o, o), new Vec3d(1-o, 0, o), new Vec3d(o, 0, o)));
            quads.add(createQuad(new Vec3d(o, 0, 1-o), new Vec3d(1-o, 0, 1-o), new Vec3d(1-o, o, 1-o), new Vec3d(o, o, 1-o)));
        } else {
            quads.add(createQuad(new Vec3d(o, o, o), new Vec3d(1-o, o, o), new Vec3d(1-o, o, 1-o), new Vec3d(o, o, 1-o)));
        }

        if (east) {
            quads.add(createQuad(new Vec3d(1-o, 1-o, 1-o), new Vec3d(1, 1-o, 1-o), new Vec3d(1, 1-o, o), new Vec3d(1-o, 1-o, o)));
            quads.add(createQuad(new Vec3d(1-o, o, o), new Vec3d(1, o, o), new Vec3d(1, o, 1-o), new Vec3d(1-o, o, 1-o)));
            quads.add(createQuad(new Vec3d(1-o, 1-o, o), new Vec3d(1, 1-o, o), new Vec3d(1, o, o), new Vec3d(1-o, o, o)));
            quads.add(createQuad(new Vec3d(1-o, o, 1-o), new Vec3d(1, o, 1-o), new Vec3d(1, 1-o, 1-o), new Vec3d(1-o, 1-o, 1-o)));
        } else {
            quads.add(createQuad(new Vec3d(1-o, o, o), new Vec3d(1-o, 1-o, o), new Vec3d(1-o, 1-o, 1-o), new Vec3d(1-o, o, 1-o)));
        }

        if (west) {
            quads.add(createQuad(new Vec3d(0, 1-o, 1-o), new Vec3d(o, 1-o, 1-o), new Vec3d(o, 1-o, o), new Vec3d(0, 1-o, o)));
            quads.add(createQuad(new Vec3d(0, o, o), new Vec3d(o, o, o), new Vec3d(o, o, 1-o), new Vec3d(0, o, 1-o)));
            quads.add(createQuad(new Vec3d(0, 1-o, o), new Vec3d(o, 1-o, o), new Vec3d(o, o, o), new Vec3d(0, o, o)));
            quads.add(createQuad(new Vec3d(0, o, 1-o), new Vec3d(o, o, 1-o), new Vec3d(o, 1-o, 1-o), new Vec3d(0, 1-o, 1-o)));
        } else {
            quads.add(createQuad(new Vec3d(o, o, 1-o), new Vec3d(o, 1-o, 1-o), new Vec3d(o, 1-o, o), new Vec3d(o, o, o)));
        }

        if (north) {
            quads.add(createQuad(new Vec3d(o, 1-o, o), new Vec3d(1-o, 1-o, o), new Vec3d(1-o, 1-o, 0), new Vec3d(o, 1-o, 0)));
            quads.add(createQuad(new Vec3d(o, o, 0), new Vec3d(1-o, o, 0), new Vec3d(1-o, o, o), new Vec3d(o, o, o)));
            quads.add(createQuad(new Vec3d(1-o, o, 0), new Vec3d(1-o, 1-o, 0), new Vec3d(1-o, 1-o, o), new Vec3d(1-o, o, o)));
            quads.add(createQuad(new Vec3d(o, o, o), new Vec3d(o, 1-o, o), new Vec3d(o, 1-o, 0), new Vec3d(o, o, 0)));
        } else {
            quads.add(createQuad(new Vec3d(o, 1-o, o), new Vec3d(1-o, 1-o, o), new Vec3d(1-o, o, o), new Vec3d(o, o, o)));
        }
        if (south) {
            quads.add(createQuad(new Vec3d(o, 1-o, 1), new Vec3d(1-o, 1-o, 1), new Vec3d(1-o, 1-o, 1-o), new Vec3d(o, 1-o, 1-o)));
            quads.add(createQuad(new Vec3d(o, o, 1-o), new Vec3d(1-o, o, 1-o), new Vec3d(1-o, o, 1), new Vec3d(o, o, 1)));
            quads.add(createQuad(new Vec3d(1-o, o, 1-o), new Vec3d(1-o, 1-o, 1-o), new Vec3d(1-o, 1-o, 1), new Vec3d(1-o, o, 1)));
            quads.add(createQuad(new Vec3d(o, o, 1), new Vec3d(o, 1-o, 1), new Vec3d(o, 1-o, 1-o), new Vec3d(o, o, 1-o)));
        } else {
            quads.add(createQuad(new Vec3d(o, o, 1-o), new Vec3d(1-o, o, 1-o), new Vec3d(1-o, 1-o, 1-o), new Vec3d(o, 1-o, 1-o)));
        }

        return quads;
	}
	*/

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
		return base.getParticleTexture();
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
