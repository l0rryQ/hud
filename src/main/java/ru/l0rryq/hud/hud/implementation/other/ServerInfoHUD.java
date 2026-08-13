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

    // We'll use a built-in icon sprite/texture for singleplayer or fallback if favicon is not available.
    // Minecraft uses "world_list/world_icon" sprite, or we can use custom icons. Let's make a fallback identifier or use world icon sprite.
    private static final Identifier WORLD_ICON = Identifier.fromNamespaceAndPath("minecraft", "textures/gui/sprites/world_list/world_icon.png");
    private static final Identifier DEFAULT_FAVICON = Identifier.fromNamespaceAndPath("minecraft", "textures/misc/unknown_server.png");

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

        if (CLIENT.isLocalServer()) {
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

        // Singleplayer -> world icon. Multiplayer -> server icon if available, else default favicon.
        Identifier texture = WORLD_ICON;
        if (!CLIENT.isLocalServer()) {
            // Note: Since downloading and keeping track of dynamic favicons in memory might require texture registration / dynamic textures
            // or using the favicon texture that Minecraft already registers for server entries, we'll try to find if there is an existing texture
            // registered or just fall back to standard icons.
            // Let's check how ServerData favicon works. Often, ServerData.getIconBytes() is used, but it's loaded dynamically in Multiplayer screen.
            // If there's no easy way to render dynamic favicon bytes without writing a custom dynamic texture class, we can fall back to a clean default server icon.
            texture = DEFAULT_FAVICON;
        }

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
