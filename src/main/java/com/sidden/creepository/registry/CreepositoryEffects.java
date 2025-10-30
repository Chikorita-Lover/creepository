package com.sidden.creepository.registry;

import com.sidden.creepository.Creepository;
import com.sidden.creepository.effect.SugarCraveEffect;
import com.sidden.creepository.effect.SugarRushEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreepositoryEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Creepository.MOD_ID);

    public static final Holder<MobEffect> SUGAR_RUSH = MOB_EFFECTS.register("sugar_rush",
            () -> new SugarRushEffect(MobEffectCategory.NEUTRAL, 0xffeadb)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            ResourceLocation.fromNamespaceAndPath(Creepository.MOD_ID, "sugar_rush"), 0.8f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE,
                            ResourceLocation.fromNamespaceAndPath(Creepository.MOD_ID, "sugar_rush"), 0.5f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final Holder<MobEffect> SUGAR_CRAVE = MOB_EFFECTS.register("sugar_crave",
            () -> new SugarCraveEffect(MobEffectCategory.HARMFUL, 0x4e281d));


    public static void init(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}