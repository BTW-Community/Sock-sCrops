package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SCBlockChoppingBoard extends BlockContainer {

	//Array of all items that have a custom renderer
	private static ArrayList<Item> specialRenderItems = new ArrayList();
	
	static
	{
		specialRenderItems.add(Item.skull);
		specialRenderItems.add(Item.bread);
		specialRenderItems.add(Item.appleRed);
		specialRenderItems.add(Item.appleGold);
		specialRenderItems.add(Item.bowlEmpty);
		specialRenderItems.add(Item.bowlSoup);
		specialRenderItems.add(FCBetterThanWolves.fcItemSteakAndPotatoes);
		specialRenderItems.add(FCBetterThanWolves.fcItemHeartyStew);
		specialRenderItems.add(FCBetterThanWolves.fcItemChickenSoup);
		specialRenderItems.add(FCBetterThanWolves.fcItemFishSoup);
		specialRenderItems.add(FCBetterThanWolves.fcItemTastySandwich);
		specialRenderItems.add(FCBetterThanWolves.fcItemCreeperOysters);
		specialRenderItems.add(Item.book);
		specialRenderItems.add(Item.enchantedBook);
		specialRenderItems.add(Item.writableBook);
		specialRenderItems.add(Item.writtenBook);
		specialRenderItems.add(Item.cake);
		specialRenderItems.add(Item.bucketEmpty);
		specialRenderItems.add(Item.bucketWater);
		specialRenderItems.add(Item.bucketMilk);
		specialRenderItems.add(FCBetterThanWolves.fcItemBucketMilkChocolate);
		specialRenderItems.add(FCBetterThanWolves.fcItemBucketCement);
		specialRenderItems.add(FCBetterThanWolves.fcItemDonut);
		
		specialRenderItems.add(SCDefs.donutSugar);
		specialRenderItems.add(SCDefs.donutChocolate);
		
		specialRenderItems.add(SCDefs.itemMuffinChocolate);
		specialRenderItems.add(SCDefs.itemMuffinSweetberry);
		specialRenderItems.add(SCDefs.itemMuffinBlueberry);
		
		specialRenderItems.add(FCBetterThanWolves.fcItemEnderSpectacles);
		specialRenderItems.add(FCBetterThanWolves.fcItemDung);
		specialRenderItems.add(FCBetterThanWolves.fcItemGoldenDung);
		
		specialRenderItems.add(Item.cake);
		specialRenderItems.add(SCDefs.chocolateCakeItem);
		specialRenderItems.add(SCDefs.carrotCakeItem);
		specialRenderItems.add(SCDefs.cakeSlice);
		specialRenderItems.add(SCDefs.chocolateCakeSlice);
		specialRenderItems.add(SCDefs.carrotCakeSlice);
		
		specialRenderItems.add(SCDefs.pieCrust);
		
		specialRenderItems.add(Item.pumpkinPie);
		specialRenderItems.add(SCDefs.sweetberryPieCooked);
		specialRenderItems.add(SCDefs.blueberryPieCooked);
		specialRenderItems.add(SCDefs.applePieCooked);
		specialRenderItems.add(SCDefs.cherryPieCooked);
		specialRenderItems.add(SCDefs.lemonPieCooked);
		
		specialRenderItems.add(SCDefs.cakeSlice);
		specialRenderItems.add(SCDefs.chocolateCakeSlice);
		specialRenderItems.add(SCDefs.carrotCakeSlice);
		
		specialRenderItems.add(SCDefs.pumpkinPieSlice);
		specialRenderItems.add(SCDefs.sweetberryPieSlice);
		specialRenderItems.add(SCDefs.blueberryPieSlice);
		specialRenderItems.add(SCDefs.applePieSlice);
		specialRenderItems.add(SCDefs.cherryPieSlice);
		specialRenderItems.add(SCDefs.lemonPieSlice);

		specialRenderItems.add(Item.fishRaw);
		specialRenderItems.add(SCDefs.cod);
		specialRenderItems.add(SCDefs.salmon);
		specialRenderItems.add(SCDefs.tropical);
		specialRenderItems.add(SCDefs.puffer);
		
//		specialRenderItems.add(SCDefs.breadSlice);
//		specialRenderItems.add(SCDefs.hamburgerUnfinished);
//		specialRenderItems.add(SCDefs.hamburger);
		

	}
	
	protected SCBlockChoppingBoard(int blockID) {
		super(blockID, Material.wood);
		
        setHardness( 0.5F );        
		
        setStepSound( soundWoodFootstep );   
        
        SetFireProperties( FCEnumFlammability.PLANKS );
        
//        setCreativeTab( CreativeTabs.tabDecorations );
        
        setTickRandomly(true);
        
        setUnlocalizedName( "SCBlockChoppingBoard" );
        
        setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
    	par3List.add(new ItemStack(par1, 1, 0));
    	par3List.add(new ItemStack(par1, 1, 1));
    	par3List.add(new ItemStack(par1, 1, 2));
    	par3List.add(new ItemStack(par1, 1, 3));
    	par3List.add(new ItemStack(par1, 1, 4));
    	par3List.add(new ItemStack(par1, 1, 5));
    	par3List.add(new ItemStack(par1, 1, 6));
    }
	
	@Override
	public int getDamageValue(World world, int i, int j, int k)
	{
		SCTileEntityChoppingBoard choppingBoard = (SCTileEntityChoppingBoard) world.getBlockTileEntity(i, j, k);
		
		return choppingBoard.getWoodType();
	}
    
	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new SCTileEntityChoppingBoard();
	}
	
	
    private boolean hasSpecialRenderer(Item item) {
		return specialRenderItems.contains(item);
	}
	
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int iFacing, float fXClick, float fYClick, float fZClick)
	{
		SCTileEntityChoppingBoard choppingBoard = (SCTileEntityChoppingBoard) world.getBlockTileEntity(i, j, k);

		ItemStack knifeStack = choppingBoard.getKnifeStack();

		ItemStack heldStack = player.getCurrentEquippedItem();

		if (choppingBoard != null) {
			if (heldStack != null) {
				if (knifeStack == null) {
					ItemStack stackToStore = heldStack.splitStack(1);

					choppingBoard.setInventorySlotContents(0, stackToStore);
					
					int rotation = MathHelper.floor_double((double)(player.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
					
	                choppingBoard.setItemRotation(rotation);
	                
	                // System.out.println("Rotation = " + rotation);
	                
					if (!world.isRemote) {
						world.playAuxSFX(FCBetterThanWolves.m_iItemCollectionPopSoundAuxFXID, i, j, k, 0);
					}

					return true;
				}
				else {
					// knifeStack has item

					ItemStack onBoardStack = choppingBoard.getKnifeStack();

					SCCraftingManagerBurgerRecipes burgerRecipe = onBoardStack == null ? null
							: SCCraftingManagerBurgerFilter.instance.getRecipe(heldStack, onBoardStack);
					
					//Burger making
					if (onBoardStack != null && burgerRecipe != null)
					{						
						ItemStack output = burgerRecipe.getBoardOutput().copy();
						boolean ejects = burgerRecipe.getEjects();
						
						if (onBoardStack.itemID == burgerRecipe.getStackOnBoard().itemID)
						{
							if (heldStack.itemID == burgerRecipe.getInput().itemID)
							{		
								
								setBurger(world, i, j, k, player, choppingBoard, heldStack, output, ejects);
								
								return true;
							}
						}
					}
					
					SCCraftingManagerChoppingBoardFilterRecipe recipe = onBoardStack == null ? null
							: SCCraftingManagerChoppingBoardFilter.instance.getRecipe(heldStack, onBoardStack);
					
					if (recipe != null) {

						ItemStack[] output = recipe.getBoardOutput();

						world.playAuxSFX(SCCustomAuxFX.choppingBoardAuxFXID, i, j, k, 0);

						assert (output != null && output.length > 0);

						for (int listIndex = 0; listIndex < output.length; listIndex++) {
							ItemStack cutStack = output[listIndex].copy();

							if (cutStack != null) {
								if (!world.isRemote) {

									world.playSoundAtEntity(player, this.stepSound.getStepSound(),
											this.stepSound.getVolume() * 0.2F, this.stepSound.getPitch() * 3F);

									int dir = getDirection(world.getBlockMetadata(i, j, k));
									dir = Direction.rotateOpposite[dir];
									int facing = Direction.directionToFacing[dir];

									FCUtilsItem.EjectStackFromBlockTowardsFacing(world, i, j, k, cutStack, facing);

								}

								player.playSound("mob.zombie.wood", 0.05F, 2.5F * 10);

							}
						}

						ItemStack arrow = GetFirstArrowStackInHotbar(player, recipe.getStackOnBoard());

						if (arrow != null) {
							ItemStack stackToStore;

							if (arrow.stackSize > 1) {
								stackToStore = arrow.splitStack(1);
								choppingBoard.setInventorySlotContents(0, stackToStore);
							}
							else {
								player.inventory.consumeInventoryItem(arrow.itemID);
								choppingBoard.setInventorySlotContents(0, arrow);
							}

							if (!world.isRemote) {
								world.playAuxSFX(FCBetterThanWolves.m_iItemCollectionPopSoundAuxFXID, i, j, k, 0);
							}

						} 
						else {
							choppingBoard.setKnifeStack(null);
						}

						if (!player.capabilities.isCreativeMode && heldStack.getItem() instanceof FCItemTool) {
							heldStack.attemptDamageItem(1, world.rand);

							int maxDamage = heldStack.getMaxDamage();
							if (heldStack.getItemDamage() >= maxDamage) {
								// break tool
								heldStack.stackSize = 0;
								player.playSound("random.break", 0.8F, 0.8F + player.worldObj.rand.nextFloat() * 0.4F);
							}
						}
						return true;
					}
				}
			} else {
				if (knifeStack != null) {
					// hand is empty
					FCUtilsItem.GivePlayerStackOrEject(player, knifeStack, i, j, k);

					choppingBoard.setKnifeStack(null);

					if (!world.isRemote) {
						world.playAuxSFX(FCBetterThanWolves.m_iItemCollectionPopSoundAuxFXID, i, j, k, 0);
					}
					return true;
				}
			}
		}
		return false;
	}

	protected void setBurger(World world, int i, int j, int k, EntityPlayer player, SCTileEntityChoppingBoard choppingBoard, ItemStack heldStack, ItemStack itemToStore, boolean ejects) {

		if (heldStack.stackSize > 1) {
			heldStack.splitStack(1);
		}
		else {
			player.inventory.consumeInventoryItem(heldStack.itemID);
		}
		
		if (!ejects)
		{
			choppingBoard.setKnifeStack(itemToStore);
		}
		else if (ejects )
		{
			if (!world.isRemote)
			{
				int dir = getDirection(world.getBlockMetadata(i, j, k));
				dir = Direction.rotateOpposite[dir];
				int facing = Direction.directionToFacing[dir];

				FCUtilsItem.EjectStackFromBlockTowardsFacing(world, i, j, k, itemToStore, facing);
				
			}

			choppingBoard.setKnifeStack(null);									
		}
		
	}


	private ItemStack GetFirstArrowStackInHotbar(EntityPlayer player, ItemStack itemOnBoard) {
    	for ( int i = 0; i < 9; i++ )
    	{
    		ItemStack tempStack = player.inventory.getStackInSlot( i );
    		
    		if ( tempStack != null && tempStack.itemID == itemOnBoard.itemID && tempStack.getItemDamage() == itemOnBoard.getItemDamage())
    		{
    			return tempStack;
    		}
    	}
    	
    	return null;
	}
    
	@Override
	public void onEntityCollidedWithBlock( World world, int i, int j, int k, Entity entity )
	{
		// don't collect items on the client, as it's dependent on the state of the inventory

		if ( !world.isRemote )
		{
			if ( entity instanceof EntityItem )
			{
				OnEntityItemCollidedWithBlock( world, i, j, k, (EntityItem)entity );
			}
		}		
	}
	
	private void OnEntityItemCollidedWithBlock(World world, int i, int j, int k, EntityItem entityItem)
	{
		float collisionBoxHeight = 2/16F;
		
		List collisionList = null;

		// check for items within the collection zone

		collisionList = world.getEntitiesWithinAABB(EntityItem.class,
				AxisAlignedBB.getAABBPool().getAABB((float) i, (float) j + collisionBoxHeight, (float) k,
						(float) (i + 1), (float) j + collisionBoxHeight + 0.05F, (float) (k + 1)));

		if (collisionList != null && collisionList.size() > 0)
		{
			TileEntity tileEnt = world.getBlockTileEntity(i, j, k);

			if (!(tileEnt instanceof IInventory)) {
				return;
			}
			
			IInventory inventoryEntity = (IInventory) tileEnt;
			
			

			for (int listIndex = 0; listIndex < collisionList.size(); listIndex++) {
				EntityItem targetEntityItem = (EntityItem) collisionList.get(listIndex);
				
				if ( !inventoryEntity.isStackValidForSlot(0, targetEntityItem.getEntityItem() ))
				{
					return;
				}
				
				if (!targetEntityItem.isDead) {
					if (FCUtilsInventory.AddItemStackToInventoryInSlotRange(inventoryEntity, targetEntityItem.getEntityItem(),1,1)) {
						world.playSoundEffect((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "random.pop",
								0.25F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);

						targetEntityItem.setDead();
					}
				}
			}
		}
	}
	
    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par5, float par6, int par7)
    {
    	SCTileEntityChoppingBoard choppingBoard = (SCTileEntityChoppingBoard) world.getBlockTileEntity(x, y, z);
    	
        if (!world.isRemote)
        {
            int var8 = this.quantityDroppedWithBonus(par7, world.rand);

            for (int var9 = 0; var9 < var8; ++var9)
            {
                if (world.rand.nextFloat() <= par6)
                {
                    int var10 = this.idDropped(par5, world.rand, par7);

                    if (var10 > 0)
                    {
                        this.dropBlockAsItem_do(world, x, y, z, new ItemStack(var10, 1, choppingBoard.getWoodType()));
                    }
                }
            }
        }
    }
	
	@Override
    public void breakBlock( World world, int i, int j, int k, int iBlockID, int iMetadata )
    {
        FCUtilsInventory.EjectInventoryContents( world, i, j, k, (IInventory)world.getBlockTileEntity( i, j, k ) );

        super.breakBlock( world, i, j, k, iBlockID, iMetadata );
    }
	
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entity, ItemStack stack)
    {
        int setMeta = ((MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3)) % 4;
        
        world.setBlockMetadataWithNotify(i, j, k, setMeta);
        
        SCTileEntityChoppingBoard choppingBoard = (SCTileEntityChoppingBoard) world.getBlockTileEntity(i, j, k);
        
        choppingBoard.setWoodType(stack.getItemDamage());

    }
    
	@Override
    public int onBlockPlaced( World world, int i, int j, int k, int iFacing, float fClickX, float fClickY, float fClickZ, int iMetadata )
    {		
        return SetFacing( iMetadata, iFacing ); 
    }
	
//	@Override
//    public int PreBlockPlacedBy( World world, int i, int j, int k, int iMetadata, EntityLiving entity ) 
//	{
//		int iFacing = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed( entity );
//
//		return SetFacing( iMetadata, iFacing );
//	}
	
	@Override
    public boolean canPlaceBlockAt( World world, int i, int j, int k )
    {
        if ( !FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing( world, i, j - 1, k, 1, true ) )
		{
			return false;
		}
		
        return super.canPlaceBlockAt( world, i, j, k );
    }
	
	@Override
    public void onNeighborBlockChange( World world, int i, int j, int k, int iBlockID )
    {    	
		SCTileEntityChoppingBoard choppingBoard = (SCTileEntityChoppingBoard) world.getBlockTileEntity(i, j, k);
		
        if ( !FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing( world, i, j - 1, k, 1, true ) )
        {
            dropBlockAsItem( world, i, j, k, choppingBoard.getWoodType(), 0 );
            
            world.setBlockToAir( i, j, k );
        }
    }
	
    public static int getDirection(int metadata)
    {
        return (metadata & 3);
    }
	

    //------------- Render ------------//
    
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }

	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int iNeighborI, int iNeighborJ, int iNeighborK, int iSide)
	{
		return true;
	}
    
	@Override
	public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess blockAccess, int i, int j, int k) {
		int dir = getDirection(blockAccess.getBlockMetadata(i, j, k));
		
		if (dir == 0 || dir == 2)
		{
			return getBoardBounds(7, 2, 6);
		}
		else return getBoardBounds(6, 2, 7);
	}
	
	private AxisAlignedBB getBoardBounds(int i, int j, int k)
	{
    	
		AxisAlignedBB box = AxisAlignedBB.getAABBPool().getAABB( 
			8/16D - i/16D, 0.0D, 8/16D - k/16D, 
			8/16D + i/16D, j /16D, 8/16D + k/16D);
		
		return box;
	}
	
	private Icon[] woodIcon = new Icon[7];
    
	@Override
	public void registerIcons(IconRegister register) {
		
		woodIcon[0] = register.registerIcon("wood");
		woodIcon[1] = register.registerIcon("wood_spruce");
		woodIcon[2] = register.registerIcon("wood_birch");
		woodIcon[3] = register.registerIcon("wood_jungle");
		woodIcon[4] = register.registerIcon("fcBlockPlanks_blood");
		woodIcon[5] = register.registerIcon("SCBlockPackedBamboo_side");
		woodIcon[6] = register.registerIcon("SCBlockPackedBambooStripped_side");
		
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {
		
		SCTileEntityChoppingBoard choppingBoard = (SCTileEntityChoppingBoard) blockAccess.getBlockTileEntity(x, y, z);
		
		int type = choppingBoard.getWoodType();
		
		return blockIcon = woodIcon[type];
	}
	
	@Override
	public Icon getIcon(int par1, int par2) {
		
		return blockIcon;
	}
	
	
//	@Override
//	public Icon getIcon(int side, int meta) {
//		if (meta < 4)
//		{
//			return woodIcon[0];
//		}
//		else if (meta < 8)
//		{
//			return woodIcon[1];
//		}
//		else if (meta < 12)
//		{
//			return woodIcon[2];
//		}
//		else
//		{
//			return woodIcon[3];
//		}
//	}
	
	@Override
    public boolean RenderBlock( RenderBlocks renderBlocks, int i, int j, int k )
    {
		renderBlocks.setRenderBounds(GetBlockBoundsFromPoolBasedOnState(renderBlocks.blockAccess, i, j, k));
		
		setTextureRotation(renderBlocks, i, j, k);
    	renderBlocks.renderStandardBlock(this, i, j, k);
    	
    	renderBlocks.ClearUvRotation();
    	
    	
    	SCTileEntityChoppingBoard choppingBoard = (SCTileEntityChoppingBoard)renderBlocks.blockAccess.getBlockTileEntity( i, j, k );    	
    	ItemStack itemStack = choppingBoard.getKnifeStack();
    	Item item;
    	
    	choppingBoard.setItemHasSpecialRenderer(false);
    	
    	if (itemStack != null)
    	{
    		item = itemStack.getItem();
    		
    		if (hasSpecialRenderer(item) )
            {
            	choppingBoard.setItemHasSpecialRenderer(true);
            }
    	}

    	return true;
    }

	private void setTextureRotation(RenderBlocks renderBlocks, int i, int j, int k) {
		int dir = getDirection(renderBlocks.blockAccess.getBlockMetadata(i, j, k));
		
		if (dir == 2) {
			renderBlocks.SetUvRotateTop(3);
			renderBlocks.SetUvRotateBottom(3);
		}
		else if (dir == 1) {
			renderBlocks.SetUvRotateTop(1);
			renderBlocks.SetUvRotateBottom(1);
		}
		else if (dir == 3) {
			renderBlocks.SetUvRotateTop(2);
			renderBlocks.SetUvRotateBottom(2);
		}

	}
	
	@Override
	public void RenderBlockAsItem(RenderBlocks renderBlocks, int iItemDamage, float fBrightness) {
	
//		renderBlocks.setRenderBounds(getBoardBounds(7, 2, 6));
//		FCClientUtilsRender.RenderInvBlockWithMetadata(renderBlocks, this, -0.5F, -0.5F, -0.5F, iItemDamage);
		
		renderBlocks.renderBlockAsItem(this, iItemDamage, fBrightness);
	}

}
