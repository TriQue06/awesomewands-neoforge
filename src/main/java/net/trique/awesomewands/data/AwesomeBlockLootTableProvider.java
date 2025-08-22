package net.trique.awesomewands.data;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.trique.awesomewands.block.AwesomeBlocks;

import java.util.Set;

public class AwesomeBlockLootTableProvider extends BlockLootSubProvider {

    public AwesomeBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(AwesomeBlocks.MAGICAL_CUBE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return AwesomeBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}