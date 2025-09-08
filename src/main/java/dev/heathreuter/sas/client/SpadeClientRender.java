package dev.heathreuter.sas.client;

import dev.heathreuter.sas.render.GreenGlintRenderLayer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.PlayerEntityRenderer;

public class SpadeClientRender implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((type, renderer, registrationHelper, ctx) -> {
            if (renderer instanceof PlayerEntityRenderer playerRenderer) {
                registrationHelper.register(new GreenGlintRenderLayer(playerRenderer));
            }
        });
    }
}