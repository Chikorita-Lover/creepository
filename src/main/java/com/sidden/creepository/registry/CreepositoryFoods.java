package com.sidden.creepository.registry;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class CreepositoryFoods {
    public static FoodProperties CHOCOLATE = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).fast().effect(() -> new MobEffectInstance(CreepositoryEffects.SUGAR_RUSH, 1200, 0), 1F).build();
    public static FoodProperties CHOCOLATE_EGG = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).fast().build();

}
