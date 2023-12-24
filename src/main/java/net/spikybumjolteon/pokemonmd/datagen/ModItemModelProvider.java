package net.spikybumjolteon.pokemonmd.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.spikybumjolteon.pokemonmd.common.core.ModItems;
import net.spikybumjolteon.pokemonmd.common.core.ModRegistry;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn.getPackOutput(), ModRegistry.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.AURUM_DUST);
        simpleItem(ModItems.AURUM_ROD);
        simpleItem(ModItems.INFERNO_FUEL_ROD);
        simpleItem(ModItems.POKEZERG_SAMPLE);
        simpleItem(ModItems.REGENERATIVE_BIOSTEEL);

        handheldItem(ModItems.BIOSTEEL_AXE);
        handheldItem(ModItems.BIOSTEEL_HOE);
        handheldItem(ModItems.BIOSTEEL_PICKAXE);
        handheldItem(ModItems.BIOSTEEL_SHOVEL);
        handheldItem(ModItems.BIOSTEEL_SWORD);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ModRegistry.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(ModRegistry.MOD_ID, "item/" + item.getId().getPath()));
    }

}