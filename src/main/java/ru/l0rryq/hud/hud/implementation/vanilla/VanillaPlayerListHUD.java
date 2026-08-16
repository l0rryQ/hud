package ru.l0rryq.hud.hud.implementation.vanilla;

import ru.l0rryq.hud.config.BaseHUDSettings;
import ru.l0rryq.hud.helper.GrowthDirectionX;
import ru.l0rryq.hud.helper.GrowthDirectionY;
import ru.l0rryq.hud.helper.ScreenAlignmentX;
import ru.l0rryq.hud.helper.ScreenAlignmentY;
import ru.l0rryq.hud.hud.AbstractHUD;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class VanillaPlayerListHUD extends AbstractHUD {

    public VanillaPlayerListHUD() {
        super(ru.l0rryq.hud.Main.settings.vanillaPlayerList);
    }

    @Override
    public boolean shouldRender() {
        return true;
    }

    @Override
    public boolean collectHUDInformation() {
        setWidthHeight(250, 150);
        return true;
    }

    @Override
    public boolean renderHUD(GuiGraphicsExtractor context, int x, int y, boolean drawBackground, boolean drawTextShadow) {
        return true;
    }

    @Override
    public String getName() {
        return "Player List (tab)";
    }

    @Override
    public String getId() {
        return HUDId.VANILLA_PLAYER_LIST.toString();
    }
}
