package ru.l0rryq.hud.condition;

import net.minecraft.client.Minecraft;

public class DebugHUD {
    private static final Minecraft CLIENT = Minecraft.getInstance();

    public static boolean isShown(String ignored) {
        return CLIENT.debugEntries.isOverlayVisible();
    }
}
