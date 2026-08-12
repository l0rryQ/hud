package ru.l0rryq.hud.config.hud.other;

import ru.l0rryq.hud.config.BaseHUDSettings;
import ru.l0rryq.hud.helper.GrowthDirectionX;
import ru.l0rryq.hud.helper.GrowthDirectionY;
import ru.l0rryq.hud.helper.ScreenAlignmentX;
import ru.l0rryq.hud.helper.ScreenAlignmentY;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class TPSSettings {
    @ConfigEntry.Gui.TransitiveObject
    public BaseHUDSettings base = new BaseHUDSettings(false, -263, -24, ScreenAlignmentX.RIGHT, ScreenAlignmentY.BOTTOM, GrowthDirectionX.LEFT, GrowthDirectionY.UP);

    @ConfigEntry.ColorPicker
    public int color = 0xb5ead7;

    public boolean useDynamicColor = true;

    public String additionalString = " TPS";
}
