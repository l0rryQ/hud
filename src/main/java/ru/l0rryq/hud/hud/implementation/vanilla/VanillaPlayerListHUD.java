package ru.l0rryq.hud.hud.implementation.vanilla;

import ru.l0rryq.hud.hud.AbstractHUD;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.client.Minecraft;
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
    public float getScale() {
        float s = getSettings().scale;
        return s <= 0 ? 1.0f : s;
    }

    @Override
    public boolean collectHUDInformation() {
        setWidthHeight(250, 150);
        return true;
    }

    @Override
    public boolean renderHUD(GuiGraphicsExtractor context, int x, int y, boolean drawBackground, boolean drawTextShadow) {
        if (ru.l0rryq.hud.screen.EditHUDScreen.showVanillaHUDsInEditScreen && Minecraft.getInstance().gui.screen() instanceof ru.l0rryq.hud.screen.EditHUDScreen) {
            int w = getWidth();
            int h = getHeight();
            context.fill(x, y, x + w, y + h, 0x60000000);
            ru.l0rryq.hud.helper.RenderUtils.drawBorder(context, x, y, w, h, 0x80FFFFFF);
            String name = getName();
            int textW = Minecraft.getInstance().font.width(name);
            context.text(Minecraft.getInstance().font, name, x + (w - textW) / 2, y + (h - 8) / 2, 0xFFFFFFFF, true);
        }
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
