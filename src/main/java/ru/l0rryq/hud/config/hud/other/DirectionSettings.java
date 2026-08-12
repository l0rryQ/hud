package ru.l0rryq.hud.config.hud.other;

import ru.l0rryq.hud.config.BaseHUDSettings;
import ru.l0rryq.hud.helper.GrowthDirectionX;
import ru.l0rryq.hud.helper.GrowthDirectionY;
import ru.l0rryq.hud.helper.ScreenAlignmentX;
import ru.l0rryq.hud.helper.ScreenAlignmentY;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class DirectionSettings {

    @ConfigEntry.Gui.TransitiveObject
    public BaseHUDSettings base = new BaseHUDSettings(false, 24, 108, ScreenAlignmentX.LEFT, ScreenAlignmentY.TOP, GrowthDirectionX.RIGHT, GrowthDirectionY.DOWN);

    public boolean includeOrdinal = false;

    @ConfigEntry.Gui.CollapsibleObject
    public DirectionColorSetting directionColor = new DirectionColorSetting();

    public static class DirectionColorSetting {
        @ConfigEntry.ColorPicker
        public int s = 0xffb5b5;
        @ConfigEntry.ColorPicker
        public int sw = 0xffcbb3;
        @ConfigEntry.ColorPicker
        public int w = 0xffd1b7;
        @ConfigEntry.ColorPicker
        public int nw = 0xd8cae8;
        @ConfigEntry.ColorPicker
        public int n = 0xb7c9e9;
        @ConfigEntry.ColorPicker
        public int ne = 0xd4dbf0;
        @ConfigEntry.ColorPicker
        public int e = 0xffe5b4;
        @ConfigEntry.ColorPicker
        public int se = 0xffd0c4;
    }
}
