package com.sidden.creepository.registry;


import com.sidden.creepository.Creepository;
import com.sidden.creepository.block.entity.KegBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CreepositoryBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Creepository.MOD_ID);

    public static final Supplier<BlockEntityType<KegBlockEntity>> KEG = BLOCK_ENTITIES.register("keg",
            () -> BlockEntityType.Builder.of(KegBlockEntity::new, CreepositoryBlocks.KEG.get()).build(null));

    public static void init(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
