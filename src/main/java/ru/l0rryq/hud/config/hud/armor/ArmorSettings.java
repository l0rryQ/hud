package ru.l0rryq.hud.config.hud.armor;

import ru.l0rryq.hud.config.BaseHUDSettings;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class ArmorSettings {

    @ConfigEntry.Gui.TransitiveObject
    public BaseHUDSettings base;

    @ConfigEntry.ColorPicker
    public int color = 0xD0DAED;

    public ArmorSettings(BaseHUDSettings base) {
        this.base = base;
    }
}
