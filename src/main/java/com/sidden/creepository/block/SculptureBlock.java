package com.sidden.creepository.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

public class SculptureBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING;


    public static final List<IntegerProperty> FACE_PATTERNS = List.of(
            IntegerProperty.create("carving_tr", 0, 4),
            IntegerProperty.create("carving_tl", 0, 4),
            IntegerProperty.create("carving_br", 0, 4),
            IntegerProperty.create("carving_bl", 0, 4)
    );

    public SculptureBlock(Properties properties) {
        super(properties);
        BlockState blockstate = this.stateDefinition.any().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH);

        for (IntegerProperty patternprop: FACE_PATTERNS) {
            blockstate = blockstate.setValue(patternprop, 0);
        }

        this.registerDefaultState(blockstate);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide()) {
            OptionalInt quad = this.getHitSlot(hitResult, state);
            //TODO test modded pickaxes
            if (stack.is(Tags.Items.MINING_TOOL_TOOLS)) {

                BlockState UpdatedState = state;

                IntegerProperty intProp = FACE_PATTERNS.get(quad.getAsInt());
                int UpdatedValue = state.getValue(intProp).intValue() == 4 ? 0 : state.getValue(intProp).intValue()+1;
                UpdatedState = UpdatedState.setValue(intProp, UpdatedValue);

                Objects.requireNonNull(level).setBlock(pos, UpdatedState, 3);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(UpdatedState));
                return ItemInteractionResult.SUCCESS;

            }
        }
        return ItemInteractionResult.FAIL;
    }

    /*
        below adapted from net.minecraft.world.level.block.ChiseledBookSelfBlock
    */

    private OptionalInt getHitSlot(BlockHitResult hitReselt, BlockState state) {
        return getRelativeHitCoordinatesForBlockFace(hitReselt, state.getValue(HorizontalDirectionalBlock.FACING))
                .map(vec -> {
                    int j = vec.x >= 0.5F ? 0 : 1;
                    int i = vec.y >= 0.5F ? 0 : 1;
                    System.out.println("x: " + vec.x + " y: " + vec.y);
                    // serialisation
                    return OptionalInt.of(j + (i * 2));
                })
                .orElseGet(OptionalInt::empty);
    }

    private static Optional<Vec2> getRelativeHitCoordinatesForBlockFace(BlockHitResult hitResult, Direction face) {
        Direction direction = hitResult.getDirection();
        if (face != direction) {
            return Optional.empty();
        } else {
            BlockPos blockpos = hitResult.getBlockPos().relative(direction);
            Vec3 vec3 = hitResult.getLocation().subtract((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
            double d0 = vec3.x();
            double d1 = vec3.y();
            double d2 = vec3.z();

            return switch (direction) {
                case NORTH -> Optional.of(new Vec2((float)(1.0 - d0), (float)d1));
                case SOUTH -> Optional.of(new Vec2((float)d0, (float)d1));
                case WEST -> Optional.of(new Vec2((float)d2, (float)d1));
                case EAST -> Optional.of(new Vec2((float)(1.0 - d2), (float)d1));
                case DOWN, UP -> Optional.empty();
            };
        }
    }

    /*
        above adapted from net.minecraft.world.level.block.ChiseledBookSelfBlock
    */

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return null;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
        builder.add(
                FACE_PATTERNS.get(0),
                FACE_PATTERNS.get(1),
                FACE_PATTERNS.get(2),
                FACE_PATTERNS.get(3)
        );

    }


    static {
        FACING = HorizontalDirectionalBlock.FACING;
        FACE_PATTERNS.get(0);
        FACE_PATTERNS.get(1);
        FACE_PATTERNS.get(2);
        FACE_PATTERNS.get(3);
    }

}
