package creepertech.util;

import com.google.common.primitives.Ints;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.pipeline.LightUtil;

public class QuadHelper {
	public static int[] vertexToInts(double x, double y, double z, float u, float v, TextureAtlasSprite sprite) {
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
	
	public static BakedQuad createQuad(Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4, TextureAtlasSprite sprite) {
        Vec3d normal = v1.subtract(v2).crossProduct(v3.subtract(v2));
        EnumFacing side = LightUtil.toSide((float) normal.xCoord, (float) normal.yCoord, (float) normal.zCoord);
        BakedQuad b = new BakedQuad(Ints.concat(
                vertexToInts(v1.xCoord, v1.yCoord, v1.zCoord, 0, 0, sprite),
                vertexToInts(v2.xCoord, v2.yCoord, v2.zCoord, 0, 16, sprite),
                vertexToInts(v3.xCoord, v3.yCoord, v3.zCoord, 16, 16, sprite),
                vertexToInts(v4.xCoord, v4.yCoord, v4.zCoord, 16, 0, sprite)
        ), -1, side, sprite, false, new VertexFormat());
        return b;
    }
}
