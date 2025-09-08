package dev.heathreuter.sas.mixin.client;

import dev.heathreuter.sas.client.SasClientState;
import dev.heathreuter.sas.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {
    @Inject(method = "renderFirstPersonItem(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/util/Hand;FLnet/minecraft/item/ItemStack;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At("HEAD"))
    private void sas$flipIrisSwing(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand,
                                   float swingProgress, ItemStack stack, float equipProgress,
                                   MatrixStack matrices, VertexConsumerProvider vcp, int light,
                                   CallbackInfo ci) {
        if (SasClientState.flipNextSwing && hand == Hand.MAIN_HAND && stack.isOf(ModItems.IRIS)) {
            matrices.translate(-0.4F, 0.0F, 0.0F);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
            SasClientState.flipNextSwing = false;
        }
    }
}