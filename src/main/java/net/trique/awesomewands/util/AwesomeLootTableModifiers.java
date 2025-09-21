package net.trique.awesomewands.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.trique.awesomewands.item.AwesomeItems;

public final class AwesomeLootTableModifiers {

    private AwesomeLootTableModifiers() {}

    private static final ResourceLocation IGLOO_CHEST       = ResourceLocation.fromNamespaceAndPath("minecraft", "chests/igloo_chest");
    private static final ResourceLocation DESERT_PYRAMID    = ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid");
    private static final ResourceLocation MINESHAFT         = ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft");
    private static final ResourceLocation NETHER_BRIDGE     = ResourceLocation.fromNamespaceAndPath("minecraft", "chests/nether_bridge");
    private static final ResourceLocation JUNGLE_TEMPLE     = ResourceLocation.fromNamespaceAndPath("minecraft", "chests/jungle_temple");
    private static final ResourceLocation END_CITY_TREASURE = ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure");

    public static void register() {
        NeoForge.EVENT_BUS.addListener(AwesomeLootTableModifiers::onLootTableLoad);
    }

    private static LootPool makePoolWithChance(LootPoolEntryContainer.Builder<?> entryBuilder, float chance) {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(entryBuilder.when(LootItemRandomChanceCondition.randomChance(chance)))
                .build();
    }

    private static void onLootTableLoad(final LootTableLoadEvent event) {
        final ResourceLocation id = event.getName();
        if (id == null) return;

        final float CHANCE = 0.10f;

        if (id.equals(IGLOO_CHEST)) {
            event.getTable().addPool(
                    makePoolWithChance(LootItem.lootTableItem(AwesomeItems.ICE_WAND.get()), CHANCE)
            );
        } else if (id.equals(DESERT_PYRAMID)) {
            event.getTable().addPool(
                    makePoolWithChance(LootItem.lootTableItem(AwesomeItems.THUNDER_WAND.get()), CHANCE)
            );
        } else if (id.equals(MINESHAFT)) {
            event.getTable().addPool(
                    makePoolWithChance(LootItem.lootTableItem(AwesomeItems.FIRE_WAND.get()), CHANCE)
            );
        } else if (id.equals(NETHER_BRIDGE)) {
            event.getTable().addPool(
                    makePoolWithChance(LootItem.lootTableItem(AwesomeItems.VAMPIRIC_WAND.get()), CHANCE)
            );
        } else if (id.equals(JUNGLE_TEMPLE)) {
            event.getTable().addPool(
                    makePoolWithChance(LootItem.lootTableItem(AwesomeItems.HEAL_WAND.get()), CHANCE)
            );
        } else if (id.equals(END_CITY_TREASURE)) {
            event.getTable().addPool(
                    makePoolWithChance(LootItem.lootTableItem(AwesomeItems.PSYCHIC_WAND.get()), CHANCE)
            );
        }
    }
}