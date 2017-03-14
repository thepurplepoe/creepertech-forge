package creepertech.client.model.cubes;

import java.util.ArrayList;
import java.util.List;

import creepertech.util.MatrixHelper;
import creepertech.util.QuadHelper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.Vec3d;

public class Cube {
	public boolean topNode;
	public Cube parent;
	public ArrayList<Cube> children;
	
	public boolean transforming;
	
	public Vec3d[] points = new Vec3d[8];
	protected Vec3d[] temppoints;
	
	
	public Cube(boolean top, Cube par, Vec3d startpoint, Vec3d endpoint) {
		topNode = top;
		parent = par;
		points[0] = new Vec3d(startpoint.xCoord, endpoint.yCoord, endpoint.zCoord);
		points[1] = endpoint;
		points[2] = new Vec3d(endpoint.yCoord, endpoint.yCoord, startpoint.zCoord);
		points[3] = new Vec3d(startpoint.xCoord, endpoint.yCoord, startpoint.zCoord);
		points[4] = startpoint;
		points[5] = new Vec3d(endpoint.xCoord, startpoint.yCoord, startpoint.zCoord);
		points[6] = new Vec3d(endpoint.xCoord, startpoint.yCoord, endpoint.zCoord);
		points[7] = new Vec3d(startpoint.xCoord, startpoint.yCoord, endpoint.zCoord);
		transforming = false;
	}
	
	public Cube(Vec3d startpoint, Vec3d endpoint) {
		this(true, null, startpoint, endpoint);
	}
	
	public Cube(Cube parent, Vec3d startpoint, Vec3d endpoint) {
		this(false, parent, startpoint, endpoint);
	}
	
	public static Vec3d getEndFromStartAndDimensions(Vec3d start, double x, double y, double z) {
		return new Vec3d(start.xCoord + x, start.yCoord + y, start.zCoord + z);
	}
	
	public void startTransform() {
		transforming = true;
		temppoints = points;
	}
	
	public void endTransform() {
		transforming = false;
		points = temppoints;
	}
	
	public List<BakedQuad> addQuads(List<BakedQuad> quads, TextureAtlasSprite sprite) {
		return addQuads(quads, sprite, points);
	}
	
	public List<BakedQuad> addQuads(List<BakedQuad> quads, TextureAtlasSprite sprite, Vec3d[] transformedpoints) {
		quads.add(QuadHelper.createQuad(transformedpoints[0], transformedpoints[1], transformedpoints[2], transformedpoints[3], sprite));
        quads.add(QuadHelper.createQuad(transformedpoints[4], transformedpoints[5], transformedpoints[6], transformedpoints[7], sprite));
        quads.add(QuadHelper.createQuad(transformedpoints[5], transformedpoints[2], transformedpoints[1], transformedpoints[6], sprite));
        quads.add(QuadHelper.createQuad(transformedpoints[7], transformedpoints[0], transformedpoints[3], transformedpoints[4], sprite));
        quads.add(QuadHelper.createQuad(transformedpoints[3], transformedpoints[2], transformedpoints[5], transformedpoints[4], sprite));
        quads.add(QuadHelper.createQuad(transformedpoints[7], transformedpoints[6], transformedpoints[1], transformedpoints[0], sprite));
        return quads;
	}
	
	/*
	public List<BakedQuad> addQuadsWithTranslation(List<BakedQuad> quads, TextureAtlasSprite sprite, Vec3d transform) {
		Vec3d transformedstart = MatrixHelper.transformCoordinates(MatrixHelper.getTranslationMatrix(transform), start);
		Vec3d transformedend = MatrixHelper.transformCoordinates(MatrixHelper.getTranslationMatrix(transform), end);
		return addQuads(quads, sprite, transformedstart, transformedend);
	}
	*/
	
	public void translate(Vec3d transform) {
		for (int i = 0; i < points.length; i++) {
			points[i] = MatrixHelper.transformCoordinates(MatrixHelper.getTranslationMatrix(transform), points[i]);
		}
	}
	
	public void rotateX(double angle) {
		for (int i = 0; i < points.length; i++) {
			points[i] = MatrixHelper.transformCoordinates(MatrixHelper.getXRotationMatrix(angle), points[i]);
		}
	}
	
	/*
	public List<BakedQuad> addQuadsWithXRotation(List<BakedQuad> quads, TextureAtlasSprite sprite, double angleindegrees) {
		Vec3d transformedstart = MatrixHelper.transformCoordinates(MatrixHelper.getXRotationMatrix(angleindegrees), start);
		Vec3d transformedend = MatrixHelper.transformCoordinates(MatrixHelper.getXRotationMatrix(angleindegrees), end);
		return addQuads(quads, sprite, transformedstart, transformedend);
	}
	*/
}
