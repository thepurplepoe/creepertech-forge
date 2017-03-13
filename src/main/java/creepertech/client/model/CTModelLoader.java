package creepertech.client.model;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Charsets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

public class CTModelLoader implements ICustomModelLoader {
	private static final String EMPTY_MODEL_RAW = "{    \'elements\': [        {   \'from\': [0, 0, 0],            \'to\': [16, 16, 16],            \'faces\': {                \'down\': {\'uv\': [0, 0, 16, 16], \'texture\': \'\' }            }        }    ]}".replaceAll("\'", "\"");
    protected static final ModelBlock MODEL_GENERATED = ModelBlock.deserialize(EMPTY_MODEL_RAW);
    protected static final ModelBlock MODEL_ENTITY = ModelBlock.deserialize(EMPTY_MODEL_RAW);
	
	
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
	}

	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		return (modelLocation == BakedModelCTGun.attachmentModelLocation);
	}
	
    protected ResourceLocation getModelLocation(ResourceLocation location)
    {
        return new ResourceLocation(location.getResourceDomain(), "models/" + location.getResourcePath() + ".json");
    }

	public ModelBlock loadModelBlock(ResourceLocation location) {
		Reader reader = null;
		IResource iresource = null;
		ModelBlock lvt_5_1_ = MODEL_GENERATED;
		
		try {
			String s = location.getResourcePath();
		
			if (!"builtin/generated".equals(s))
			{
				iresource = Minecraft.getMinecraft().getResourceManager().getResource(this.getModelLocation(location));
				reader = new InputStreamReader(iresource.getInputStream(), Charsets.UTF_8);
		
				lvt_5_1_ = ModelBlock.deserialize(reader);
				lvt_5_1_.name = location.toString();
				ModelBlock modelblock1 = lvt_5_1_;
				return modelblock1;
			}
		
			lvt_5_1_ = MODEL_GENERATED;
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly((Closeable)iresource);
		}
		
		return lvt_5_1_;
	}
	
	@Override
	public IModel loadModel(ResourceLocation location) throws Exception {	
		ModelBlock model = loadModelBlock(location);
		IModel imodel = new ModelCTAttachment(location, model);
		return imodel;
	}
}
