package dev.heathreuter.sas.component;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.util.Optional;

import static java.util.Optional.of;

public class IrisData {
    public static Optional<Integer> getCharges(ItemStack stack) {
        NbtCompound tag = getTag(stack);
        return tag.getInt("charges");
    }

    public static void setCharges(ItemStack stack, int value) {
        NbtCompound tag = getTag(stack);
        tag.putInt("charges", value);
        tag.putInt("custom_model_data", value);
        saveTag(stack, tag);
    }

    public static Optional<Long> getLastHitTime(ItemStack stack) {
        NbtCompound tag = getTag(stack);
        return tag.getLong("last_hit_time");
    }

    public static void setLastHitTime(ItemStack stack, long time) {
        NbtCompound tag = getTag(stack);
        tag.putLong("last_hit_time", time);
        saveTag(stack, tag);
    }

    private static NbtCompound getTag(ItemStack stack) {
        var data = stack.getOrDefault(DataComponentTypes.CUSTOM_DATA,
                net.minecraft.component.type.NbtComponent.of(new NbtCompound()));
        return data.copyNbt();
    }

    private static void saveTag(ItemStack stack, NbtCompound tag) {
        stack.set(DataComponentTypes.CUSTOM_DATA,
                net.minecraft.component.type.NbtComponent.of(tag));
    }
}