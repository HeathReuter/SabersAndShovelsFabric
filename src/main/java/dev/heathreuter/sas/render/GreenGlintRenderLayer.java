package dev.heathreuter.sas.render;

import dev.heathreuter.sas.client.SasClientState;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;

public class GreenGlintRenderLayer
        extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {

    public GreenGlintRenderLayer(
            FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers,
                       int light,
                       PlayerEntityRenderState state,
                       float limbAngle,
                       float limbDistance) {

        if (SasClientState.greenGlowTicks > 0) {
            VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntityGlint());

            this.getContextModel().render(
                matrices,
                consumer,
                light,
                OverlayTexture.DEFAULT_UV,
                0xFF00FF00
            );
        }
    }
}