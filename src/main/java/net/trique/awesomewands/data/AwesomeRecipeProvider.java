package net.trique.awesomewands.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.trique.awesomewands.item.AwesomeItems;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class AwesomeRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public AwesomeRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // === RUNES ===

        // Ice Rune: Stone + Blue Ice
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AwesomeItems.ICE_RUNE.get())
                .requires(Items.STONE)
                .requires(Items.BLUE_ICE)
                .unlockedBy("has_blue_ice", has(Items.BLUE_ICE))
                .save(recipeOutput);

        // Spark Rune: Stone + Gold Ingot
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AwesomeItems.SPARK_RUNE.get())
                .requires(Items.STONE)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                .save(recipeOutput);

        // Flame Rune: Stone + Blaze Powder
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AwesomeItems.FIRE_RUNE.get())
                .requires(Items.STONE)
                .requires(Items.BLAZE_POWDER)
                .unlockedBy("has_blaze_powder", has(Items.BLAZE_POWDER))
                .save(recipeOutput);

        // Wart Rune: Stone + Nether Wart
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AwesomeItems.WART_RUNE.get())
                .requires(Items.STONE)
                .requires(Items.NETHER_WART)
                .unlockedBy("has_nether_wart", has(Items.NETHER_WART))
                .save(recipeOutput);

        // Heal Rune: Stone + Emerald
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AwesomeItems.HEAL_RUNE.get())
                .requires(Items.STONE)
                .requires(Items.EMERALD)
                .unlockedBy("has_emerald", has(Items.EMERALD))
                .save(recipeOutput);

        // Arcane Rune: Stone + Amethyst Shard
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AwesomeItems.ARCANE_RUNE.get())
                .requires(Items.STONE)
                .requires(Items.AMETHYST_SHARD)
                .unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD))
                .save(recipeOutput);
    }
}
