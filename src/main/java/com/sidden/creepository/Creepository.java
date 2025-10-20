package com.sidden.creepository;

import com.mojang.logging.LogUtils;
import com.sidden.creepository.registry.CreepositoryBlocks;
import com.sidden.creepository.registry.CreepositoryCreativeTabs;
import com.sidden.creepository.registry.CreepositoryEntities;
import com.sidden.creepository.registry.CreepositoryItems;
import net.minecraft.world.level.biome.Biomes;
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
        CreepositoryEntities.init(modEventBus);
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

        }
    }

}