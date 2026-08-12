package ru.l0rryq.hud.hud.implementation.coordinate.normal;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.coordinate.CoordSettings;
import ru.l0rryq.hud.hud.HUDId;
import ru.l0rryq.hud.hud.implementation.coordinate.AbstractCoordinateHUD;
import net.minecraft.resources.Identifier;

public class XCoordinateHUD extends AbstractCoordinateHUD {

    private static final CoordSettings SETTINGS = Main.settings.coordSettings.X;
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/coordinate_x.png");

    public XCoordinateHUD() {
        super(SETTINGS, TEXTURE);
    }

    @Override
    public int getCoord() {
        return (int) CLIENT.player.position().x;
    }

    @Override
    public String getName() {
        return "X Coordinate HUD";
    }

    @Override
    public String getId() {
        return HUDId.X_COORDINATE.toString();
    }
}
