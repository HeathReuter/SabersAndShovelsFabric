package dev.heathreuter.sas.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SpadeItem extends Item {
    public SpadeItem(Settings settings) {
        super(settings);
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player) {
            float damage = target.getMaxHealth() - target.getHealth();
            if (damage > 0f) {
                float heal = damage * 0.10f;
                player.heal(2);
            }
        }

        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }
}