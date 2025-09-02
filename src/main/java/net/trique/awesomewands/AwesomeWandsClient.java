package net.trique.awesomewands;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.trique.awesomewands.block.AwesomeBlocks;
import net.trique.awesomewands.particle.AwesomeParticleTemplate;
import net.trique.awesomewands.particle.AwesomeParticles;

@Mod(value = AwesomeWands.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = AwesomeWands.MODID, value = Dist.CLIENT)

public class AwesomeWandsClient {
    public AwesomeWandsClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(AwesomeBlocks.MAGICAL_CUBE.get(), RenderType.translucent());
          });
    }

    @SubscribeEvent
    static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(AwesomeParticles.ICE_BEAM.get(), AwesomeParticleTemplate.Provider::new);
        event.registerSpriteSet(AwesomeParticles.THUNDER_BEAM.get(), AwesomeParticleTemplate.Provider::new);
        event.registerSpriteSet(AwesomeParticles.FIRE_BEAM.get(), AwesomeParticleTemplate.Provider::new);
        event.registerSpriteSet(AwesomeParticles.VAMPIRIC_BEAM.get(), AwesomeParticleTemplate.Provider::new);
        event.registerSpriteSet(AwesomeParticles.HEAL_BEAM.get(), AwesomeParticleTemplate.Provider::new);
        event.registerSpriteSet(AwesomeParticles.PSYCHIC_BEAM.get(), AwesomeParticleTemplate.Provider::new);
    }
}