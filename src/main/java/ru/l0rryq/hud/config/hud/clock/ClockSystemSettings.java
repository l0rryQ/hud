package ru.l0rryq.hud.config.hud.clock;

import ru.l0rryq.hud.config.BaseHUDSettings;
import ru.l0rryq.hud.helper.GrowthDirectionX;
import ru.l0rryq.hud.helper.GrowthDirectionY;
import ru.l0rryq.hud.helper.ScreenAlignmentX;
import ru.l0rryq.hud.helper.ScreenAlignmentY;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class ClockSystemSettings {

    @ConfigEntry.Gui.TransitiveObject
    public BaseHUDSettings base = new BaseHUDSettings(false, -24, -24, ScreenAlignmentX.RIGHT, ScreenAlignmentY.BOTTOM, GrowthDirectionX.LEFT, GrowthDirectionY.UP);

    public boolean use12Hour = false;

    @ConfigEntry.ColorPicker
    public int color = 0xFFFFFF;
}
