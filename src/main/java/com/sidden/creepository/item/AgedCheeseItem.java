package com.sidden.creepository.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Random;

public class AgedCheeseItem extends Item {
    public AgedCheeseItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);

        if (!level.isClientSide) {

            for(MobEffectInstance effect : livingEntity.getActiveEffects()) {
                if (1 == 1 && new Random().nextFloat(0, 5) == 0 ) {
                    livingEntity.removeEffect(effect.getEffect());
                }
            }
        }

        return result;
    }
}
