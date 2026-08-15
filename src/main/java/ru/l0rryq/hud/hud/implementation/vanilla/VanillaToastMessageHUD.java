package ru.l0rryq.hud.hud.implementation.vanilla;

import ru.l0rryq.hud.config.BaseHUDSettings;
import ru.l0rryq.hud.helper.GrowthDirectionX;
import ru.l0rryq.hud.helper.GrowthDirectionY;
import ru.l0rryq.hud.helper.ScreenAlignmentX;
import ru.l0rryq.hud.helper.ScreenAlignmentY;
import ru.l0rryq.hud.hud.AbstractHUD;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class VanillaToastMessageHUD extends AbstractHUD {

    public VanillaToastMessageHUD() {
        super(new BaseHUDSettings(true, -160, 0, ScreenAlignmentX.RIGHT, ScreenAlignmentY.TOP, GrowthDirectionX.LEFT, GrowthDirectionY.DOWN, false));
    }

    @Override
    public boolean shouldRender() {
        return true;
    }

    @Override
    public boolean collectHUDInformation() {
        setWidthHeight(160, 32);
        return true;
    }

    @Override
    public boolean renderHUD(GuiGraphicsExtractor context, int x, int y, boolean drawBackground, boolean drawTextShadow) {
        return true;
    }

    @Override
    public String getName() {
        return "Toast Message";
    }

    @Override
    public String getId() {
        return HUDId.VANILLA_TOAST_MESSAGE.toString();
    }
}
