package dev.heathreuter.sas.util;

import dev.heathreuter.sas.Sas;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEED_SAS_TOOL = createTag("need_sas_tool");
        public static final TagKey<Block> INCORRECT_FOR_SAS_TOOL = createTag("incorrect_for_sas_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Sas.MOD_ID, name));
        }
    }
    public static class Items {
            private static TagKey<Item> createTag(String name) {
                return TagKey.of(RegistryKeys.ITEM, Identifier.of(Sas.MOD_ID, name));
            }
    }
}
