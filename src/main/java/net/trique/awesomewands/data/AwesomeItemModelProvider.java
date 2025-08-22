package net.trique.awesomewands.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.trique.awesomewands.AwesomeWands;
import net.trique.awesomewands.item.AwesomeItems;

import java.util.LinkedHashMap;

public class AwesomeItemModelProvider extends ItemModelProvider {

    public AwesomeItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AwesomeWands.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(AwesomeItems.ICE_WAND);
        handheldItem(AwesomeItems.THUNDER_WAND);
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }
}