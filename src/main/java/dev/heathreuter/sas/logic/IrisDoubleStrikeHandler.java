package dev.heathreuter.sas.logic;

import dev.heathreuter.sas.Sas;
import dev.heathreuter.sas.item.ModItems;
import dev.heathreuter.sas.network.FlipSwingPayload;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.*;

public final class IrisDoubleStrikeHandler {
    private static final Map<UUID, Integer> lockout = new HashMap<>();
    private static final List<Scheduled> tasks = new ArrayList<>();

    public static void init() {
        Sas.LOGGER.info("IrisDoubleStrikeHandler.init()");
        ServerTickEvents.END_SERVER_TICK.register(IrisDoubleStrikeHandler::tick);

        ServerLivingEntityEvents.AFTER_DAMAGE.register((target, source, amount, taken, blocked) -> {
            if (blocked || taken <= 0) return;
            if (!(source.getAttacker() instanceof PlayerEntity player)) return;

            ItemStack stack = player.getMainHandStack();
            if (!stack.isOf(ModItems.IRIS)) return;

            MinecraftServer server = player.getServer();
            if (server == null) return;
            int now = server.getTicks();
            Integer last = lockout.get(player.getUuid());
            if (last != null && now - last < 20) return; // защита от частых спамов
            lockout.put(player.getUuid(), now);

            Sas.LOGGER.info("Iris hit by {} on {} — scheduling double strike", player.getName().getString(), target.getType().getName().getString());

            schedule(server, 4, () -> {
                try {
                    if (!player.isAlive() || !target.isAlive()) return;
                    if (player.squaredDistanceTo(target) > 9.0) return;

                    target.hurtTime = 0;
                    target.timeUntilRegen = 0;

                    double damage = player.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);

                    Sas.LOGGER.info("Applying second damage {} to {}", damage, target.getType().getName().getString());

                    target.damage(
                        (ServerWorld) target.getWorld(),
                        player.getDamageSources().playerAttack(player),
                        (float) damage
                    );

                    if (player instanceof ServerPlayerEntity sp) {
                        ServerPlayNetworking.send(sp, new FlipSwingPayload());
                        Sas.LOGGER.info("Sent FlipSwingPayload to {}", sp.getName().getString());
                    }
                } catch (Exception e) {
                    Sas.LOGGER.error("Error during scheduled iris double strike", e);
                }
            });
        });
    }

    private static final class Scheduled { int runAt; Runnable task; }

    private static void schedule(MinecraftServer server, int delayTicks, Runnable task) {
        Scheduled s = new Scheduled();
        s.runAt = server.getTicks() + delayTicks;
        s.task = task;
        tasks.add(s);
    }

    private static void tick(MinecraftServer server) {
        int t = server.getTicks();
        tasks.removeIf(s -> {
            if (s.runAt <= t) {
                try { s.task.run(); } catch (Throwable e) { Sas.LOGGER.error("task error", e); }
                return true;
            }
            return false;
        });
    }
}