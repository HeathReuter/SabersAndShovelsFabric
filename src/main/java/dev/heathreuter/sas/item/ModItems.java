package dev.heathreuter.sas.item;

import dev.heathreuter.sas.Sas;
import dev.heathreuter.sas.item.custom.IrisItem;
import dev.heathreuter.sas.item.custom.SpadeItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModItems {

    public static Item register(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(Sas.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM,
                        Identifier.of(Sas.MOD_ID,name)))));
    }

    public static final Item IRIS = register(
            "iris",
            settings -> new IrisItem(settings.sword(ModToolMaterials.SAS, 0, -2.4f)) {

                @Override
                public void appendTooltip(ItemStack stack, Item.TooltipContext context,
                                          TooltipDisplayComponent displayComponent,
                                          Consumer<Text> textConsumer, TooltipType type) {
                    if (Screen.hasShiftDown()) {
                        textConsumer.accept(Text.translatable("tooltip.sas.iris.shift_down_1"));
                        textConsumer.accept(Text.translatable("tooltip.sas.iris.shift_down_2"));
                        textConsumer.accept(Text.translatable("tooltip.sas.iris.shift_down_3"));
                        textConsumer.accept(Text.translatable("tooltip.sas.iris.shift_down_4"));
                    } else {
                        textConsumer.accept(Text.translatable("tooltip.sas.iris_1"));
                        textConsumer.accept(Text.translatable("tooltip.sas.iris_2"));
                    }
                    super.appendTooltip(stack, context, displayComponent, textConsumer, type);
                }
            }
    );

    public static final Item SPADE = register(
            "spade",
            settings -> new SpadeItem(settings.shovel(ModToolMaterials.SAS, 5, -3.0f)){
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context,
                                          TooltipDisplayComponent displayComponent,
                                          Consumer<Text> textConsumer, TooltipType type) {
                    textConsumer.accept(Text.translatable("tooltip.sas.spade"));
                }
            }
    );

    public static void registerModItems() {
        Sas.LOGGER.info("Registering Mod Items for " + Sas.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(IRIS);
            entries.add(SPADE);
        });
    }
}