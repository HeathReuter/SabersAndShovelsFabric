package dev.heathreuter.sas.logic;

import dev.heathreuter.sas.component.IrisData;
import dev.heathreuter.sas.item.custom.IrisItem;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class IrisChargeTicker {
    public static void init() {
        ServerTickEvents.END_SERVER_TICK.register(IrisChargeTicker::tick);
    }

    private static void tick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            long now = player.getWorld().getTime();

            PlayerInventory inv = player.getInventory();

            for (ItemStack stack : inv.getMainStacks()) {
                check(stack, now);
            }

            ItemStack offhand = inv.getStack(40);
            check(offhand, now);

            for (EquipmentSlot slot : new EquipmentSlot[]{
                    EquipmentSlot.HEAD,
                    EquipmentSlot.CHEST,
                    EquipmentSlot.LEGS,
                    EquipmentSlot.FEET
            }) {
                check(inv.player.getEquippedStack(slot), now);
            }

        }
    }

    private static void check(ItemStack stack, long now) {
        if (!(stack.getItem() instanceof IrisItem)) return;

        int charges = IrisData.getCharges(stack).orElse(0);
        long expire = IrisData.getExpireTime(stack).orElse(0L);

        if (charges > 0 && now >= expire) {
            IrisData.setCharges(stack, 0);
            IrisData.setExpireTime(stack, 0);
        }
    }
}
