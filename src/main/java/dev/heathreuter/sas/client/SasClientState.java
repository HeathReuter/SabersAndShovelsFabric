package dev.heathreuter.sas.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class SasClientState {
    public static volatile boolean flipNextSwing = false;
    public static boolean greenGlowActive = false;
    private static int glowTicks = 0;
    public static int greenGlowTicks = 0;


    public static void triggerGlow(int durationTicks) {
        greenGlowActive = true;
        glowTicks = durationTicks;
    }

    public static void clientTick() {
        if (greenGlowTicks > 0) {
            greenGlowTicks--;
        }
    }

}