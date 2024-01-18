package net.spikybumjolteon.pokemonmd.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.spikybumjolteon.pokemonmd.common.core.ModRegistry;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator generatorIn, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(generatorIn.getPackOutput(), lookupProvider, blockTagsProvider, ModRegistry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

    }

    @SafeVarargs
    private void addItemsToTag(TagKey<Item> tag, Supplier<? extends ItemLike>... items) {
        tag(tag).add(Arrays.stream(items).map(Supplier::get).map(ItemLike::asItem).toArray(Item[]::new));
    }

    @SafeVarargs
    private void appendToTag(TagKey<Item> tag, TagKey<Item>... toAppend) {
        tag(tag).addTags(toAppend);
    }

    @Override
    public String getName() {
        return "PokemonMD Item Tags";
    }

}