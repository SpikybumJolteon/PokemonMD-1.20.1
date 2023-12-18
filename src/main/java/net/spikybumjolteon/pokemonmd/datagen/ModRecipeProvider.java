package net.spikybumjolteon.pokemonmd.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.spikybumjolteon.pokemonmd.common.core.ModBlocks;
import net.spikybumjolteon.pokemonmd.common.core.ModItems;
import net.spikybumjolteon.pokemonmd.common.core.ModRegistry;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static net.spikybumjolteon.pokemonmd.common.core.ModRegistry.RL;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn.getPackOutput());
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreSmelting(consumer, List.of(ModItems.AURUM_DUST.get()), RecipeCategory.MISC, Items.GOLD_NUGGET, 0.1f, 200, "gold_nugget_from_aurum_dust");
        oreBlasting(consumer, List.of(ModItems.AURUM_DUST.get()), RecipeCategory.MISC, Items.GOLD_NUGGET, 0.1f, 100, "gold_nugget_from_aurum_dust");

        shapeless(ModItems.AURUM_DUST.get(), 9, ModItems.AURUM_DUST.get(),
                ModBlocks.PACKED_AURUM_DUST.get()
        ).save(consumer, RL("aurum_dust_from_packed_aurum_dust"));

        shaped(ModBlocks.PACKED_AURUM_DUST.get(), 1, ModItems.AURUM_DUST.get(),
                "AAA/AAA/AAA",
                'A', ModItems.AURUM_DUST.get()
        ).save(consumer, RL("packed_aurum_dust_from_aurum_dust"));

        shaped(ModItems.AURUM_ROD.get(), 1, ModItems.AURUM_DUST.get(),
                "A/A/A",
                'A', ModItems.AURUM_DUST.get()
        ).save(consumer);

        shaped(ModItems.INFERNO_FUEL_ROD.get(), 1, ModItems.AURUM_DUST.get(),
                "P/B/A",
                'P', Items.PAPER,
                'B', Items.BLAZE_POWDER,
                'A', ModItems.AURUM_ROD.get()
        ).save(consumer);
    }

    private String getId(String s) {
        return RL(s).toString();
    }

    private <T extends ItemLike> String safeName(T required) {
        ResourceLocation key = ForgeRegistries.ITEMS.getKey(required.asItem());
        return key == null ? "" : key.getPath().replace('/', '_');
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer, ModRegistry.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    /*
    Below methods are taken from Pneumaticraft
     */
    private <T extends ItemLike, U extends ShapedRecipeBuilder> U genericShaped(U builder, T result, T required, String pattern, Object... keys) {
        Arrays.stream(pattern.split("/")).forEach(builder::pattern);
        for (int i = 0; i < keys.length; i += 2) {
            Object v = keys[i + 1];
            if (v instanceof TagKey<?>) {
                //noinspection unchecked
                builder.define((Character) keys[i], (TagKey<Item>) v);
            } else if (v instanceof ItemLike) {
                builder.define((Character) keys[i], (ItemLike) v);
            } else if (v instanceof Ingredient) {
                builder.define((Character) keys[i], (Ingredient) v);
            } else {
                throw new IllegalArgumentException("bad type for recipe ingredient " + v);
            }
        }
        builder.unlockedBy("has_" + safeName(required), has(required));
        return builder;
    }

    private <T extends ItemLike> ShapedRecipeBuilder shaped(T result, int count, T required, String pattern, Object... keys) {
        return genericShaped(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, count), result, required, pattern, keys);
    }

    private <T extends ItemLike> ShapedRecipeBuilder shaped(T result, T required, String pattern, Object... keys) {
        return shaped(result, 1, required, pattern, keys);
    }

    private <T extends ItemLike> ShapelessRecipeBuilder shapeless(T result, T required, Object... ingredients) {
        return shapeless(result, 1, required, ingredients);
    }

    private <T extends ItemLike> ShapelessRecipeBuilder shapeless(T result, int count, T required, Object... ingredients) {
        ShapelessRecipeBuilder b = ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, count);
        for (Object v : ingredients) {
            if (v instanceof TagKey<?>) {
                //noinspection unchecked
                b.requires((TagKey<Item>) v);
            } else if (v instanceof ItemLike) {
                b.requires((ItemLike) v);
            } else if (v instanceof Ingredient) {
                b.requires((Ingredient) v);
            } else {
                throw new IllegalArgumentException("bad type for recipe ingredient " + v);
            }
        }
        b.unlockedBy("has_" + safeName(required), has(required));
        return b;
    }
}
