package net.spikybumjolteon.pokemonmd.common.core;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.spikybumjolteon.pokemonmd.common.core.item.ModFuelItem;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ModRegistry.MOD_ID);

    public static final RegistryObject<Item> AURUM_DUST = register( "aurum_dust");
    public static final RegistryObject<Item> AURUM_ROD = register( "aurum_rod");
    public static final RegistryObject<Item> INFERNO_FUEL_ROD = registerFuel( "inferno_fuel_rod", 1600);

    /* -----------------------*/
    private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> sup) {
        return ITEMS.register(name, sup);
    }

    private static RegistryObject<Item> register(final String name) {
        return register(name, () -> new Item(ModItems.defaultProps()));
    }

    public static Item.Properties defaultProps() {
        return new Item.Properties();
    }

    public static Item.Properties defaultUnstackableProps() {
        return defaultProps().stacksTo(1);
    }

    private static RegistryObject<Item> registerFuel(final String name, int burnTime) {
        return register(name, () -> new ModFuelItem(defaultProps(), burnTime));
    }
/*
    public static Item.Properties defaultSummonProps()
    {
        return defaultUnstackableProps().defaultMaxDamage(PoketamableSummonItem.maximumDamage);
    }

    private static RegistryObject<Item> registerFood(final String name, FoodProperties food) {
        return register(name, () -> new Item(defaultProps().food(food)));
    }
    */
}