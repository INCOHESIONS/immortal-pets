package io.github.incohesions.immortal_pets;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImmortalPets implements ModInitializer {
    public static final String MOD_ID = "immortal_pets";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final TagKey<Item> PET_POISON = TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "pet_poison"));

    @Override
    public void onInitialize() {
        LOGGER.info("Mod initialized successfully!");
    }
}
