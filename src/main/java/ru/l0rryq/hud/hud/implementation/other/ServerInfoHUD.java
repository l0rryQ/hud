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
import net.minecraft.client.gui.screens.FaviconTexture;
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

    private FaviconTexture faviconTexture;
    private byte[] lastUploadedIconBytes = null;
    private java.nio.file.Path lastUploadedWorldIconPath = null;
    private boolean hasCustomFavicon = false;

    public ServerInfoHUD() {
        super(SETTINGS.base);
    }

    @Override
    public boolean collectHUDInformation() {
        boolean isEditScreen = CLIENT.gui.screen() instanceof ru.l0rryq.hud.screen.EditHUDScreen;
        if (CLIENT.player == null && !isEditScreen) return false;

        boolean isSingleplayer = CLIENT.isLocalServer() || CLIENT.getCurrentServer() == null;
        if (isSingleplayer) {
            if (SETTINGS.showOnlyOnServers && !isEditScreen) {
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

        if (faviconTexture == null) {
            faviconTexture = FaviconTexture.forServer(CLIENT.getTextureManager(), "lryq_hud_server_info");
        }

        hasCustomFavicon = false;

        if (isSingleplayer) {
            if (CLIENT.getSingleplayerServer() != null) {
                try {
                    java.nio.file.Path iconPath = CLIENT.getSingleplayerServer().getWorldPath(net.minecraft.world.level.storage.LevelResource.ICON_FILE);
                    if (java.nio.file.Files.exists(iconPath)) {
                        hasCustomFavicon = true;
                        if (!iconPath.equals(lastUploadedWorldIconPath)) {
                            lastUploadedWorldIconPath = iconPath;
                            lastUploadedIconBytes = null;
                            try (java.io.InputStream stream = java.nio.file.Files.newInputStream(iconPath)) {
                                com.mojang.blaze3d.platform.NativeImage nativeImage = com.mojang.blaze3d.platform.NativeImage.read(stream);
                                faviconTexture.upload(nativeImage);
                            }
                        }
                    }
                } catch (Exception e) {
                    // fallback
                }
            }
        } else {
            ServerData serverData = CLIENT.getCurrentServer();
            if (serverData != null) {
                byte[] bytes = serverData.getIconBytes();
                if (bytes != null && bytes.length > 0) {
                    hasCustomFavicon = true;
                    if (!java.util.Arrays.equals(bytes, lastUploadedIconBytes)) {
                        lastUploadedIconBytes = bytes;
                        lastUploadedWorldIconPath = null;
                        try (java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(bytes)) {
                            com.mojang.blaze3d.platform.NativeImage nativeImage = com.mojang.blaze3d.platform.NativeImage.read(bais);
                            faviconTexture.upload(nativeImage);
                        } catch (Exception e) {
                            hasCustomFavicon = false;
                        }
                    }
                }
            }
        }

        if (hasCustomFavicon) {
            texture = faviconTexture.textureLocation();
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
                0xFFFFFFFF,
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
