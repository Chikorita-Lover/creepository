package com.sidden.creepository.datagen;

import com.sidden.creepository.Creepository;
import com.sidden.creepository.registry.CreepositoryBlocks;
import com.sidden.creepository.registry.CreepositoryItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class CustomRecipeProvider extends RecipeProvider {
    public CustomRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    private static ResourceLocation crafting(String path) {
        return ResourceLocation.fromNamespaceAndPath(Creepository.MOD_ID, "crafting/" + path);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CreepositoryItems.CHOCOLATE, 2).requires(Items.COCOA_BEANS).requires(Items.COCOA_BEANS).requires(Items.COCOA_BEANS).requires(Items.SUGAR).group(getItemName(CreepositoryItems.CHOCOLATE)).unlockedBy(getHasName(Items.COCOA_BEANS), has(Items.COCOA_BEANS)).save(recipeOutput, crafting(getItemName(CreepositoryItems.CHOCOLATE)));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CreepositoryItems.CHOCOLATE, 9).requires(CreepositoryBlocks.CHOCOLATE_BLOCK).group(getItemName(CreepositoryItems.CHOCOLATE)).unlockedBy(getHasName(CreepositoryBlocks.CHOCOLATE_BLOCK), has(CreepositoryBlocks.CHOCOLATE_BLOCK)).save(recipeOutput, crafting("chocolate_from_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CreepositoryItems.CHOCOLATE, 3).requires(CreepositoryItems.CHOCOLATE_EGG).group(getItemName(CreepositoryItems.CHOCOLATE)).unlockedBy(getHasName(CreepositoryItems.CHOCOLATE_EGG), has(CreepositoryItems.CHOCOLATE_EGG)).save(recipeOutput, crafting("chocolate_from_egg"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CreepositoryBlocks.CHOCOLATE_BLOCK).define('#', CreepositoryItems.CHOCOLATE).pattern("###").pattern("###").pattern("###").unlockedBy(getHasName(CreepositoryItems.CHOCOLATE), has(CreepositoryItems.CHOCOLATE)).save(recipeOutput, crafting(getItemName(CreepositoryBlocks.CHOCOLATE_BLOCK)));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, CreepositoryBlocks.PLANT_POT).define('#', Items.BRICK).define('X', Items.DIRT).pattern("#X#").pattern("###").unlockedBy(getHasName(Items.BRICK), has(Items.BRICK)).save(recipeOutput, crafting(getItemName(CreepositoryBlocks.PLANT_POT)));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, CreepositoryBlocks.SCULPTURE).define('#', Items.STONE).pattern("###").pattern(" # ").pattern("###").unlockedBy(getHasName(Items.STONE), has(Items.STONE)).save(recipeOutput, crafting(getItemName(CreepositoryBlocks.SCULPTURE)));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, CreepositoryBlocks.PUDDING).define('C', CreepositoryItems.CHOCOLATE).define('W', Items.WHEAT).define('E', Items.EGG).pattern("CCC").pattern("ECE").pattern("WWW").unlockedBy(getHasName(CreepositoryItems.CHOCOLATE), has(CreepositoryItems.CHOCOLATE)).save(recipeOutput, crafting(getItemName(CreepositoryBlocks.PUDDING)));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CreepositoryBlocks.SOFT_CHEESE).requires(Items.MILK_BUCKET).requires(Items.RED_MUSHROOM).requires(Items.BROWN_MUSHROOM).group(getItemName(CreepositoryBlocks.SOFT_CHEESE)).unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET)).save(recipeOutput, crafting(getItemName(CreepositoryBlocks.SOFT_CHEESE)));
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, CreepositoryBlocks.SOFT_CHEESE).define('#', CreepositoryItems.SOFT_CHEESE_SLICE).pattern("##").pattern("##").group(getItemName(CreepositoryBlocks.SOFT_CHEESE)).unlockedBy(getHasName(CreepositoryItems.SOFT_CHEESE_SLICE), has(CreepositoryItems.SOFT_CHEESE_SLICE)).save(recipeOutput, crafting("soft_cheese_from_slices"));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, CreepositoryBlocks.AGED_CHEESE).define('#', CreepositoryItems.AGED_CHEESE_SLICE).pattern("##").pattern("##").unlockedBy(getHasName(CreepositoryItems.AGED_CHEESE_SLICE), has(CreepositoryItems.AGED_CHEESE_SLICE)).save(recipeOutput, crafting(getItemName(CreepositoryBlocks.AGED_CHEESE)));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CreepositoryItems.TOMATO_SEEDS).requires(CreepositoryItems.TOMATO).unlockedBy(getHasName(CreepositoryItems.TOMATO), has(CreepositoryItems.TOMATO)).save(recipeOutput, crafting(getItemName(CreepositoryItems.TOMATO_SEEDS)));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CreepositoryItems.HAMBURGER, 2).requires(Items.BREAD).requires(Items.KELP).requires(Items.COOKED_BEEF).requires(CreepositoryItems.TOMATO).requires(CreepositoryItems.AGED_CHEESE_SLICE).unlockedBy(getHasName(Items.COOKED_BEEF), has(Items.COOKED_BEEF)).save(recipeOutput, crafting(getItemName(CreepositoryItems.HAMBURGER)));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CreepositoryItems.CHOCOLATE_MILK).requires(CreepositoryItems.CHOCOLATE).requires(CreepositoryItems.CHOCOLATE).requires(CreepositoryItems.CHOCOLATE).requires(Items.GLASS_BOTTLE).requires(Items.MILK_BUCKET).unlockedBy(getHasName(CreepositoryItems.CHOCOLATE), has(CreepositoryItems.CHOCOLATE)).save(recipeOutput, crafting(getItemName(CreepositoryItems.CHOCOLATE_MILK)));
    }
}
