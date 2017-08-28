package thepurplepoe.creepertech.client.model;

import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import thepurplepoe.api.items.ICustomModelledItem;
import thepurplepoe.creepertech.common.item.Items;
import thepurplepoe.creepertech.common.util.ModRef;

public class PerspectiveModel implements IPerspectiveAwareModel {
	private IBakedModel Model2d;
	private IBakedModel Model3d;
	private IBakedModel ModelSilencer;
	
	private String name;
	
	private TextureAtlasSprite sprite;

	public PerspectiveModel(IBakedModel model2d, IBakedModel model3d, String itemName, IBakedModel silencer) {
	       super();
	       Model2d = model2d;
	       Model3d = model3d;
	       sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(ModRef.modId + ":items/cttest");
	       name = itemName;
	       ModelSilencer = silencer;
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		return Model3d.getQuads(state, side, rand);
	}

	@Override
	public boolean isAmbientOcclusion() {
		return false;
	}

	@Override 
	public boolean isGui3d() {
		return false;
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

	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType) {
		Matrix4f FPRH = convert(((ICustomModelledItem)(Items.Registry.getObject(name))).getFPRH());
		Matrix4f FPLH = convert(((ICustomModelledItem)(Items.Registry.getObject(name))).getFPLH());
		Matrix4f TPRH = convert(((ICustomModelledItem)(Items.Registry.getObject(name))).getTPRH());
		Matrix4f TPLH = convert(((ICustomModelledItem)(Items.Registry.getObject(name))).getTPLH());
		Matrix4f DEFAULT = convert(((ICustomModelledItem)(Items.Registry.getObject(name))).getDEFAULT());
        switch(cameraTransformType)
        {
        case GUI:
            if(Model2d instanceof IPerspectiveAwareModel){
                Pair<? extends IBakedModel, Matrix4f> result =((IPerspectiveAwareModel) Model2d).handlePerspective(cameraTransformType);
                return result;
            }
            return Pair.of(this.Model2d,null);
        case FIRST_PERSON_LEFT_HAND:
            return Pair.of(this.Model3d, FPLH);
        case FIRST_PERSON_RIGHT_HAND:
            return Pair.of(this.Model3d, FPRH);
        case THIRD_PERSON_LEFT_HAND:
            return Pair.of(this.Model3d, TPLH);
        case THIRD_PERSON_RIGHT_HAND:
            return Pair.of(this.Model3d, TPRH);
        default:
            return Pair.of(this.Model3d, DEFAULT);
        }
    }
	
	protected Matrix4f convert(org.lwjgl.util.vector.Matrix4f mat) {
		Matrix4f ret = new Matrix4f();
		ret.m00 = mat.m00;
		ret.m01 = mat.m01;
		ret.m02 = mat.m02;
		ret.m03 = mat.m03;
		ret.m10 = mat.m10;
		ret.m11 = mat.m11;
		ret.m12 = mat.m12;
		ret.m13 = mat.m13;
		ret.m20 = mat.m20;
		ret.m21 = mat.m21;
		ret.m22 = mat.m22;
		ret.m23 = mat.m23;
		ret.m30 = mat.m30;
		ret.m31 = mat.m31;
		ret.m32 = mat.m32;
		ret.m33 = mat.m33;
		return ret;
	}
	
	protected Matrix4f convert(Matrix4f mat) {
		return mat;
	}
}
