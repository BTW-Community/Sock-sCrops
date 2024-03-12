package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class SCBlockPlanterFarmlandDung extends SCBlockPlanterFarmland {
	
	protected SCBlockPlanterFarmlandDung(int iBlockID, String unlocalisedName) {
		super(iBlockID, unlocalisedName);
	}
	
	//------------- Client ------------//
	
	protected Icon dung;
	protected Icon dungWet;
	
	@Override
	public void registerIcons(IconRegister register) {
		super.registerIcons(register);
		
		dung = register.registerIcon( "SCBlockFarmlandDungedOverlay_dry");
		dungWet = register.registerIcon( "SCBlockFarmlandDungedOverlay_wet");
	}
	
	@Override
	protected void renderOverlay(RenderBlocks renderer, int i, int j, int k) {
		Icon overlay;
		
		if (isHydrated(renderer.blockAccess.getBlockMetadata(i, j, k)))
		{
			overlay = dungWet;
	    }
		else overlay = dung;
		
		renderer.setOverrideBlockTexture(overlay);
		renderer.setRenderBounds(0,0.999,0,1,1,1);
    	renderer.renderStandardBlock(this, i, j, k);
    	renderer.clearOverrideBlockTexture();
	}
	
	@Override
	protected void renderOverlayItem(RenderBlocks renderer, int iItemDamage, float brightness) {
		Icon overlay = dung;
		int var14 = getGrassColor(iItemDamage);
		
		float var8 = (float)(var14 >> 16 & 255) / 255.0F;
		float var9 = (float)(var14 >> 8 & 255) / 255.0F;
        float var10 = (float)(var14 & 255) / 255.0F;
        GL11.glColor4f(var8 * brightness, var9 * brightness, var10 * brightness, 1.0F);
		
    	renderer.setOverrideBlockTexture(overlay);
		renderer.setRenderBounds(0,0.998,0,1,1,1);
		FCClientUtilsRender.RenderInvBlockWithMetadata( renderer, this, -0.5F, -0.5F, -0.5F, iItemDamage );    
    	renderer.clearOverrideBlockTexture();
	}
}
