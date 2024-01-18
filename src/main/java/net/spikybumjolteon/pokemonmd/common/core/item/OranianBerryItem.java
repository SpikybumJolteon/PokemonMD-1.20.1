package net.spikybumjolteon.pokemonmd.common.core.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.IPlantable;
import net.spikybumjolteon.pokemonmd.common.core.ModBlocks;
import net.spikybumjolteon.pokemonmd.common.core.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OranianBerryItem extends ModBerry
{
    public static final FoodProperties ORANIAN_BERRY_FOOD = (new FoodProperties.Builder()).nutrition(1).fast()
            .saturationMod(0.1F).alwaysEat().build();
    public static final float pokemonHealAmount = 6f;

    public OranianBerryItem()
    {
        super(ModBlocks.ORANIAN_BERRY_CROP.get(), ModItems.defaultProps().food(ORANIAN_BERRY_FOOD), pokemonHealAmount);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.pokemonmd.oranian_berry_tooltip"));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if(this.isEdible())
        {
            if (pLivingEntity.getHealth() < pLivingEntity.getMaxHealth() && pLivingEntity.getHealth() > 0);
            {
                pLivingEntity.heal(1F);

                pLivingEntity.eat(pLevel, pStack);
            }
            return pLivingEntity.eat(pLevel, pStack);
        }

        return pStack;
    }
}