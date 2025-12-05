package me.drex.instantfeedback.datagen;

import me.drex.instantfeedback.InstantFeedback;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.advancements.criterion.PlayerInteractTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    protected ModAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
        HolderGetter<EntityType<?>> entityGetter = registryLookup.lookupOrThrow(Registries.ENTITY_TYPE);

        //noinspection removal
        Advancement.Builder.advancement()
            .parent(Identifier.withDefaultNamespace("end/root"))
            .display(Items.WHITE_HARNESS, Component.translatable("advancement.instantfeedback.end.ride_happy_ghast.title"), Component.translatable("advancement.instantfeedback.end.ride_happy_ghast.description"), null, AdvancementType.CHALLENGE, true, true, false)
            .addCriterion(
                "ride_happy_ghast",
                PlayerInteractTrigger.TriggerInstance.itemUsedOnEntity(
                    ItemPredicate.Builder.item(), Optional.of(EntityPredicate.wrap(
                        EntityPredicate.Builder.entity().of(entityGetter, EntityType.HAPPY_GHAST)
                            .located(LocationPredicate.Builder.inDimension(Level.END))
                    ))
                )
            )
            .save(consumer, InstantFeedback.id("end/ride_happy_ghast"));

    }
}
