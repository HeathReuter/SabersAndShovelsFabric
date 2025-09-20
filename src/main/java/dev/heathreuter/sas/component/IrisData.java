package dev.heathreuter.sas.component;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class IrisData {
    public static int getCharges(ItemStack stack) {
        NbtCompound tag = getTag(stack);
        return tag.getInt("charges").get();
    }

    public static void setCharges(ItemStack stack, int value) {
        NbtCompound tag = getTag(stack);
        tag.putInt("charges", value);
        saveTag(stack, tag);
    }

    public static long getLastHitTime(ItemStack stack) {
        NbtCompound tag = getTag(stack);
        return tag.getLong("last_hit_time").get();
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