package com.sidden.creepository.client.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record AgingRecipeInput(ItemStack mainInput, ItemStack agingInput) implements RecipeInput {
    @Override
    public ItemStack getItem(int i) {
        if (i == 0) return mainInput;
        if (i == 1) return  agingInput;

        return ItemStack.EMPTY;
    }

    @Override
    public int size() {
        return 2;
    }
}
