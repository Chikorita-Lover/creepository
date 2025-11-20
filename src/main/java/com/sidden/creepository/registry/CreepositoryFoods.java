package com.sidden.creepository.registry;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class CreepositoryFoods {
    public static FoodProperties CHOCOLATE = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1f).fast().effect(() -> new MobEffectInstance(CreepositoryEffects.SUGAR_RUSH, 1200, 0), 1F).build();
    public static FoodProperties SOFT_CHEESE_SLICE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).fast().build();
    public static FoodProperties AGED_CHEESE_SLICE = new FoodProperties.Builder().nutrition(5).saturationModifier(0.5f).fast().build();

}
