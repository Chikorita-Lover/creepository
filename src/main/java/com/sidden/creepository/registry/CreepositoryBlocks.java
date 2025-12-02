package com.sidden.creepository.registry;

import com.sidden.creepository.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;
public class CreepositoryBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(com.sidden.creepository.Creepository.MOD_ID);


   public static final DeferredBlock<Block> CHOCOLATE_BLOCK = registerBlock("chocolate_block",
          () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD)));

    public static final DeferredBlock<Block> PUDDING = registerPuddingBlock("pudding",
            ()-> new PuddingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));

    public static final DeferredBlock<Block> KEG = registerBlock("keg",
            () -> new KegBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));

    public static final DeferredBlock<Block> PLANT_POT = registerBlock("plant_pot",
            ()-> new PlantPotBock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)));

    public static final DeferredBlock<Block> SCULPTURE = registerBlock("sculpture",
            ()-> new SculptureBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredBlock<Block> CAMERA = registerBlock("camera",
            ()-> new CameraBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)));

    public static final DeferredBlock<Block> SOFT_CHEESE = registerBlock("soft_cheese",
            ()-> new SoftCheeseBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).randomTicks()));

    public static final DeferredBlock<Block> AGED_CHEESE = registerBlock("aged_cheese",
            ()-> new AgedCheeseBlock(BlockBehaviour.Properties.ofFullCopy(SOFT_CHEESE.get()).randomTicks()));

    private static Block stair(DeferredBlock<Block> baseBlock) {
        return new StairBlock(baseBlock.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(baseBlock.get()));
    }

    private static Block flowerPot(Block potted) {
        return new FlowerPotBlock(potted, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerPuddingBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerPuddingBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerPureBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        CreepositoryItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    private static <T extends Block> void registerPuddingBlockItem(String name, DeferredBlock<T> block) {
        CreepositoryItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().stacksTo(16)));
    }
    public static void init(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}