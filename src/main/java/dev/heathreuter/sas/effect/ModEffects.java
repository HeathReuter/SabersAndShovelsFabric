package dev.heathreuter.sas.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static StatusEffect SPADE_GLOW;

    public static void registerEffects() {
        SPADE_GLOW = Registry.register(
                Registries.STATUS_EFFECT,
                Identifier.of("sas", "spade_glow"),
                new SpadeGlowEffect()
        );
    }
}