package com.sidden.creepository;

import com.mojang.logging.LogUtils;
import com.sidden.creepository.client.screen.KegScreen;
import com.sidden.creepository.entity.client.renderer.ChockenRenderer;
import com.sidden.creepository.registry.*;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.slf4j.Logger;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(Creepository.MOD_ID)
public class Creepository
{
    public static final String MOD_ID = "creepository";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Creepository(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        CreepositoryItems.init(modEventBus);
        CreepositoryBlocks.init(modEventBus);
        CreepositoryEffects.init(modEventBus);
        CreepositoryEntities.init(modEventBus);
        CreepositoryBlockEntities.init(modEventBus);
        CreepositoryMenus.init(modEventBus);
        CreepositoryRecipes.init(modEventBus);
        CreepositoryCreativeTabs.init(modEventBus);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        registerBiomes();
    }

    public void registerBiomes()
    {
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(CreepositoryEntities.CHOCKEN.get(), ChockenRenderer::new);
        }
        @SubscribeEvent
        public static void registerMenuScreens(RegisterMenuScreensEvent event) {
            event.register(CreepositoryMenus.KEG.get(), KegScreen::new);
        }
    }

}