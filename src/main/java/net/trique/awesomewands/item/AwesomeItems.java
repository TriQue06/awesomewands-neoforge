package net.trique.awesomewands.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.trique.awesomewands.AwesomeWands;
import net.trique.awesomewands.item.wands.*;

public class AwesomeItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AwesomeWands.MODID);

    // === WANDS ===
    public static final DeferredItem<Item> ICE_WAND = ITEMS.register("ice_wand",
            () -> new IceWandItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).durability(64)));

    public static final DeferredItem<Item> THUNDER_WAND = ITEMS.register("thunder_wand",
            () -> new ThunderWandItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).durability(64)));

    public static final DeferredItem<Item> FIRE_WAND = ITEMS.register("fire_wand",
            () -> new FireWandItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).durability(64)));

    public static final DeferredItem<Item> VAMPIRIC_WAND = ITEMS.register("vampiric_wand",
            () -> new VampiricWandItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).durability(64)));

    public static final DeferredItem<Item> HEAL_WAND = ITEMS.register("heal_wand",
            () -> new HealWandItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).durability(64)));

    public static final DeferredItem<Item> PSYCHIC_WAND = ITEMS.register("psychic_wand",
            () -> new PsychicWandItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).durability(64)));

    // === RUNES ===
    public static final DeferredItem<Item> ICE_RUNE = ITEMS.register("ice_rune",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> SPARK_RUNE = ITEMS.register("spark_rune",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> FIRE_RUNE = ITEMS.register("fire_rune",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> WART_RUNE = ITEMS.register("wart_rune",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> HEAL_RUNE = ITEMS.register("heal_rune",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> ARCANE_RUNE = ITEMS.register("arcane_rune",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}