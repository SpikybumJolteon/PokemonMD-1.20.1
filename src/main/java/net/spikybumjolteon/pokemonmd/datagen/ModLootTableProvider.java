package net.spikybumjolteon.pokemonmd.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.spikybumjolteon.pokemonmd.common.core.ModBlocks;
import net.spikybumjolteon.pokemonmd.common.core.ModItems;
import net.spikybumjolteon.pokemonmd.common.core.block.OranianBerryCropBlock;

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

    private static class BlockLootTablePokemonMD extends BlockLootSubProvider {
        public BlockLootTablePokemonMD() {
            super(Set.of(), FeatureFlags.DEFAULT_FLAGS);
        }

        @Override
        protected void generate() {
            for (RegistryObject<Block> ro : ModBlocks.BLOCKS.getEntries()) {
                Block b = ro.get();
                if (!(b instanceof CropBlock))
                {
                dropSelf(b);
                }
            }

            LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                    .hasBlockStateProperties(ModBlocks.ORANIAN_BERRY_CROP.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OranianBerryCropBlock.AGE, OranianBerryCropBlock.MAX_AGE));

//            this.add(ModBlocks.ORANIAN_BERRY_CROP.get(), createCropDrops(ModBlocks.ORANIAN_BERRY_CROP.get(), ModItems.REVIVE_SEED.get(),
//                            ModItems.ORANIAN_BERRY.get(), lootitemcondition$builder));
            this.add(ModBlocks.ORANIAN_BERRY_CROP.get(), this.applyExplosionDecay(ModBlocks.ORANIAN_BERRY_CROP.get(),
                    LootTable.lootTable().withPool(LootPool.lootPool()
                            .add(LootItem.lootTableItem(ModItems.ORANIAN_BERRY.get())))
                            .withPool(LootPool.lootPool().when(lootitemcondition$builder)
                                    .add(LootItem.lootTableItem(ModItems.ORANIAN_BERRY.get())
                                            .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))
                            .withPool(LootPool.lootPool().when(lootitemcondition$builder).add(LootItem.lootTableItem(ModItems.REVIVE_SEED.get())
                                    .when(LootItemRandomChanceCondition.randomChance(0.9F))))));

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