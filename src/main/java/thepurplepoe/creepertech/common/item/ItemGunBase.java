package thepurplepoe.creepertech.common.item;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thepurplepoe.api.items.ICustomModelledItem;
import thepurplepoe.api.items.ItemWrapper;

public class ItemGunBase extends ItemWrapper implements ICustomModelledItem {
	private float rotX = 0.08f;
	private float rotY = -3.12f;
	private float rotZ = -0.16f;
	private float tX = -0.64f;
	private float tY = 1.44f;
	private float tZ = 0.04f;
	private float sc = 0.24f;
	
	Matrix4f FPLH = new Matrix4f();
	Matrix4f FPRH = new Matrix4f();
	Matrix4f TPLH = new Matrix4f();
	Matrix4f TPRH = new Matrix4f();
	Matrix4f DEFAULT = new Matrix4f();

	public ItemGunBase(String name) {
		super(name);
		FPRH.setIdentity();
		FPRH.rotate(rotX, new Vector3f(1,0,0));
		FPRH.rotate(rotY, new Vector3f(0,1,0));
		FPRH.rotate(rotZ, new Vector3f(0,0,1));
	    FPRH.m03 = tX; 
	    FPRH.m13 = tY;
	    FPRH.m23 = tZ;
	    Vector3f scale = new Vector3f();
	    scale.set(sc, sc, sc);
	    FPRH.scale(scale);
	    
		FPLH.setIdentity();
		FPLH.rotate(rotX, new Vector3f(1,0,0));
		FPLH.rotate(rotY, new Vector3f(0,1,0));
		FPLH.rotate(rotZ, new Vector3f(0,0,1));
	    FPLH.m03 = 0.48f; 
	    FPLH.m13 = tY;
	    FPLH.m23 = tZ;
	    scale.set(sc, sc, sc);
	    FPLH.scale(scale);
	    
		TPRH.setIdentity();
		TPRH.rotate(1.32f, new Vector3f(1,0,0));
		TPRH.rotate(-3.12f, new Vector3f(0,1,0));
		TPRH.rotate(-0.16f, new Vector3f(0,0,1));
	    TPRH.m03 = -0.72f; 
	    TPRH.m13 = 1.44f;
	    TPRH.m23 = 0.88f;
	    scale.set(sc, sc, sc);
	    TPRH.scale(scale);
	    
		TPLH.setIdentity();
		TPLH.rotate(1.32f, new Vector3f(1,0,0));
		TPLH.rotate(-3.12f, new Vector3f(0,1,0));
		TPLH.rotate(-0.16f, new Vector3f(0,0,1));
	    TPLH.m03 = 0.48f; 
	    TPLH.m13 = 1.44f;
	    TPLH.m23 = 0.88f;
	    scale.set(sc, sc, sc);
	    TPLH.scale(scale);
	    
		DEFAULT.setIdentity();
		DEFAULT.rotate(rotX, new Vector3f(1,0,0));
		DEFAULT.rotate(rotY, new Vector3f(0,1,0));
		DEFAULT.rotate(rotZ, new Vector3f(0,0,1));
	    DEFAULT.m03 = tX; 
	    DEFAULT.m13 = tY;
	    DEFAULT.m23 = tZ;
	    scale.set(sc, sc, sc);
	    DEFAULT.scale(scale);
	}
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{		
	}

	@Override
	public String getItemName() {
		return itemName;
	}

	@Override
	public Matrix4f getFPRH() {
		return FPRH;
	}

	@Override
	public Matrix4f getFPLH() {
		return FPLH;
	}

	@Override
	public Matrix4f getTPRH() {
		return TPRH;
	}

	@Override
	public Matrix4f getTPLH() {
		return TPLH;
	}

	@Override
	public Matrix4f getDEFAULT() {
		return DEFAULT;
	}
}
