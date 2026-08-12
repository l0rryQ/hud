package ru.l0rryq.hud.hud.implementation.coordinate.nether;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.coordinate.CoordSettings;
import ru.l0rryq.hud.hud.HUDId;
import ru.l0rryq.hud.hud.implementation.coordinate.AbstractCoordinateHUD;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;

public class NetherYCoordinate extends AbstractCoordinateHUD {

    private static final CoordSettings SETTINGS = Main.settings.coordSettings.netherY;
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/coordinate_y.png");

    public NetherYCoordinate() {
        super(SETTINGS, TEXTURE);
    }

    @Override
    public boolean shouldRender() {
        return super.shouldRender() && (CLIENT.player.level().dimension() == Level.OVERWORLD || CLIENT.player.level().dimension() == Level.NETHER);
    }

    @Override
    public int getCoord() {
        return (int) CLIENT.player.position().y;
    }

    @Override
    public String getName() {
        return "Nether Y Coordinate";
    }

    @Override
    public String getId() {
        return HUDId.NETHER_Y_COORDINATE.toString();
    }
}
