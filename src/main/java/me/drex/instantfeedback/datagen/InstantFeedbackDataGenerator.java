package me.drex.instantfeedback.datagen;

import me.drex.instantfeedback.worldgen.ModVegetationFeatures;
import me.drex.instantfeedback.worldgen.ModVegetationPlacements;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class InstantFeedbackDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModWorldgenProvider::new);
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        DataGeneratorEntrypoint.super.buildRegistry(registryBuilder);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ModVegetationFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, ModVegetationPlacements::bootstrap);
    }
}
