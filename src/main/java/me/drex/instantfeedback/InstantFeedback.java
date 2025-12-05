package me.drex.instantfeedback;

import me.drex.instantfeedback.block.ModBlocks;
import me.drex.instantfeedback.entity.ModFrogVariants;
import me.drex.instantfeedback.item.ModCauldronInteraction;
import me.drex.instantfeedback.item.ModItems;
import me.drex.instantfeedback.worldgen.FallenDarkOakTrunkPlacer;
import me.drex.instantfeedback.worldgen.ModVegetationPlacements;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.advancements.criterion.DamageSourcePredicate;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.attribute.modifier.FloatModifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.frog.FrogVariant;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InstantFeedback implements ModInitializer {

    public static final String MOD_ID = "instantfeedback";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final SimpleParticleType CREAKING_EYES = FabricParticleTypes.simple();
    public static final ParticleType<ColorParticleOption> TINTED_NEEDLES = FabricParticleTypes.complex(ColorParticleOption::codec, ColorParticleOption::streamCodec);
    public static final TrunkPlacerType<FallenDarkOakTrunkPlacer> FALLEN_DARK_OAK_TRUNK_PLACER = Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "fallen_dark_oak_trunk_placer"), new TrunkPlacerType<>(FallenDarkOakTrunkPlacer.CODEC));


    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "creaking_eyes"), CREAKING_EYES);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "tinted_needles"), TINTED_NEEDLES);
        ModBlocks.initialize();
        ModItems.initialize();
        BiomeModifications.create(Identifier.fromNamespaceAndPath(MOD_ID, "pale_garden_remove_spawn"))
            .add(ModificationPhase.REMOVALS, context -> context.getBiomeKey() == Biomes.PALE_GARDEN, context -> {
                context.getSpawnSettings().clearSpawns();
            });

        BiomeModifications.create(Identifier.fromNamespaceAndPath(MOD_ID, "pale_garden_replace_vegetation"))
            .add(ModificationPhase.REPLACEMENTS, context -> context.getBiomeKey() == Biomes.PALE_GARDEN, context -> {
                context.getGenerationSettings().removeFeature(VegetationPlacements.PALE_GARDEN_VEGETATION);
                context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModVegetationPlacements.PALE_GARDEN_VEGETATION);
            });

        BiomeModifications.create(Identifier.fromNamespaceAndPath(MOD_ID, "pale_garden_environment_fog"))
            .add(ModificationPhase.ADDITIONS, context -> context.getBiomeKey() == Biomes.PALE_GARDEN, context -> {
                context.getAttributes().setModifier(EnvironmentAttributes.FOG_END_DISTANCE, FloatModifier.MULTIPLY, 1 / 16f);
            });

        BiomeModifications.addFeature(
            context -> context.getBiomeKey() == Biomes.PALE_GARDEN,
            GenerationStep.Decoration.VEGETAL_DECORATION,
            ModVegetationPlacements.PATCH_PALE_PUMPKIN
        );
        BiomeModifications.addFeature(
            context -> context.getBiomeKey() == Biomes.PALE_GARDEN,
            GenerationStep.Decoration.VEGETAL_DECORATION,
            ModVegetationPlacements.PILE_PALE_LEAVES
        );
        BiomeModifications.addFeature(
            context -> context.getBiomeKey() == Biomes.PALE_GARDEN,
            GenerationStep.Decoration.VEGETAL_DECORATION,
            ModVegetationPlacements.PALE_VEGETATION
        );
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            HolderGetter<EntityType<?>> entityTypes = registries.lookupOrThrow(Registries.ENTITY_TYPE);
            HolderGetter<FrogVariant> frogVariants = registries.lookupOrThrow(Registries.FROG_VARIANT);
            var magmaCube = EntityType.MAGMA_CUBE.getDefaultLootTable();
            if (magmaCube.isPresent() && magmaCube.get() == key && source.isBuiltin()) {
                tableBuilder.modifyPools(builder -> {
                    builder.add(
                        LootItem.lootTableItem(ModItems.CERULEAN_FROGLIGHT)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                            .when(DamageSourceCondition.hasDamageSource(
                                DamageSourcePredicate.Builder.damageType()
                                    .source(
                                        EntityPredicate.Builder.entity()
                                            .of(entityTypes, EntityType.FROG)
                                            .components(
                                                DataComponentMatchers.Builder.components()
                                                    .exact(DataComponentExactPredicate.expect(DataComponents.FROG_VARIANT, frogVariants.getOrThrow(ModFrogVariants.DARK)))
                                                    .build()
                                            )
                                    )
                            ))
                    );
                });
            }
        });

        ModCauldronInteraction.bootstrap();
    }

    public static String id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path).toString();
    }
}