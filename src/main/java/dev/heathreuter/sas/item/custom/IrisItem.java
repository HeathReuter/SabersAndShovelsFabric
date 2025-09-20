package dev.heathreuter.sas.item.custom;

import dev.heathreuter.sas.component.IrisData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class IrisItem extends Item {
    public IrisItem(Settings settings) {
        super(settings);
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player) {

            long now = player.getWorld().getTime();
            long last = IrisData.getLastHitTime(stack);

            if (now - last > 240) {

                IrisData.setCharges(stack, 0);
            }

            int charges = IrisData.getCharges(stack);

            if (charges < 4) {
                IrisData.setCharges(stack, charges + 1);
                IrisData.setLastHitTime(stack, now);

            } else {
                if (target.getWorld() instanceof ServerWorld sw) {
                    target.damage(sw, player.getDamageSources().playerAttack(player), 7.5f);
                    player.damage(sw, player.getDamageSources().generic(), 2.0f);
                }
                IrisData.setCharges(stack, 0);
            }
        }
    }
}
