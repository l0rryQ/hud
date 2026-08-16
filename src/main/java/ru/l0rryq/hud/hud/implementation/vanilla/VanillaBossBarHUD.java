package ru.l0rryq.hud.hud.implementation.vanilla;

import ru.l0rryq.hud.config.BaseHUDSettings;
import ru.l0rryq.hud.helper.GrowthDirectionX;
import ru.l0rryq.hud.helper.GrowthDirectionY;
import ru.l0rryq.hud.helper.ScreenAlignmentX;
import ru.l0rryq.hud.helper.ScreenAlignmentY;
import ru.l0rryq.hud.hud.AbstractHUD;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class VanillaBossBarHUD extends AbstractHUD {

    public VanillaBossBarHUD() {
        super(ru.l0rryq.hud.Main.settings.vanillaBossBar);
    }

    @Override
    public boolean shouldRender() {
        return true;
    }

    @Override
    public boolean collectHUDInformation() {
        setWidthHeight(182, 20);
        return true;
    }

    @Override
    public boolean renderHUD(GuiGraphicsExtractor context, int x, int y, boolean drawBackground, boolean drawTextShadow) {
        return true;
    }

    @Override
    public String getName() {
        return "Boss Bar";
    }

    @Override
    public String getId() {
        return HUDId.VANILLA_BOSS_BAR.toString();
    }
}
