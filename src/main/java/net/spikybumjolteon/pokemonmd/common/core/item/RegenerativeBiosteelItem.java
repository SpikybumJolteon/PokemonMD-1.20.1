package net.spikybumjolteon.pokemonmd.common.core.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.spikybumjolteon.pokemonmd.common.core.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RegenerativeBiosteelItem extends Item
{
    public RegenerativeBiosteelItem()
    {
        super(ModItems.defaultProps());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.pokemonmd.regenerative_biosteel_tooltip"));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}