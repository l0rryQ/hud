package ru.l0rryq.hud.config.hud.clock;

import ru.l0rryq.hud.config.BaseHUDSettings;
import ru.l0rryq.hud.helper.GrowthDirectionX;
import ru.l0rryq.hud.helper.GrowthDirectionY;
import ru.l0rryq.hud.helper.ScreenAlignmentX;
import ru.l0rryq.hud.helper.ScreenAlignmentY;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class ClockInGameSettings {

    @ConfigEntry.Gui.TransitiveObject
    public BaseHUDSettings base = new BaseHUDSettings(true, 3, 24, ScreenAlignmentX.CENTER, ScreenAlignmentY.TOP, GrowthDirectionX.RIGHT, GrowthDirectionY.DOWN);

    public boolean use12Hour = false;

    @ConfigEntry.Gui.CollapsibleObject
    public ClockInGameColorSetting color = new ClockInGameColorSetting();

    public static class ClockInGameColorSetting {
        @ConfigEntry.ColorPicker
        public int day = 0xfff9b5;
        @ConfigEntry.ColorPicker
        public int night = 0xd6cbef;
        @ConfigEntry.ColorPicker
        public int rain = 0xb5d0e8;
        @ConfigEntry.ColorPicker
        public int thunder = 0x8faecb;
    }
}
