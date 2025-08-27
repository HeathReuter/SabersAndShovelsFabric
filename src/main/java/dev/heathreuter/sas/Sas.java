package dev.heathreuter.sas;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.heathreuter.sas.item.ModItems;

public class Sas implements ModInitializer {
    public static final String MOD_ID = "sas";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
    }
}
