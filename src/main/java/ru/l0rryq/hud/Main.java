package ru.l0rryq.hud;

import ru.l0rryq.hud.config.Settings;
import ru.l0rryq.hud.hud.HUDComponent;
import ru.l0rryq.hud.init.ConfigInit;
import ru.l0rryq.hud.init.EventInit;
import ru.l0rryq.hud.init.KeybindInit;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.KeyMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("lryq_hud");

    public static Settings settings;

    public static KeyMapping.Category keyCategory;
    public static KeyMapping openEditHUDKey;
    public static KeyMapping toggleHUDKey;

    @Override
    public void onInitializeClient() {

        ConfigInit.init();
        KeybindInit.init();
        EventInit.init();
        HUDComponent.getInstance().init();
    }

}
