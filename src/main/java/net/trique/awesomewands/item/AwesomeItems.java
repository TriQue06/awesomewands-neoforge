package net.trique.awesomewands.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.trique.awesomewands.AwesomeWands;

public class AwesomeItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AwesomeWands.MODID);

    public static final DeferredItem<Item> ICE_WAND = ITEMS.register("ice_wand",
            () -> new IceWandItem(new Item.Properties().stacksTo(1).durability(64)));

    public static final DeferredItem<Item> THUNDER_WAND = ITEMS.register("thunder_wand",
            () -> new ThunderWandItem(new Item.Properties().stacksTo(1).durability(64)));

    public static final DeferredItem<Item> FIRE_WAND = ITEMS.register("fire_wand",
            () -> new FireWandItem(new Item.Properties().stacksTo(1).durability(64)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}