package com.sidden.creepository.registry;

import com.sidden.creepository.Creepository;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = Creepository.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class CreepositoryGameplayEvents {

    @SubscribeEvent
    public static void onItemRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();

        if (player.hasEffect(CreepositoryEffects.SUGAR_CRAVE)) {
            if (!stack.is(CreepositoryItemTags.CHOCOLATY)) {
                event.setCanceled(true);
                player.displayClientMessage(
                        net.minecraft.network.chat.Component.literal("You crave only chocolate right now..."),
                        true
                );
            }
        }
    }
}
