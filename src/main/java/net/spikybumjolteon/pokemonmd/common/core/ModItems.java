package net.spikybumjolteon.pokemonmd.common.core;

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.spikybumjolteon.pokemonmd.common.core.item.*;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ModRegistry.MOD_ID);

    public static final RegistryObject<Item> AURUM_DUST = register("aurum_dust");
    public static final RegistryObject<Item> AURUM_ROD = register("aurum_rod");
    public static final RegistryObject<Item> INFERNO_FUEL_ROD = registerFuel("inferno_fuel_rod", 1600);

    public static final RegistryObject<Item> POKEZERG_SAMPLE = register("pokezerg_sample",
            PokezergSampleItem::new);
    public static final RegistryObject<Item> REGENERATIVE_BIOSTEEL = register("regenerative_biosteel",
            RegenerativeBiosteelItem::new);

    public static final RegistryObject<Item> ORANIAN_BERRY = register("oranian_berry",
            OranianBerryItem::new);
    public static final RegistryObject<Item> REVIVE_SEED = register("revive_seed",
            ReviveSeedItem::new);

    public static final RegistryObject<Item> BIOSTEEL_AXE = register("biosteel_axe",
            BiosteelAxeItem::new);
    public static final RegistryObject<Item> BIOSTEEL_HOE = register("biosteel_hoe",
            BiosteelHoeItem::new);
    public static final RegistryObject<Item> BIOSTEEL_PICKAXE = register("biosteel_pickaxe",
            BiosteelPickaxeItem::new);
    public static final RegistryObject<Item> BIOSTEEL_SHOVEL = register("biosteel_shovel",
            BiosteelShovelItem::new);
    public static final RegistryObject<Item> BIOSTEEL_SWORD = register("biosteel_sword",
            BiosteelSwordItem::new);

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
}