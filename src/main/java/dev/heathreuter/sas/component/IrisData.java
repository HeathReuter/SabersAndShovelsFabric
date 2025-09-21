package dev.heathreuter.sas.component;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;

import java.util.List;
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
        saveTag(stack, tag);

        CustomModelDataComponent cmd = new CustomModelDataComponent(
                List.of(),
                List.of(),
                List.of(String.valueOf(value)),
                List.of()
        );

        stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, cmd);
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

    public static Optional<Long> getExpireTime(ItemStack stack) {
        NbtCompound tag = getTag(stack);
        return tag.getLong("iris_expire");
    }

    public static void setExpireTime(ItemStack stack, long time) {
        NbtCompound tag = getTag(stack);
        tag.putLong("iris_expire", time);
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