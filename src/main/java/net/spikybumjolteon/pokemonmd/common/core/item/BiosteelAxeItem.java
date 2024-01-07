package net.spikybumjolteon.pokemonmd.common.core.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.spikybumjolteon.pokemonmd.common.core.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BiosteelAxeItem extends AxeItem
{
    public BiosteelAxeItem()
    {
        super(ModToolTiers.BIOSTEEL, 6.0F, -3.1F, ModItems.defaultUnstackableProps());
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        BiosteelItemBehavior.biosteelRegenerateDurablity(pLevel, pStack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.pokemonmd.biosteel_tooltip"));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}