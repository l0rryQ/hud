package ru.l0rryq.hud.hud.implementation.coordinate;

import ru.l0rryq.hud.config.hud.coordinate.CoordSettings;
import ru.l0rryq.hud.helper.HUDDisplayMode;
import ru.l0rryq.hud.helper.RenderUtils;
import ru.l0rryq.hud.hud.AbstractHUD;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

public abstract class AbstractCoordinateHUD extends AbstractHUD {

    protected static final Minecraft CLIENT = Minecraft.getInstance();

    public final CoordSettings SETTINGS;
    public final Identifier TEXTURE;

    private static final int TEXTURE_WIDTH = 13;
    private static final int TEXTURE_HEIGHT = 13;
    private static final int ICON_WIDTH = 13;
    private static final int ICON_HEIGHT = 13;

    public AbstractCoordinateHUD(CoordSettings coordSettings, Identifier TEXTURE) {
        super(coordSettings.base);

        this.SETTINGS = coordSettings;
        this.TEXTURE = TEXTURE;
    }

    public abstract int getCoord();

    private String coordStr;
    private int color;
    private HUDDisplayMode displayMode;

    @Override
    public boolean collectHUDInformation() {
        coordStr = getFormattedText(Integer.toString(getCoord()));
        int strWidth = CLIENT.font.width(coordStr) - 1;

        displayMode = getSettings().getDisplayMode();

        int width = displayMode.calculateWidth(ICON_WIDTH, strWidth);

        color = SETTINGS.color | 0xFF000000;

        setWidthHeightColor(width, ICON_HEIGHT, color);

        return coordStr != null;
    }

    @Override
    public boolean renderHUD(GuiGraphicsExtractor context, int x, int y, boolean drawBackground, boolean drawTextShadow) {

        int w = getWidth();
        int h = getHeight();

        return RenderUtils.drawSmallHUD(
                context,
                coordStr,
                x, y,
                w, h,
                TEXTURE,
                0.0F, 0.0F,
                TEXTURE_WIDTH, TEXTURE_HEIGHT,
                ICON_WIDTH, ICON_HEIGHT,
                color,
                displayMode,
                drawBackground,
                drawTextShadow
        );
    }
}
