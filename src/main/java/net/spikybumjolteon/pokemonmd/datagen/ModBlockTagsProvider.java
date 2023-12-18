package net.spikybumjolteon.pokemonmd.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.spikybumjolteon.pokemonmd.common.core.ModBlocks;
import net.spikybumjolteon.pokemonmd.common.core.ModRegistry;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), lookupProvider, ModRegistry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

//        this.tab(BlockTags.MINEABLE_WITH_PICKAXE)
//                .add(ModBlocks.PACKED_AURUM_DUST.get());

//        this.tag(BlockTags.NEEDS_IRON_TOOL)
//                .add(ModBlocks.PACKED_AURUM_DUST.get());

//        ModBlocks.BLOCKS.getEntries().forEach(ro -> {
//            Block block = ro.get();
//            if (!(block instanceof LiquidBlock) && !(block instanceof AirBlock)) {
//                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
//                tag(BlockTags.NEEDS_IRON_TOOL).add(block);
//            }
//        });
//        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
//                .add(ModBlocks.PACKED_AURUM_DUST.get());

    }

// with thanks to Tropicraft for these helper methods

    @SafeVarargs
    private <T> T[] resolveAll(IntFunction<T[]> creator, Supplier<? extends T>... suppliers) {
        return Arrays.stream(suppliers).map(Supplier::get).toArray(creator);
    }

    @SafeVarargs
    private void createTag(TagKey<Block> tag, Supplier<? extends Block>... blocks) {
        tag(tag).add(resolveAll(Block[]::new, blocks));
    }

    @SafeVarargs
    private void appendToTag(TagKey<Block> tag, TagKey<Block>... toAppend) {
        tag(tag).addTags(toAppend);
    }

    @SafeVarargs
    private void createAndAppend(TagKey<Block> tag, TagKey<Block> to, Supplier<? extends Block>... blocks) {
        createTag(tag, blocks);
        appendToTag(to, tag);
    }

    @Override
    public String getName() {
        return "PokemonMD Block Tags";
    }

}