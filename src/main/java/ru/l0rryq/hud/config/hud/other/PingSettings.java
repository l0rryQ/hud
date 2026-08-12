package ru.l0rryq.hud.config.hud.other;

import ru.l0rryq.hud.config.BaseHUDSettings;
import ru.l0rryq.hud.helper.GrowthDirectionX;
import ru.l0rryq.hud.helper.GrowthDirectionY;
import ru.l0rryq.hud.helper.ScreenAlignmentX;
import ru.l0rryq.hud.helper.ScreenAlignmentY;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

public class PingSettings {

    @ConfigEntry.Gui.TransitiveObject
    public BaseHUDSettings base = new BaseHUDSettings(false, -128, -24, ScreenAlignmentX.RIGHT, ScreenAlignmentY.BOTTOM, GrowthDirectionX.LEFT, GrowthDirectionY.UP);

    @ConfigEntry.ColorPicker
    public int color = 0xa4d8e1;

    public boolean useDynamicColor = true;

    @Comment("Ping update interval, in seconds.")
    public double updateInterval = 5.0;

    public String additionalString = " ms";
}
