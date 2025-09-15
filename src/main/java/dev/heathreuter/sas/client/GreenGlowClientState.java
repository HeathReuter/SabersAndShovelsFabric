package dev.heathreuter.sas.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class GreenGlowClientState {
    private static final Map<UUID, Integer> GLOW_TTL = new ConcurrentHashMap<>();
    private static final int DURATION_TICKS = 60;

    private GreenGlowClientState() {}

    public static void enableGlow(UUID playerUuid) {
        GLOW_TTL.put(playerUuid, DURATION_TICKS);
    }

    public static boolean isGlowing(UUID playerUuid) {
        Integer ttl = GLOW_TTL.get(playerUuid);
        return ttl != null && ttl > 0;
    }

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.isPaused()) return;
            GLOW_TTL.replaceAll((uuid, ttl) -> Math.max(0, ttl - 1));
            GLOW_TTL.entrySet().removeIf(e -> e.getValue() <= 0);
        });
    }

    public static UUID getSelfUuid() {
        var mc = MinecraftClient.getInstance();
        return (mc.player != null) ? mc.player.getUuid() : null;
    }
}
