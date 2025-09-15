package dev.heathreuter.sas.render;

import dev.heathreuter.sas.client.SasClientState;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;

package dev.heathreuter.sas.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.heathreuter.sas.client.GreenGlowClientState;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.ColorHelper;

import java.util.UUID;

public class GreenGlintFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>>
        extends FeatureRenderer<T, M> {

    public GreenGlintFeatureRenderer(FeatureRendererContext<T, M> ctx) {
        super(ctx);
    }

    @Override
    public void render(MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers,
                       int light, T entity,
                       float limbAngle, float limbDistance, float tickDelta,
                       float animationProgress, float headYaw, float headPitch) {

        UUID id = entity.getUuid();
        if (!GreenGlowClientState.isGlowing(id)) return;
        if (entity.isInvisible()) return;

        M model = getContextModel();
        model.animateModel(entity, limbAngle, limbDistance, tickDelta);
        model.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

        matrices.push();

        RenderLayer layer = RenderLayer.getEntityGlint();
        VertexConsumer vc = vertexConsumers.getBuffer(layer);

        float r = 0x22 / 255f, g = 0xFF / 255f, b = 0x55 / 255f, a = 1f;

        RenderSystem.setShaderGlintColor(r, g, b, a);
        try {
            model.render(matrices, vc, light, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, 1f);
        } finally {
            RenderSystem.setShaderGlintColor(1f, 1f, 1f, 1f);
        }

        matrices.pop();
    }
}
