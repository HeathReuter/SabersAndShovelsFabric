package dev.heathreuter.sas.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpadeItem extends Item {
    public SpadeItem(Settings settings) {
        super(settings);
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player) {
            if (isCriticalHit(player)) {
                player.heal(2.0F);
            }
        }

        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    private boolean isCriticalHit(PlayerEntity player) {
        World world = player.getWorld();
        return player.fallDistance > 0.0F
                && !player.isOnGround()
                && !player.isClimbing()
                && !player.isTouchingWater()
                && !player.hasStatusEffect(net.minecraft.entity.effect.StatusEffects.BLINDNESS)
                && !player.hasVehicle()
                && !player.getAbilities().flying
                && !world.isClient;
    }
}