package dev.heathreuter.sas;

import dev.heathreuter.sas.logic.IrisChargeScheduler;
import dev.heathreuter.sas.logic.IrisDoubleStrikeHandler;
import dev.heathreuter.sas.network.FlipSwingPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.heathreuter.sas.item.ModItems;

public class Sas implements ModInitializer {
    public static final String MOD_ID = "sas";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("SAS onInitialize()");
        ModItems.registerModItems();

        PayloadTypeRegistry.playS2C().register(FlipSwingPayload.ID, FlipSwingPayload.CODEC);

        IrisDoubleStrikeHandler.init();
        IrisChargeScheduler.init();
    }
}