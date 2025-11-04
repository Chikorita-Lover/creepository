package com.sidden.creepository.registry;

import com.sidden.creepository.Creepository;
import com.sidden.creepository.entity.Chocken;
import com.sidden.creepository.entity.client.model.ChockenModel;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = Creepository.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CreepositoryEventBusEvents {

    @SubscribeEvent
    public  static  void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ChockenModel.LAYER_LOCATION, ChockenModel::createBodyLayer);

    }


    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CreepositoryEntities.CHOCOLATE_EGG.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    public  static  void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(CreepositoryEntities.CHOCKEN.get(), Chocken.createAttributes().build());


    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerGuiLayers(RegisterGuiLayersEvent event) {
        HUDOverlays.register(event);
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
    }

}

