package net.spikybumjolteon.pokemonmd.common.core.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.spikybumjolteon.pokemonmd.common.core.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BiosteelPickaxeItem extends PickaxeItem
{
    public BiosteelPickaxeItem()
    {
        super(ModToolTiers.BIOSTEEL, 1, -2.8F, ModItems.defaultUnstackableProps());
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