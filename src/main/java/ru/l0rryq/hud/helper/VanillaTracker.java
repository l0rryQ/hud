package ru.l0rryq.hud.helper;

import ru.l0rryq.hud.hud.AbstractHUD;
import ru.l0rryq.hud.hud.HUDComponent;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class VanillaTracker {
    public static void begin(GuiGraphicsExtractor context, String hudId, float defaultX, float defaultY) {
        AbstractHUD hud = HUDComponent.getInstance().getHUD(hudId);
        if (hud != null) {
            float scale = hud.getScale();
            float x = hud.getX();
            float y = hud.getY();

            context.pose().pushMatrix();
            context.pose().translate(x, y);
            context.pose().scale(scale, scale);
            context.pose().translate(-defaultX, -defaultY);
        }
    }

    public static void end(GuiGraphicsExtractor context, String hudId) {
        AbstractHUD hud = HUDComponent.getInstance().getHUD(hudId);
        if (hud != null) {
            context.pose().popMatrix();
        }
    }
}
