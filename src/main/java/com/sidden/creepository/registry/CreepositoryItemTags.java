package com.sidden.creepository.registry;

import com.sidden.creepository.Creepository;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CreepositoryItemTags {

    public static final TagKey<Item> CHOCOLATY = create("chocolaty");

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Creepository.MOD_ID, name));
    }
}
