package net.spikybumjolteon.pokemonmd.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.spikybumjolteon.pokemonmd.common.core.ModBlocks;
import net.spikybumjolteon.pokemonmd.common.core.ModRegistry;
import net.spikybumjolteon.pokemonmd.common.core.block.OranianBerryCropBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper exFileHelper) {
        super(generator.getPackOutput(), ModRegistry.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.PACKED_AURUM_DUST);

        makeOranianBerryCrop((CropBlock) ModBlocks.ORANIAN_BERRY_CROP.get(), "oranian_berry_stage", "oranian_berry_stage");
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    public void makeOranianBerryCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> oranianBerryStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] oranianBerryStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(((OranianBerryCropBlock) block).getAgeProperty()),
                new ResourceLocation(ModRegistry.MOD_ID, "block/" + textureName + state.getValue(((OranianBerryCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }
}
