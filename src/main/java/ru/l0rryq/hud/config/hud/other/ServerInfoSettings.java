package ru.l0rryq.hud.config.hud.other;

import ru.l0rryq.hud.config.BaseHUDSettings;
import ru.l0rryq.hud.helper.GrowthDirectionX;
import ru.l0rryq.hud.helper.GrowthDirectionY;
import ru.l0rryq.hud.helper.ScreenAlignmentX;
import ru.l0rryq.hud.helper.ScreenAlignmentY;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class ServerInfoSettings {

    @ConfigEntry.Gui.TransitiveObject
    public BaseHUDSettings base = new BaseHUDSettings(true, 24, 110, ScreenAlignmentX.LEFT, ScreenAlignmentY.TOP, GrowthDirectionX.RIGHT, GrowthDirectionY.DOWN);

    @ConfigEntry.ColorPicker
    public int color = 0x90EE90; // Light green default

    public String additionalString = "";

    public boolean showOnlyOnServers = false;
}
