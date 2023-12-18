package net.spikybumjolteon.pokemonmd.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.spikybumjolteon.pokemonmd.common.core.ModBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class ModLootTableProvider extends LootTableProvider {

    public ModLootTableProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn.getPackOutput(), Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(BlockLootTablePokemonMD::new, LootContextParamSets.BLOCK)
        ));
    }

//    @Override
//    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationresults) {
//        // ...
//    }

    private static class BlockLootTablePokemonMD extends BlockLootSubProvider {
        public BlockLootTablePokemonMD() {
            super(Set.of(), FeatureFlags.DEFAULT_FLAGS);
        }

        @Override
        protected void generate() {
            for (RegistryObject<Block> ro : ModBlocks.BLOCKS.getEntries()) {
                Block b = ro.get();
//                if (b instanceof PneumaticCraftEntityBlock && ForgeRegistries.ITEMS.containsKey(ro.getId())) {
//                    addStandardSerializedDrop(b, ro.getId());
//                } else if (b instanceof SlabBlock) {
//                    add(b, this::createSlabItemTable);
//                } else if (b.asItem() != Items.AIR) {
                dropSelf(b);
//                }
            }
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            List<Block> l = new ArrayList<>();
            for (RegistryObject<Block> ro : ModBlocks.BLOCKS.getEntries()) {
                if (ForgeRegistries.ITEMS.containsKey(ro.getId())) {
                    l.add(ro.get());
                }
            }
            return l;
        }

//        private void addStandardSerializedDrop(Block block, ResourceLocation blockId) {
//            LootPool.Builder builder = LootPool.lootPool()
//                    .name(blockId.getPath())
//                    .when(ExplosionCondition.survivesExplosion())
//                    .setRolls(ConstantValue.exactly(1))
//                    .add(LootItem.lootTableItem(block)
//                            .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
//                            .apply(LootFunc.BlockEntitySerializerFunction.builder()));
//            add(block, LootTable.lootTable().withPool(builder));
//        }


//    public static class MechanicVillagerChestLootProvider implements LootTableSubProvider {
//        @Override
//        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
//            LootPool.Builder lootPool = LootPool.lootPool();
//            lootPool.setRolls(ConstantValue.exactly(4))
//                    .add(createEntry(ModItems.COMPRESSED_IRON_INGOT.get(), 10, 4, 12))
//                    .add(createEntry(ModItems.AMADRON_TABLET.get(), 2, 1, 1))
//                    .add(createEntry(ModItems.AIR_CANISTER.get(), 10, 1, 5))
//                    .add(createEntry(ModItems.PNEUMATIC_CYLINDER.get(), 5, 2, 4))
//                    .add(createEntry(ModItems.LOGISTICS_CORE.get(), 8, 4, 8))
//                    .add(createEntry(ModItems.CAPACITOR.get(), 4, 4, 8))
//                    .add(createEntry(ModItems.TRANSISTOR.get(), 4, 4, 8))
//                    .add(createEntry(ModItems.TURBINE_ROTOR.get(), 5, 2, 4))
//                    .add(createEntry(ModBlocks.COMPRESSED_IRON_BLOCK.get(), 2, 1, 2))
//                    .add(createEntry(ModBlocks.VORTEX_TUBE.get(), 5, 1, 1))
//                    .add(createEntry(ModBlocks.PRESSURE_TUBE.get(), 10, 3, 8))
//                    .add(createEntry(ModBlocks.ADVANCED_PRESSURE_TUBE.get(), 4, 3, 8))
//                    .add(createEntry(ModBlocks.HEAT_PIPE.get(), 8, 3, 8))
//                    .add(createEntry(ModBlocks.APHORISM_TILE.get(), 5, 2, 3));
//
//            LootTable.Builder lootTable = LootTable.lootTable();
//            lootTable.withPool(lootPool);
//            consumer.accept(RL("chests/mechanic_house"), lootTable);
//        }

        private LootPoolEntryContainer.Builder<?> createEntry(ItemLike item, int weight, int min, int max) {
            return createEntry(new ItemStack(item), weight)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
        }

        private LootPoolSingletonContainer.Builder<?> createEntry(ItemStack item, int weight) {
            LootPoolSingletonContainer.Builder<?> ret = LootItem.lootTableItem(item.getItem()).setWeight(weight);
            if (item.hasTag())
                ret.apply(SetNbtFunction.setTag(item.getOrCreateTag()));
            return ret;
        }
    }

    private LootPoolEntryContainer.Builder<?> ammo(ItemLike item) {
        return createEntry(new ItemStack(item), 1)
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                .apply(EnchantRandomlyFunction.randomApplicableEnchantment());
    }

    private LootPoolEntryContainer.Builder<?> createEntry(ItemLike item, int weight, int min, int max) {
        return createEntry(new ItemStack(item), weight)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
    }

    private LootPoolSingletonContainer.Builder<?> createEntry(ItemStack item, int weight) {
        LootPoolSingletonContainer.Builder<?> ret = LootItem.lootTableItem(item.getItem()).setWeight(weight);
        if (item.hasTag())
            ret.apply(SetNbtFunction.setTag(item.getOrCreateTag()));
        return ret;
    }
}