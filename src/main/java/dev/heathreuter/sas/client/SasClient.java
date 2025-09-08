package dev.heathreuter.sas.client;

import dev.heathreuter.sas.render.GreenGlintRenderLayer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.PlayerEntityRenderer;

@Environment(EnvType.CLIENT)
public class SasClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GreenGlowClientState.init();
        ClientTickEvents.END_CLIENT_TICK.register(client -> SasClientState.clientTick());

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((type, renderer, helper, ctx) -> {
            if (type == EntityType.PLAYER) {
                helper.register(new GreenGlintFeatureRenderer<>(renderer));
            }
        });
    }
}
