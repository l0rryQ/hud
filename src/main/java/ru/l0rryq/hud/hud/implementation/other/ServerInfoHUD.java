package ru.l0rryq.hud.hud.implementation.other;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.other.ServerInfoSettings;
import ru.l0rryq.hud.helper.HUDDisplayMode;
import ru.l0rryq.hud.helper.RenderUtils;
import ru.l0rryq.hud.hud.AbstractHUD;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class ServerInfoHUD extends AbstractHUD {

    private static final ServerInfoSettings SETTINGS = Main.settings.serverInfoSettings;

    private static final Identifier WORLD_ICON = Identifier.fromNamespaceAndPath("lryq-hud", "hud/world_info.png");
    private static final Identifier SERVER_ICON = Identifier.fromNamespaceAndPath("lryq-hud", "hud/server_info.png");

    private static final int ICON_WIDTH = 13;
    private static final int ICON_HEIGHT = 13;

    private static final Minecraft CLIENT = Minecraft.getInstance();

    private String serverText;
    private HUDDisplayMode displayMode;

    public ServerInfoHUD() {
        super(SETTINGS.base);
    }

    @Override
    public boolean collectHUDInformation() {
        if (CLIENT.player == null) return false;

        boolean isSingleplayer = CLIENT.isLocalServer() || CLIENT.getCurrentServer() == null;
        if (isSingleplayer) {
            if (SETTINGS.showOnlyOnServers) {
                return false;
            }
            if (CLIENT.getSingleplayerServer() != null && CLIENT.getSingleplayerServer().getWorldData() != null) {
                serverText = CLIENT.getSingleplayerServer().getWorldData().getLevelName();
            } else {
                serverText = "Singleplayer";
            }
        } else {
            ServerData serverData = CLIENT.getCurrentServer();
            if (serverData != null) {
                serverText = serverData.ip;
            } else {
                serverText = "Unknown Server";
            }
        }

        if (SETTINGS.additionalString != null && !SETTINGS.additionalString.isEmpty()) {
            serverText += SETTINGS.additionalString;
        }

        serverText = getFormattedText(serverText);
        int strWidth = CLIENT.font.width(serverText) - 1;

        displayMode = getSettings().getDisplayMode();
        int width = displayMode.calculateWidth(ICON_WIDTH, strWidth);

        int color = SETTINGS.color | 0xFF000000;

        setWidthHeightColor(width, ICON_HEIGHT, color);

        return serverText != null;
    }

    @Override
    public boolean renderHUD(GuiGraphicsExtractor context, int x, int y, boolean drawBackground, boolean drawTextShadow) {
        int w = getWidth();
        int h = getHeight();
        int c = getColor();

        boolean isSingleplayer = CLIENT.isLocalServer() || CLIENT.getCurrentServer() == null;
        Identifier texture = isSingleplayer ? WORLD_ICON : SERVER_ICON;

        return RenderUtils.drawSmallHUD(
                context,
                serverText,
                x, y,
                w, h,
                texture,
                0.0F, 0.0F,
                ICON_WIDTH, ICON_HEIGHT,
                ICON_WIDTH, ICON_HEIGHT,
                c,
                displayMode,
                drawBackground,
                drawTextShadow
        );
    }

    @Override
    public String getName() {
        return "Server Info HUD";
    }

    @Override
    public String getId() {
        return HUDId.SERVER_INFO.toString();
    }
}
