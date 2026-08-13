package ru.l0rryq.hud.hud.implementation.other;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.other.FPSSettings;
import ru.l0rryq.hud.helper.HUDDisplayMode;
import ru.l0rryq.hud.helper.RenderUtils;
import ru.l0rryq.hud.hud.AbstractHUD;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

public class FPSHUD extends AbstractHUD {

    private static final FPSSettings FPS_SETTINGS = Main.settings.fpsSettings;

    private static final Identifier FPS_TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/fps.png");

    private static final int TEXTURE_WIDTH = 13;
    private static final int TEXTURE_HEIGHT = 13;
    private static final int ICON_WIDTH = 13;
    private static final int ICON_HEIGHT = 13;

    private static final Minecraft CLIENT = Minecraft.getInstance();

    public FPSHUD() {
        super(FPS_SETTINGS.base);
    }

    @Override
    public String getName() {
        return "FPS HUD";
    }

    @Override
    public String getId() {
        return HUDId.FPS.toString();
    }

    private String fpsStr;
    private HUDDisplayMode displayMode;

    @Override
    public boolean collectHUDInformation() {
        fpsStr = getFormattedText(CLIENT.getFps() + FPS_SETTINGS.additionalString);
        int strWidth = CLIENT.font.width(fpsStr) - 1;

        displayMode = getSettings().getDisplayMode();

        int width = displayMode.calculateWidth(ICON_WIDTH, strWidth);

        int color = FPS_SETTINGS.color | 0xFF000000;

        setWidthHeightColor(width, TEXTURE_HEIGHT, color);

        return fpsStr != null;
    }

    @Override
    public boolean renderHUD(GuiGraphicsExtractor context, int x, int y, boolean drawBackground, boolean drawTextShadow) {

        int w = getWidth();
        int h = getHeight();
        int c = getColor();

        return RenderUtils.drawSmallHUD(
                context,
                fpsStr,
                x, y,
                w, h,
                FPS_TEXTURE,
                0.0F, 0.0F,
                TEXTURE_WIDTH, TEXTURE_HEIGHT,
                ICON_WIDTH, ICON_HEIGHT,
                c,
                displayMode,
                drawBackground,
                drawTextShadow
        );
    }
}
