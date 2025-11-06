package com.sidden.creepository.registry;


import com.sidden.creepository.Creepository;
import com.sidden.creepository.client.recipe.AgingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreepositoryRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Creepository.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Creepository.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<AgingRecipe>> KEG_SERIALIZER =
            SERIALIZERS.register("aging", AgingRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<AgingRecipe>> KEG_TYPE =
            TYPES.register("aging", () -> new RecipeType<AgingRecipe>() {
                @Override
                public String toString() {
                    return "aging";
                }
            });


    public static void init(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }

}
