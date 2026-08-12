package ru.l0rryq.hud.hud.implementation.coordinate.normal;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.coordinate.CoordSettings;
import ru.l0rryq.hud.hud.HUDId;
import ru.l0rryq.hud.hud.implementation.coordinate.AbstractCoordinateHUD;
import net.minecraft.resources.Identifier;

public class ZCoordinateHUD extends AbstractCoordinateHUD {
    private static final CoordSettings SETTINGS = Main.settings.coordSettings.Z;
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/coordinate_z.png");

    public ZCoordinateHUD() {
        super(SETTINGS, TEXTURE);
    }

    @Override
    public int getCoord() {
        return (int) CLIENT.player.position().z;
    }

    @Override
    public String getName() {
        return "Z Coordinate HUD";
    }

    @Override
    public String getId() {
        return HUDId.Z_COORDINATE.toString();
    }
}
