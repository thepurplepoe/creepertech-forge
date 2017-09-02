package thepurplepoe.creepertech.common.util;

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
        EnumFacing side = LightUtil.toSide((float) normal.x, (float) normal.y, (float) normal.z);
        BakedQuad b = new BakedQuad(Ints.concat(
                vertexToInts(v1.x, v1.y, v1.z, 0, 0, sprite),
                vertexToInts(v2.x, v2.y, v2.z, 0, 16, sprite),
                vertexToInts(v3.x, v3.y, v3.z, 16, 16, sprite),
                vertexToInts(v4.x, v4.y, v4.z, 16, 0, sprite)
        ), -1, side, sprite, false, new VertexFormat());
        return b;
    }
}
