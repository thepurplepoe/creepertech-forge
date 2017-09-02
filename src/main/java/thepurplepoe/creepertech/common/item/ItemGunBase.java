package thepurplepoe.creepertech.common.item;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import thepurplepoe.api.items.ICustomModelledItem;
import thepurplepoe.api.items.ItemWrapper;
import thepurplepoe.creepertech.common.entity.EntityProjectileBase;

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
	
	public boolean Silenced = false;
	
	public float RecoilTime; // How long it takes for the recoil to take affect
	public float RecoveryTime; // How long it takes for the recoil to be recovered
	
	public int FireDelay;
	
	public static String Initialized = "Initialized"; // Is the Gun Initialized
	
	public static String ShotReady = "ShotRecharged"; // Is the Gun ready to fire another shot
	public static String ShotTime = "ShotTime"; // How many ticks since the gun last fired (Used for determining above)
	
	public static String Recoiling = "Recoiling"; // The gun is recoiling
	public static String Recovering = "Recovering"; // The gun is recovering recoil
	
	public static String CRX = "CRX";
	public static String CTY = "CTY";
	public static String CTZ = "CTZ";

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
	    
		this.setMaxStackSize(1);
		
		FireDelay = 2;	
		RecoilTime = 1000;
		RecoveryTime = 500;
	}
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		// No point doing all this if its on the ground / client + crashies
		if (!(entityIn instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer p = (EntityPlayer)entityIn;
		 
		// Make sure all the variables are initialized
		if (!GetBooleanVariable(stack, Initialized)) {
			SetVariable(stack, Initialized, true);
			SetVariable(stack, ShotReady, true);
			SetVariable(stack, ShotTime, 0);
			SetVariable(stack, Recoiling, false);
			SetVariable(stack, Recovering, false);
			SetVariable(stack, CRX, 0);
			SetVariable(stack, CTY, 0);
			SetVariable(stack, CTZ, 0);
		}
		 
		// If the gun isnt ready to shoot
		if (!GetBooleanVariable(stack, ShotReady)) {
			// Increment this
			IncrementVariable(stack, ShotTime, 1);	 
			 
			// If the shot is now ready to be ready
			if (GetIntegerVariable(stack, ShotTime) >= FireDelay) {
				// Make it ready and reset
				SetVariable(stack, ShotReady, true);
				SetVariable(stack, ShotTime, 0);
			}
		}
		 
		if (p.getHeldItemMainhand() == stack || p.getHeldItemOffhand() == stack) {	
			if (Mouse.isButtonDown(1) && GetBooleanVariable(stack, ShotReady)) {
				//Random r = new Random();
				
				
				EntityProjectileBase entityarrow = new EntityProjectileBase(worldIn, p);
                entityarrow.setAim(p, p.rotationPitch, p.rotationYaw, 0.0F, 4.5f, 1.0F);
				
                if (!worldIn.isRemote)
                {
                    worldIn.spawnEntity(entityarrow);
                }
                
                SetVariable(stack, Recoiling, true);
                SetVariable(stack, Recovering, false);

                worldIn.playSound((EntityPlayer)null, p.posX, p.posY, p.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 1.5f);
				 	 
				// Make the gun unready to fire again
				SetVariable(stack, ShotReady, false);
			}
		}
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
