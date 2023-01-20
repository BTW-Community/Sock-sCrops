// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.5.2
// Paste this class into your mod and call render() in your Entity Render class
// Note: You may need to adjust the y values of the 'setRotationPoint's

package net.minecraft.src;

public class SCModelEntityTropical extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer body;
	private final ModelRenderer tailfin;
	private final ModelRenderer leftFin;
	private final ModelRenderer rightFin;

	public SCModelEntityTropical() {
		textureWidth = 32;
		textureHeight = 32;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -1.5F, 0.0F);
		bone.addChild(body);
		this.body.setTextureOffset(0, 0).addBox(-1.0F, -1.5F, -3.0F, 2, 3, 6, 0.0F);
		this.body.setTextureOffset(10, -6).addBox(0.0F, -5.5F, -2.9992F, 0, 4, 6, 0.0F);

		tailfin = new ModelRenderer(this);
		tailfin.setRotationPoint(0.0F, -0.5F, 3.0F);
		body.addChild(tailfin);
		setRotation(tailfin, 0.0F, -0.48F, 0.0F);
		this.tailfin.setTextureOffset(24, -4).addBox(0.0F, -1.0F, 0.0F, 0, 3, 4, 0.0F);

		leftFin = new ModelRenderer(this);
		leftFin.setRotationPoint(1.0F, -0.5F, 0.0F);
		body.addChild(leftFin);
		setRotation(leftFin, 0.0F, -0.6109F, 0.0F);
		this.leftFin.setTextureOffset(2, 12).addBox(0.0F, 0.0F, 0.0F, 2, 2, 0, 0.0F);

		rightFin = new ModelRenderer(this);
		rightFin.setRotationPoint(-1.0F, -0.5F, 0.0F);
		body.addChild(rightFin);
		setRotation(rightFin, 0.0F, 0.6109F, 0.0F);
		this.rightFin.setTextureOffset(2, 16).addBox(-2.0F, 0.0F, 0.0F, 2, 2, 0, 0.0F);
	}

	/**
	* Sets the models various rotation angles then renders the model.
	*/
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bone.render(f5);
	}

	/**
	* Sets the model's various rotation angles. For bipeds, f and f1 are used for animating the movement of arms
	* and legs, where f represents the time(so that arms and legs swing back and forth) and f1 represents how
	* "far" arms and legs can swing at most.
	*/
	@Override
    public void setRotationAngles(float ticks, float playerDistance, float f2, float f3, float f4, float f5, Entity entity) {
		
		float speed = 0.3F;
		
		if (playerDistance != -1)
		{
			body.setRotationPoint(8.0F, -1.5F, 0.0F);
			this.bone.rotateAngleY = (float) Math.toRadians(ticks*4);
			
			this.body.rotateAngleY = MathHelper.sin(ticks * speed) * (float)Math.PI/8;
			
			this.rightFin.rotateAngleY = MathHelper.cos(ticks * speed) * (float)Math.PI/8;
			this.leftFin.rotateAngleY = -this.rightFin.rotateAngleY;
			
			this.tailfin.rotateAngleY = -this.body.rotateAngleY;
		}
		else
		{
			body.setRotationPoint(0.0F, 0.0F, 0.0F);
			bone.setRotationPoint(0, -1F, 0);	
			this.bone.rotateAngleZ = -(float)Math.PI/2;
			this.bone.rotateAngleX = -(f3 / (180F / (float)Math.PI)) - (float)Math.PI/2;
		}
		

    }
	
	/**
	*	Sets the rotation of a ModelRenderer. Only called if the ModelRenderer has a rotation
	*/
    public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}