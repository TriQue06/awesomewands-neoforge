package net.trique.awesomewands.data;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.trique.awesomewands.AwesomeWands;
import net.trique.awesomewands.block.AwesomeBlocks;

public class AwesomeBlockStateProvider extends BlockStateProvider {

    public AwesomeBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AwesomeWands.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(AwesomeBlocks.MAGICAL_CUBE);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}