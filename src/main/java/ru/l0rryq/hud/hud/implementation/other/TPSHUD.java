package ru.l0rryq.hud.hud.implementation.other;

import ru.l0rryq.hud.Helper;
import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.other.TPSSettings;
import ru.l0rryq.hud.helper.HUDDisplayMode;
import ru.l0rryq.hud.helper.RenderUtils;
import ru.l0rryq.hud.helper.TPSTracker;
import ru.l0rryq.hud.hud.AbstractHUD;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

public class TPSHUD extends AbstractHUD {

    private static final TPSSettings SETTINGS = Main.settings.tpsSettings;
    private static final Minecraft CLIENT = Minecraft.getInstance();

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/tps.png");

    private static final int ICON_HEIGHT = 13;
    private static final int ICON_WIDTH = 13;
    private static final int TEXTURE_WIDTH = 13;
    private static final int TEXTURE_HEIGHT = ICON_HEIGHT * 5;

    public TPSHUD() {
        super(SETTINGS.base);
    }

    private String str;
    private int color;
    private int step;
    private HUDDisplayMode displayMode;

    @Override
    public boolean collectHUDInformation() {

        float tps = (float) Math.round(TPSTracker.getTPS() * 10) / 10;

        step = getStep(tps);
        str = getFormattedText(tps + SETTINGS.additionalString);
        int strWidth = CLIENT.font.width(str) - 1;

        displayMode = getSettings().getDisplayMode();
        int width = displayMode.calculateWidth(ICON_WIDTH, strWidth);

        color = (SETTINGS.useDynamicColor ? (Helper.getItemBarColor(4 - step, 4)) : SETTINGS.color) | 0xFF000000;

        setWidthHeightColor(width, ICON_HEIGHT, color);

        return str != null;
    }

    public int getStep(double tps) {
        if (tps >= 19.9) return 0;
        else if (tps >= 19.5) return 1;
        else if (tps >= 18.0) return 2;
        else if (tps >= 15) return 3;
        else return 4;
    }

    @Override
    public boolean renderHUD(GuiGraphicsExtractor context, int x, int y, boolean drawBackground, boolean drawTextShadow) {

        int w = getWidth();
        int h = getHeight();

        return RenderUtils.drawSmallHUD(
                context,
                str,
                x, y,
                w, h,
                TEXTURE,
                0.0F, ICON_HEIGHT * step,
                TEXTURE_WIDTH, TEXTURE_HEIGHT,
                ICON_WIDTH, ICON_HEIGHT,
                color,
                displayMode,
                drawBackground,
                drawTextShadow
        );
    }

    @Override
    public String getName() {
        return "TPS HUD";
    }

    @Override
    public String getId() {
        return HUDId.TPS.toString();
    }
}
