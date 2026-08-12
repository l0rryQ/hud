package ru.l0rryq.hud.config.hud.hand;

import ru.l0rryq.hud.config.BaseHUDSettings;
import ru.l0rryq.hud.helper.GrowthDirectionX;
import ru.l0rryq.hud.helper.GrowthDirectionY;
import ru.l0rryq.hud.helper.ScreenAlignmentX;
import ru.l0rryq.hud.helper.ScreenAlignmentY;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

public class HandSettings {

    @ConfigEntry.Gui.TransitiveObject
    public BaseHUDSettings base;

    @ConfigEntry.ColorPicker
    public int color;

    public HandSettings(boolean shouldRender, int x, int y, ScreenAlignmentX originX, ScreenAlignmentY originY, GrowthDirectionX growthDirectionX, GrowthDirectionY growthDirectionY, int color) {
        base = new BaseHUDSettings(shouldRender, x, y, originX, originY, growthDirectionX, growthDirectionY);
        this.color = color;
    }
}
