package dev.heathreuter.sas.item;

import dev.heathreuter.sas.Sas;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    public static Item register(String name, Function<Item.Settings, Item> function) {

        return Registry.register(Registries.ITEM, Identifier.of(Sas.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM,
                        Identifier.of(Sas.MOD_ID,name)))));
    }

    public static final Item IRIS = register(
            "iris",
            settings -> new Item(settings.sword(ModToolMaterials.SAS, 3, -2.4f))
    );


//    private static Item register(String name, Function<Item.Settings, Item> itemFunction, Item.Settings settings) {
//        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Sas.MOD_ID,name));
//
//        Item item = itemFunction.apply(settings.registryKey(itemKey));
//
//        Registry.register(Registries.ITEM, itemKey, item);
//
//        return item;
//    }
//






    public static void registerModItems() {
        Sas.LOGGER.info("Registering Mod Items for " + Sas.MOD_ID);

        // Add items to creative tabs
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(IRIS);
        });
    }
}