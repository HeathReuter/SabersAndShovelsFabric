package dev.heathreuter.sas.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class IrisClientState {
    public static volatile boolean flipNextSwing = false;

}