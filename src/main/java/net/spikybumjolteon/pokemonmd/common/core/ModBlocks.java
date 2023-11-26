package net.spikybumjolteon.pokemonmd.common.core;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ModRegistry.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = ModItems.ITEMS;

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> sup) {
        return register(name, sup, ModBlocks::itemDefault);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> sup, Function<RegistryObject<T>, Supplier<? extends Item>> itemCreator) {
        RegistryObject<T> ret = registerNoItem(name, sup);
        ITEMS.register(name, itemCreator.apply(ret));
        return ret;
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<? extends T> sup) {
        return BLOCKS.register(name, sup);
    }

    private static Supplier<BlockItem> itemDefault(final RegistryObject<? extends Block> blockSupplier) {
        return item(blockSupplier);
    }

    private static Supplier<BlockItem> item(final RegistryObject<? extends Block> blockSupplier) {
        return () -> new BlockItem(blockSupplier.get(), ModItems.defaultProps());
    }

    public static final RegistryObject<Block> PACKED_AURUM_DUST = register("packed_aurum_dust",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SAND)));

 /*   public static final RegistryObject<AirCannonBlock> AIR_CANNON = register("air_cannon",
            AirCannonBlock::new);*/
/*
    public static Block.Properties defaultProps() {
        return Block.Properties.of()
                .mapColor(MapColor.METAL)
                .strength(3f, 10f)
                .sound(SoundType.METAL);
    }*/

    /*
    public static final RegistryObject<Block> PACKED_AURUM_DUST = registerBlock("packed_aurum_dust",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SAND)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }*/
}
