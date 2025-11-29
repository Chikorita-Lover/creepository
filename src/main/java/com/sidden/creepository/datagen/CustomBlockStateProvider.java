package com.sidden.creepository.datagen;

import com.sidden.creepository.Creepository;
import com.sidden.creepository.block.SculptureBlock;
import com.sidden.creepository.registry.CreepositoryBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.HashMap;
import java.util.List;

public class CustomBlockStateProvider extends BlockStateProvider {
    ModelFile.UncheckedModelFile templateTopLeft     = new ModelFile.UncheckedModelFile(modLoc("block/sculpture/template_sculpture_top_left"));
    ModelFile.UncheckedModelFile templateTopRight    = new ModelFile.UncheckedModelFile(modLoc("block/sculpture/template_sculpture_top_right"));
    ModelFile.UncheckedModelFile templateBottomLeft  = new ModelFile.UncheckedModelFile(modLoc("block/sculpture/template_sculpture_bottom_left"));
    ModelFile.UncheckedModelFile templateBottomRight = new ModelFile.UncheckedModelFile(modLoc("block/sculpture/template_sculpture_bottom_right"));
    List<SculptureDesign> designs = SculptureDesign.getDesigns();


    public CustomBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Creepository.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        sculptureBlock();
    }

    private void sculptureBlock() {
        var block = CreepositoryBlocks.SCULPTURE.get();

        ModelFile.UncheckedModelFile baseModel = new ModelFile.UncheckedModelFile(modLoc("block/sculpture/sculpture_base"));

        HashMap<Integer, ModelFile[]> quadAndModels = new HashMap<Integer, ModelFile[]>();

        quadAndModels.put(0, GenerateModelArray("top_right", templateTopRight));
        quadAndModels.put(1, GenerateModelArray("top_left", templateTopLeft));
        quadAndModels.put(2, GenerateModelArray("bottom_right", templateBottomRight));
        quadAndModels.put(3, GenerateModelArray("bottom_left", templateBottomLeft));

        //System.out.println(overlays);

        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);

        for (Direction direction : BlockStateProperties.HORIZONTAL_FACING.getPossibleValues()) {
            int yRot = direction.toYRot() -180 == 0 ? 0 : (int) direction.toYRot() - 180;

            builder.part().modelFile(baseModel).rotationY(yRot).uvLock(true).addModel()
                    .condition(BlockStateProperties.HORIZONTAL_FACING, direction).end();

            //loop for each quad and use hashmap
            for (int i = 0; i < 4; i++) {
                var quadDesign = SculptureBlock.FACE_PATTERNS.get(i);
                ModelFile[] models = quadAndModels.get(i);

                for (SculptureDesign design : designs) {
                    builder.part()
                            .modelFile(models[design.Id])
                            .rotationY(yRot)
                            //.uvLock(true)
                            .addModel()
                            .condition(BlockStateProperties.HORIZONTAL_FACING, direction)
                            .condition(quadDesign, design.Id)
                            .end();
                }
            }
        }

        //inv
        itemModels().cubeAll("sculpture", modLoc("block/sculpture_face"));
    }

    private ModelFile[] GenerateModelArray(String quad, ModelFile.UncheckedModelFile template){
        ModelFile[] arr = new ModelFile[designs.toArray().length];

        for (SculptureDesign design : designs) {
            arr[design.Id] = createOverlay("sculpture_"+ quad + "_" + design.Name,  template, design.Name);
        }

        return arr;
    }

    private ModelFile createOverlay(String name, ModelFile.UncheckedModelFile template, String type) {
        ResourceLocation tex = modLoc("block/sculpture_" + type);
        return models().withExistingParent(name, template.getLocation())
                .texture("texture", tex);
    }
}