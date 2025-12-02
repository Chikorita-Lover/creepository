package com.sidden.creepository.registry;

import com.sidden.creepository.Creepository;
import com.sidden.creepository.item.AgedCheeseSliceItem;
import com.sidden.creepository.item.ChocolateEggItem;
import com.sidden.creepository.item.SoftCheeseSliceItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreepositoryItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Creepository.MOD_ID);

    public static final DeferredItem<Item> CHOCOLATE = ITEMS.register("chocolate",
            () -> new Item(new Item.Properties().food(CreepositoryFoods.CHOCOLATE)));

    public static final DeferredItem<Item> CHOCOLATE_EGG = ITEMS.register("chocolate_egg",
            () -> new ChocolateEggItem(new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> SOFT_CHEESE_SLICE = ITEMS.register("soft_cheese_slice",
            () -> new SoftCheeseSliceItem(new Item.Properties().food(CreepositoryFoods.SOFT_CHEESE_SLICE)));

    public static final DeferredItem<Item> AGED_CHEESE_SLICE = ITEMS.register("aged_cheese_slice",
            () -> new AgedCheeseSliceItem(new Item.Properties().food(CreepositoryFoods.AGED_CHEESE_SLICE)));

    public static final DeferredItem<Item> CHOCKEN_SPAWN_EGG = ITEMS.register("chocken_spawn_egg",
            () -> new DeferredSpawnEggItem(CreepositoryEntities.CHOCKEN, 0x8f4e30, 0xdca37a,
                    new Item.Properties()));

    public static void init(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}