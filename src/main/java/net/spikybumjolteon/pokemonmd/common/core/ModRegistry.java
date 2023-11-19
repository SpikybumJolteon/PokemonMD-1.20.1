package net.spikybumjolteon.pokemonmd.common.core;

import net.minecraft.resources.ResourceLocation;

public class ModRegistry {
    public static final String MOD_ID = "pokemonmd";

    public static ResourceLocation RL(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
