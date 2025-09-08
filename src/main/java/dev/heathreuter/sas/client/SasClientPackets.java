package dev.heathreuter.sas.client;

import dev.heathreuter.sas.network.GreenGlowPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class SasClientPackets {
    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(GreenGlowPayload.ID, (payload, context) -> {
            context.client().execute(() -> SasClientState.greenGlowTicks = 10);
        });
    }
}