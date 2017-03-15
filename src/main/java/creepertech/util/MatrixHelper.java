package creepertech.util;

import net.minecraft.util.math.Vec3d;

public class MatrixHelper {
	public static Matrix4x4 getTranslationMatrix(Vec3d translation) {
		Matrix4x4 m = new Matrix4x4();
		m.a=m.f=m.k=m.p=1;
		m.d = translation.xCoord;
		m.h = translation.yCoord;
		m.l = translation.zCoord;
		return m;
	}
	
	public static Matrix4x4 getXRotationMatrix(double angleindegrees) {
		double angle = angleindegrees * Math.PI / 180;
		Matrix4x4 m = new Matrix4x4();
		m.a=m.p=1;
		m.f = Math.cos(angle);
		m.g = -1 * Math.sin(angle);
		m.j = Math.sin(angle);
		m.k = Math.cos(angle);
		return m;
	}
	
	public static Matrix4x4 getYRotationMatrix(double angleindegrees) {
		double angle = angleindegrees * Math.PI / 180;
		Matrix4x4 m = new Matrix4x4();
		m.f=m.p=1;
		m.a = Math.cos(angle);
		m.c = Math.sin(angle);
		m.i = -1 * Math.sin(angle);
		m.k = Math.cos(angle);
		return m;
	}
	
	public static Matrix4x4 getZRotationMatrix(double angleindegrees) {
		double angle = angleindegrees * Math.PI / 180;
		Matrix4x4 m = new Matrix4x4();
		m.k=m.p=1;
		m.a = Math.cos(angle);
		m.b = -1 * Math.sin(angle);
		m.e = Math.sin(angle);
		m.f = Math.cos(angle);
		return m;
	}
	
	public static Matrix4x4 getScaleMatrix(Vec3d scalefactor) {
		Matrix4x4 m = new Matrix4x4();
		m.p=1;
		m.a = scalefactor.xCoord;
		m.f = scalefactor.yCoord;
		m.k = scalefactor.zCoord;
		return m;
	}
	
	public static Vec3d transformCoordinates(Matrix4x4 t, Vec3d c) {
		Matrix1x4 coord = new Matrix1x4();
		coord.a = c.xCoord;
		coord.b = c.yCoord;
		coord.c = c.zCoord;
		coord.d = 1;
		
		Matrix1x4 out = new Matrix1x4();
		out.a = t.a*coord.a+t.b*coord.b+t.c*coord.c+t.d*coord.d;
		out.b = t.e*coord.a+t.f*coord.b+t.g*coord.c+t.h*coord.d;
		out.c = t.i*coord.a+t.j*coord.b+t.k*coord.c+t.l*coord.d;
		out.d = t.m*coord.a+t.n*coord.b+t.o*coord.c+t.p*coord.d;
		
		return new Vec3d(out.a, out.b, out.c);
	}
}
