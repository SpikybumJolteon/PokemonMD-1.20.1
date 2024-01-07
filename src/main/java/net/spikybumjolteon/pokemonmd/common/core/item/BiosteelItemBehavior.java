package net.spikybumjolteon.pokemonmd.common.core.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class BiosteelItemBehavior
{
    public static void biosteelRegenerateDurablity(Level worldIn, ItemStack stack)
    {
        if(worldIn.getDayTime() % 500 == 0)
        {
            if(stack.getDamageValue() > 0 && stack.getDamageValue() < stack.getMaxDamage())
                stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }
}