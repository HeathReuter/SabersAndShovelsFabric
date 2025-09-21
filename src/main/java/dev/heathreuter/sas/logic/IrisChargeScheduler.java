package dev.heathreuter.sas.logic;

import dev.heathreuter.sas.component.IrisData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public class IrisChargeScheduler {
    public static final WeakHashMap<ItemStack, Long> scheduled = new WeakHashMap<>();

    public static void init() {
        ServerTickEvents.END_SERVER_TICK.register(IrisChargeScheduler::tick);
    }

    public static void scheduleReset(ItemStack stack, long worldTime) {
        scheduled.put(stack, worldTime + 240);
    }

    private static void tick(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            tickWorld(world);
        }
    }

    public static void tickWorld(ServerWorld world) {
        long now = world.getTime();
        Iterator<Map.Entry<ItemStack, Long>> iterator = scheduled.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<ItemStack, Long> entry = iterator.next();
            ItemStack stack = entry.getKey();
            Long expire = entry.getValue();

            if (stack.isEmpty() || expire <= now) {
                IrisData.setCharges(stack, 0);
                iterator.remove();
            }
        }
    }
}

