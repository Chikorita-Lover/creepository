package com.sidden.creepository.client.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sidden.creepository.registry.CreepositoryRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public record AgingRecipe(Ingredient mainInput, Ingredient agingInput, ItemStack output) implements Recipe<AgingRecipeInput> {


    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(mainInput);
        list.add(agingInput);
        return list;
    }

    @Override
    public boolean matches(AgingRecipeInput agingRecipeInput, Level level) {
        if (level.isClientSide()) return false;

        return mainInput.test(agingRecipeInput.getItem(0));
    }

    @Override
    public ItemStack assemble(AgingRecipeInput agingRecipeInput, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return com.sidden.creepository.registry.CreepositoryRecipes.KEG_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return CreepositoryRecipes.KEG_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<AgingRecipe> {
        public static final MapCodec<AgingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(AgingRecipe::mainInput),
                Ingredient.CODEC_NONEMPTY.fieldOf("ager").forGetter(AgingRecipe::agingInput),
                ItemStack.CODEC.fieldOf("result").forGetter(AgingRecipe::output)
        ).apply(inst, AgingRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, AgingRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, AgingRecipe::mainInput,
                        Ingredient.CONTENTS_STREAM_CODEC, AgingRecipe::agingInput,
                        ItemStack.STREAM_CODEC, AgingRecipe::output,
                        AgingRecipe::new);

        @Override
        public MapCodec<AgingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, AgingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
