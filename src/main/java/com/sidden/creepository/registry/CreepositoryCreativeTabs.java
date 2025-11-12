package com.sidden.creepository.registry;

import com.sidden.creepository.Creepository;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class CreepositoryCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Creepository.MOD_ID);

    public static final Supplier<CreativeModeTab> CREEPOSITORY = CREATIVE_MODE_TAB.register("creepository",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Blocks.GRASS_BLOCK))
                    .title(Component.translatable("creativetab.creepository.creepository"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(CreepositoryBlocks.SCULPTURE);
                        output.accept(CreepositoryBlocks.PLANT_POT);
                        output.accept(CreepositoryBlocks.KEG);
                        output.accept(CreepositoryBlocks.CHOCOLATE_BLOCK);
                        output.accept(CreepositoryItems.CHOCOLATE);
                        output.accept(CreepositoryItems.CHOCOLATE_EGG);
                        output.accept(CreepositoryItems.CHOCKEN_SPAWN_EGG);

                    }).build());

    public static void init(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
