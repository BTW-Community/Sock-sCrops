package net.minecraft.src;

public class SCBlockCropWildCarrotSapling extends Block {

	protected SCBlockCropWildCarrotSapling(int par1) {
		super(par1, Material.plants);
	}
	
	
	private Icon[] iconArray = new Icon[4];
	
	@Override
	public void registerIcons(IconRegister register) {
		
		for (int i = 0; i < iconArray.length; i++) {
			iconArray[i] = register.registerIcon("SCBlockCropWildCarrotSapling_" + i);
		}
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		return iconArray[meta];
	}


}
