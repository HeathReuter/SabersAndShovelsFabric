package dev.heathreuter.sas.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record FlipSwingPayload() implements CustomPayload {
    public static final CustomPayload.Id<FlipSwingPayload> ID =
            new CustomPayload.Id<>(Identifier.of("sas", "flip_swing"));

    public static final PacketCodec<RegistryByteBuf, FlipSwingPayload> CODEC =
            PacketCodec.unit(new FlipSwingPayload());

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}