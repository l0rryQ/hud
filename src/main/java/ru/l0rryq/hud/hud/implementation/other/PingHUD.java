package ru.l0rryq.hud.hud.implementation.other;

import ru.l0rryq.hud.Helper;
import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.other.PingSettings;
import ru.l0rryq.hud.helper.HUDDisplayMode;
import ru.l0rryq.hud.helper.RenderUtils;
import ru.l0rryq.hud.hud.AbstractHUD;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.multiplayer.PingDebugMonitor;
import net.minecraft.resources.Identifier;
import net.minecraft.util.debugchart.LocalSampleLogger;
import net.minecraft.world.level.Level;

public class PingHUD extends AbstractHUD {

    private static final PingSettings SETTINGS = Main.settings.pingSettings;

    private static final Identifier PING_TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/ping.png");

    private static final int TEXTURE_WIDTH = 13;
    private static final int TEXTURE_HEIGHT = 13 * 4;
    private static final int ICON_WIDTH = 13;
    private static final int ICON_HEIGHT = 13;

    private static long LAST_PING_UPDATE = -1L;
    private static Level LAST_WORLD = null;
    private static PingDebugMonitor cachedPingMeasurer;

    private static final Minecraft CLIENT = Minecraft.getInstance();

    public PingHUD() {
        super(SETTINGS.base);
    }

    @Override
    public String getName() {
        return "Ping HUD";
    }

    @Override
    public String getId() {
        return HUDId.PING.toString();
    }

    private String pingStr;
    private int strWidth;
    private int color;
    private int step;

    private HUDDisplayMode displayMode;

    @Override
    public boolean collectHUDInformation() {
        displayMode = getSettings().getDisplayMode();

        LocalSampleLogger pingLog = CLIENT.getDebugOverlay().getPingLogger();

        // different world and server checking for PingMeasurer renewal.
        Level currentLevel = CLIENT.level;
        if (currentLevel != LAST_WORLD) {
            cachedPingMeasurer = new PingDebugMonitor(CLIENT.getConnection(), pingLog);
            LAST_WORLD = currentLevel;
        }

        // update pingLog every n seconds. Because this is quite expensive.
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - LAST_PING_UPDATE >= 1000 * SETTINGS.updateInterval) {
            LAST_PING_UPDATE = currentTimeMillis;
            cachedPingMeasurer.tick();

            // cache string calculations here since ping was just updated
            int pingLogLen = pingLog.size();
            if (pingLogLen > 0) {
                long currentPing = pingLog.get(pingLogLen - 1);
                pingStr = getFormattedText(currentPing + SETTINGS.additionalString);
                strWidth = CLIENT.font.width(pingStr) - 1;

                step = Math.min((int) currentPing / 150, 3);
            }
        }

        color = (SETTINGS.useDynamicColor ? Helper.getItemBarColor(3 - step, 3) : SETTINGS.color) | 0xFF000000;
        int width = displayMode.calculateWidth(ICON_WIDTH, strWidth);
        setWidthHeightColor(width, ICON_HEIGHT, color);

        return pingStr != null;
    }

    @Override
    public boolean renderHUD(GuiGraphicsExtractor context, int x, int y, boolean drawBackground, boolean drawTextShadow) {

        int w = getWidth();
        int h = getHeight();

        return RenderUtils.drawSmallHUD(
                context,
                pingStr,
                x, y,
                w, h,
                PING_TEXTURE,
                0.0F, ICON_HEIGHT * step,
                TEXTURE_WIDTH, TEXTURE_HEIGHT,
                ICON_WIDTH, ICON_HEIGHT,
                color,
                displayMode,
                drawBackground,
                drawTextShadow
        );
    }

}
