package net.spikybumjolteon.pokemonmd.common.core.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;
import net.spikybumjolteon.pokemonmd.common.core.ModItems;
import net.spikybumjolteon.pokemonmd.common.core.ModRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier BIOSTEEL = TierSortingRegistry.registerTier(
            new ForgeTier(2, 250, 6f, 2f, 16,
                    BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.REGENERATIVE_BIOSTEEL.get())),
            new ResourceLocation(ModRegistry.MOD_ID, "biosteel"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND));
}