package ru.l0rryq.hud.condition;

import ru.l0rryq.hud.helper.Box;
import ru.l0rryq.hud.mixin.accessor.AccessorInGameHUD;
import net.minecraft.client.Minecraft;

public class HeldItemTooltip {

    private static final Minecraft CLIENT = Minecraft.getInstance();

    public static final Box boundingBox = new Box(0,0, 0, 0);

    public static boolean isShown(String ignored) {
        return ((AccessorInGameHUD) CLIENT.gui).getToolHighlightTimer() > 0;
    }

    public static int getWidth() {
        return boundingBox.getWidth();
    }

    public static int getHeight() {
        return boundingBox.getHeight();
    }

    public static void setBoundingBox(int x, int y, int width, int height) {
        boundingBox.setBoundingBox(x, y, width, height);
    }
}
