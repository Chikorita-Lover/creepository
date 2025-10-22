package com.sidden.creepository.registry;

import com.sidden.creepository.Creepository;
import com.sidden.creepository.entity.Chocken;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CreepositoryEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Creepository.MOD_ID);

    public static final Supplier<EntityType<Chocken>> CHOCKEN =
            ENTITY_TYPES.register("chocken", () -> EntityType.Builder.of(Chocken::new, MobCategory.CREATURE)
                    .sized(0.4F, 0.7F).eyeHeight(0.644F).build("chocken"));

    public static void init(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}