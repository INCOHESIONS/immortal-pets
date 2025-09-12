package io.github.incohesions.immortal_pets;

import net.fabricmc.api.ModInitializer;
import org.slf4j.LoggerFactory;

public class ImmortalPets implements ModInitializer {
    public static final String MOD_ID = "immortal_pets";

    @Override
    public void onInitialize() {
        LoggerFactory.getLogger(MOD_ID).info("Mod initialized successfully!");
    }
}
