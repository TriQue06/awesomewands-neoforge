package net.trique.awesomewands.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.trique.awesomewands.AwesomeWands;
import net.trique.awesomewands.block.AwesomeBlocks;

import java.util.function.Supplier;

public class AwesomeCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AwesomeWands.MODID);

    public static final Supplier<CreativeModeTab> ABYSS_ITEMS_TAB = CREATIVE_MODE_TAB.register("awesomeitemstab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(AwesomeItems.ICE_WAND.get()))
                    .title(Component.translatable("tab1"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(AwesomeItems.ICE_WAND);
                        output.accept(AwesomeItems.THUNDER_WAND);
                        output.accept(AwesomeItems.FIRE_WAND);
                        output.accept(AwesomeItems.VAMPIRIC_WAND);
                        output.accept(AwesomeItems.HEAL_WAND);
                        output.accept(AwesomeItems.PSYCHIC_WAND);
                        output.accept(AwesomeBlocks.MAGICAL_CUBE);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}