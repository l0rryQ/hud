package ru.l0rryq.hud.hud.implementation.other;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.other.SpeedSettings;
import ru.l0rryq.hud.helper.HUDDisplayMode;
import ru.l0rryq.hud.helper.RenderUtils;
import ru.l0rryq.hud.hud.AbstractHUD;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class SpeedHUD extends AbstractHUD {

    private static final Minecraft CLIENT = Minecraft.getInstance();
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/speed.png");

    private static final int TEXTURE_WIDTH = 13;
    private static final int TEXTURE_HEIGHT = 13;
    private static final int ICON_WIDTH = 13;
    private static final int ICON_HEIGHT = 13;

    private static final SpeedSettings SETTINGS = Main.settings.speedSettings;

    public SpeedHUD() {
        super(SETTINGS.base);
    }

    private String str;
    private int color;
    private HUDDisplayMode displayMode;

    @Override
    public boolean collectHUDInformation() {

        if (CLIENT.player == null) return false;

        Entity entity = CLIENT.player.getVehicle() != null ? CLIENT.player.getVehicle() : CLIENT.player;
        Vec3 vel = entity.getKnownSpeed();

        double speed = SETTINGS.useFullSpeed ? vel.length() : vel.horizontalDistanceSqr();
        speed = (double) Math.round(speed * 20.0 * 10) / 10;

        str = speed + SETTINGS.additionalString;
        int strWidth = CLIENT.font.width(str) - 1;

        displayMode = getSettings().getDisplayMode();
        int width = displayMode.calculateWidth(ICON_WIDTH, strWidth);
        color = SETTINGS.color | 0xff000000;

        setWidthHeightColor(width, ICON_HEIGHT, color);

        return str != null;
    }

    @Override
    public boolean renderHUD(GuiGraphicsExtractor context, int x, int y, boolean drawBackground, boolean drawTextShadow) {

        int w = getWidth();
        int h = getHeight();

        return RenderUtils.drawSmallHUD(
                context,
                str,
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

    @Override
    public String getName() {
        return "Speed Counter HUD";
    }

    @Override
    public String getId() {
        return HUDId.SPEED.toString();
    }
}
