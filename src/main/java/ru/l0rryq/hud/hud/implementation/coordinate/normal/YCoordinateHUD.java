package ru.l0rryq.hud.hud.implementation.coordinate.normal;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.coordinate.CoordSettings;
import ru.l0rryq.hud.hud.HUDId;
import ru.l0rryq.hud.hud.implementation.coordinate.AbstractCoordinateHUD;
import net.minecraft.resources.Identifier;

public class YCoordinateHUD extends AbstractCoordinateHUD {

    private static final CoordSettings SETTINGS = Main.settings.coordSettings.Y;
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/coordinate_y.png");

    public YCoordinateHUD() {
        super(SETTINGS, TEXTURE);
    }

    @Override
    public int getCoord() {
        return (int) CLIENT.player.position().y;
    }

    @Override
    public String getName() {
        return "Y Coordinate HUD";
    }

    @Override
    public String getId() {
        return HUDId.Y_COORDINATE.toString();
    }
}
