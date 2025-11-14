package com.sidden.creepository.entity.client.renderer;


import com.sidden.creepository.Creepository;
import com.sidden.creepository.entity.Chocken;
import com.sidden.creepository.entity.client.model.ChockenModel;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Chicken;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChockenRenderer extends MobRenderer<Chocken, ChockenModel<Chocken>> {
    private static final ResourceLocation CHOCKEN_LOCATION = ResourceLocation.fromNamespaceAndPath(Creepository.MOD_ID,"textures/entity/chocken.png");

    public ChockenRenderer(EntityRendererProvider.Context context) {
        super(context, new ChockenModel(context.bakeLayer(ChockenModel.LAYER_LOCATION)), 0.3F);
    }

    public ResourceLocation getTextureLocation(Chocken entity) {
        return CHOCKEN_LOCATION;
    }

    protected float getBob(Chocken livingBase, float partialTicks) {
        float f = Mth.lerp(partialTicks, livingBase.oFlap, livingBase.flap);
        float f1 = Mth.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }
}