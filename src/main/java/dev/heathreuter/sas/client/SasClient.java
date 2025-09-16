package dev.heathreuter.sas.client;

import dev.heathreuter.sas.Sas;
import dev.heathreuter.sas.network.FlipSwingPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

@Environment(EnvType.CLIENT)
public class SasClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(FlipSwingPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                if (context.client().player != null) {
                    try {
                        context.client().player.swingHand(net.minecraft.util.Hand.MAIN_HAND, true);
                    } catch (NoSuchMethodError e) {
                        context.client().player.swingHand(net.minecraft.util.Hand.MAIN_HAND);
                    }
                }
            });
        });
    }
}
