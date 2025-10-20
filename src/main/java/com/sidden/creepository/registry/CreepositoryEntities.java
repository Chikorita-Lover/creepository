package com.sidden.creepository.registry;

import com.sidden.creepository.Creepository;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreepositoryEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Creepository.MOD_ID);


    public static void init(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}