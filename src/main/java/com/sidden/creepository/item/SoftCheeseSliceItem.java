package com.sidden.creepository.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SoftCheeseSliceItem extends Item {
    public SoftCheeseSliceItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);

        if (!level.isClientSide) {

            for(MobEffectInstance effect : livingEntity.getActiveEffects()) {
                if (1 == 1) {
                    livingEntity.removeEffect(effect.getEffect());
                }
            }
        }

        return result;
    }

}
