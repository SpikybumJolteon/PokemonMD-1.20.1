package net.spikybumjolteon.pokemonmd.common.core.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;

public class ModBerry extends ItemNameBlockItem
{
    private float poketamableHealAmount;

    public ModBerry(Block pBlock, Item.Properties pProperties, float pokemonHealAmount)
    {
        super(pBlock, pProperties);

        poketamableHealAmount = pokemonHealAmount;
    }

    public float getPokemonHealAmount()
    {
        return poketamableHealAmount;
    }
}