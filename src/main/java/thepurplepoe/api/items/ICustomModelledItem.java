package thepurplepoe.api.items;

import org.lwjgl.util.vector.Matrix4f;

public interface ICustomModelledItem {
	public Matrix4f getFPRH();
	public Matrix4f getFPLH();
	public Matrix4f getTPRH();
	public Matrix4f getTPLH();
	public Matrix4f getDEFAULT();
	public String getItemName();
}
