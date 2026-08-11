package ru.l0rryq.hud.config.hud.coordinate;

import ru.l0rryq.hud.config.BaseHUDSettings;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class CoordSettings {

    @ConfigEntry.Gui.TransitiveObject
    public BaseHUDSettings base;

    @ConfigEntry.ColorPicker
    public int color;

    public CoordSettings(BaseHUDSettings base, int color) {
        this.base = base;
        this.color = color;
    }
}
