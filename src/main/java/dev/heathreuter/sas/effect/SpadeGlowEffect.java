package dev.heathreuter.sas.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SpadeGlowEffect extends StatusEffect {
    public SpadeGlowEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x00FF00); // зелёный цвет
    }
}