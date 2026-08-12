package ru.l0rryq.hud.config.hud.other;

import ru.l0rryq.hud.config.BaseHUDSettings;
import ru.l0rryq.hud.helper.GrowthDirectionX;
import ru.l0rryq.hud.helper.GrowthDirectionY;
import ru.l0rryq.hud.helper.ScreenAlignmentX;
import ru.l0rryq.hud.helper.ScreenAlignmentY;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class FPSSettings {

    @ConfigEntry.Gui.TransitiveObject
    public BaseHUDSettings base = new BaseHUDSettings(true, 24, -24, ScreenAlignmentX.LEFT, ScreenAlignmentY.BOTTOM, GrowthDirectionX.RIGHT, GrowthDirectionY.UP);

    @ConfigEntry.ColorPicker
    public int color = 0xb7dff5;

    public String additionalString = " FPS";
}
