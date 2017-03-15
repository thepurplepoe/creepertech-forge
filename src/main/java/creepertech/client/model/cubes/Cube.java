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
	
	
	public Cube(boolean top, Cube par, Vec3d startpoint, Vec3d dimensions) {
		topNode = top;
		parent = par;
		children = new ArrayList<Cube>();
		Vec3d endpoint = new Vec3d(startpoint.xCoord + dimensions.xCoord, startpoint.yCoord + dimensions.yCoord, startpoint.zCoord + dimensions.zCoord);
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
	
	public Cube(Vec3d startpoint, Vec3d dimensions) {
		this(true, null, startpoint, dimensions);
	}
	
	public Cube(Cube parent, Vec3d startpoint, Vec3d dimensions) {
		this(false, parent, startpoint, dimensions);
	}
	
	public void startTransform() {
		transforming = true;
		temppoints = points;
		for (int i = 0; i < children.size(); i++) {
			children.get(i).startTransform();
		}
	}
	
	public void endTransform() {
		transforming = false;
		points = temppoints;
		for (int i = 0; i < children.size(); i++) {
			children.get(i).endTransform();
		}
	}
	
	public List<BakedQuad> addQuads(List<BakedQuad> quads, TextureAtlasSprite sprite) {
		return addQuads(quads, sprite, points);
	}
	
	public void addChild(Cube child) {
		children.add(child);
	}
	
	public void removeChild(Cube child) {
		children.remove(child);
	}
	
	public List<BakedQuad> addQuads(List<BakedQuad> quads, TextureAtlasSprite sprite, Vec3d[] transformedpoints) {
		for (int i = 0; i < children.size(); i++) {
			quads = children.get(i).addQuads(quads, sprite, children.get(i).points);
		}
		quads.add(QuadHelper.createQuad(transformedpoints[0], transformedpoints[1], transformedpoints[2], transformedpoints[3], sprite));
        quads.add(QuadHelper.createQuad(transformedpoints[4], transformedpoints[5], transformedpoints[6], transformedpoints[7], sprite));
        quads.add(QuadHelper.createQuad(transformedpoints[5], transformedpoints[2], transformedpoints[1], transformedpoints[6], sprite));
        quads.add(QuadHelper.createQuad(transformedpoints[7], transformedpoints[0], transformedpoints[3], transformedpoints[4], sprite));
        quads.add(QuadHelper.createQuad(transformedpoints[3], transformedpoints[2], transformedpoints[5], transformedpoints[4], sprite));
        quads.add(QuadHelper.createQuad(transformedpoints[7], transformedpoints[6], transformedpoints[1], transformedpoints[0], sprite));
        return quads;
	}
	
	public void translate(Vec3d transform) {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).translate(transform);
		}
		for (int i = 0; i < points.length; i++) {
			points[i] = MatrixHelper.transformCoordinates(MatrixHelper.getTranslationMatrix(transform), points[i]);
		}
	}
	
	public void rotateX(double angle) {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).rotateX(angle);
		}
		for (int i = 0; i < points.length; i++) {
			points[i] = MatrixHelper.transformCoordinates(MatrixHelper.getXRotationMatrix(angle), points[i]);
		}
	}
	
	public void rotateY(double angle) {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).rotateX(angle);
		}
		for (int i = 0; i < points.length; i++) {
			points[i] = MatrixHelper.transformCoordinates(MatrixHelper.getYRotationMatrix(angle), points[i]);
		}
	}
	
	public void rotateZ(double angle) {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).rotateX(angle);
		}
		for (int i = 0; i < points.length; i++) {
			points[i] = MatrixHelper.transformCoordinates(MatrixHelper.getZRotationMatrix(angle), points[i]);
		}
	}
}
