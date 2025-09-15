package dev.heathreuter.sas.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record GreenGlowPayload() implements CustomPayload {
    public static final Id<GreenGlowPayload> ID =
            new Id<>(Identifier.of("sas", "textures/misc/green_glint.png"));

    public static final PacketCodec<RegistryByteBuf, GreenGlowPayload> CODEC =
            PacketCodec.unit(new GreenGlowPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}