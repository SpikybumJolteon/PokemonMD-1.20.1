package net.spikybumjolteon.pokemonmd.common.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.spikybumjolteon.pokemonmd.common.core.item.CreativeTabStackProvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModRegistry.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DEFAULT = TABS.register("default", ModCreativeModeTab::buildDefaultTab);

    private static CreativeModeTab buildDefaultTab() {
        List<ItemStack> items = ModItems.ITEMS.getEntries().stream()
                .flatMap(ro -> stacksForItem(ro.get()))
                .sorted(new ItemSorter())
                .collect(Collectors.toCollection(ArrayList::new));

        return CreativeModeTab.builder()
                .title(Component.translatable("creativetab.pokemonmd_tab"))
                .icon(() -> new ItemStack(ModItems.AURUM_DUST.get()))
                .displayItems((params, output) -> output.acceptAll(items))
                .build();
    }

    private static Stream<ItemStack> stacksForItem(Item item) {
        ItemStack stack = new ItemStack(item);
        if (item instanceof CreativeTabStackProvider provider) {
            return provider.getStacksForItem();
        } else if (item instanceof BlockItem bi && bi.getBlock() instanceof CreativeTabStackProvider provider) {
            return provider.getStacksForItem();
        } else {
            return Stream.of(stack);
        }
    }

    private static class ItemSorter implements Comparator<ItemStack> {
        @Override
        public int compare(ItemStack s1, ItemStack s2) {
            for (Class<?> cls : List.of(BlockItem.class)) {//{PressurizableItem.class, CompressedIronArmorItem.class, PneumaticArmorItem.class, SemiblockItem.class, AbstractGunAmmoItem.class, UpgradeItem.class, TubeModuleItem.class, PneumaticCraftBucketItem.class)) {
                if (cls.isAssignableFrom(s1.getItem().getClass()) && !cls.isAssignableFrom(s2.getItem().getClass())) {
                    return -1;
                } else if (cls.isAssignableFrom(s2.getItem().getClass()) && !cls.isAssignableFrom(s1.getItem().getClass())) {
                    return 1;
                }
            }
            return s1.getDisplayName().getString().compareTo(s2.getDisplayName().getString());
        }
    }

    /*

        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PokemonMD.MOD_ID);


    public static final RegistryObject<CreativeModeTab> POKEMONMD_TAB = CREATIVE_MODE_TABS.register("pokemonmd_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.AURUM_DUST.get()))
                    .title(Component.translatable("creativetab.pokemonmd_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.AURUM_DUST.get());
                        pOutput.accept(ModItems.AURUM_ROD.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
    */

}
