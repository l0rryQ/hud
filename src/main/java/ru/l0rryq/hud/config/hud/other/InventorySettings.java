package ru.l0rryq.hud.config.hud.other;

import ru.l0rryq.hud.config.BaseHUDSettings;
import ru.l0rryq.hud.helper.GrowthDirectionX;
import ru.l0rryq.hud.helper.GrowthDirectionY;
import ru.l0rryq.hud.helper.ScreenAlignmentX;
import ru.l0rryq.hud.helper.ScreenAlignmentY;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class InventorySettings {

    @ConfigEntry.Gui.TransitiveObject
    public BaseHUDSettings base = new BaseHUDSettings(false, -24, 0, ScreenAlignmentX.RIGHT, ScreenAlignmentY.MIDDLE, GrowthDirectionX.LEFT, GrowthDirectionY.MIDDLE);

    public boolean drawVertical = true;

}
