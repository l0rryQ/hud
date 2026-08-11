package ru.l0rryq.hud.condition;

import ru.l0rryq.hud.helper.Box;
import ru.l0rryq.hud.mixin.accessor.AccessorPlayerListHud;
import net.minecraft.client.Minecraft;

public class PlayerListHUD {

    private static final Minecraft CLIENT = Minecraft.getInstance();

    public static final Box boundingBox = new Box(0,0, 0, 0);

    public static boolean isShown(String ignored) {
        return ((AccessorPlayerListHud) CLIENT.gui.hud.getTabList()).isVisible();
    }

    public static int getWidth() {
        return boundingBox.getWidth();
    }

    public static int getHeight() {
        return boundingBox.getHeight();
    }
}
