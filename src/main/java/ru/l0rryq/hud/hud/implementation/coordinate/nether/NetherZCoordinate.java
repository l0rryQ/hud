package ru.l0rryq.hud.hud.implementation.coordinate.nether;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.coordinate.CoordSettings;
import ru.l0rryq.hud.hud.HUDId;
import ru.l0rryq.hud.hud.implementation.coordinate.AbstractCoordinateHUD;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NetherZCoordinate extends AbstractCoordinateHUD {

    private static final CoordSettings SETTINGS = Main.settings.coordSettings.netherZ;
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/coordinate_z.png");

    public NetherZCoordinate() {
        super(SETTINGS, TEXTURE);
    }

    @Override
    public boolean shouldRender() {
        return super.shouldRender() && (CLIENT.player.level().dimension() == Level.OVERWORLD || CLIENT.player.level().dimension() == Level.NETHER);
    }

    @Override
    public int getCoord() {
        Level world = CLIENT.player.level();
        Vec3 pos = CLIENT.player.position();

        if (world.dimension() == Level.NETHER) {
            return (int) (pos.z * 8);
        } else if (world.dimension() == Level.OVERWORLD) {
            return (int) (pos.z / 8);
        } else {
            return (int) pos.z;
        }
    }

    @Override
    public String getName() {
        return "Nether Z Coordinate";
    }

    @Override
    public String getId() {
        return HUDId.NETHER_Z_COORDINATE.toString();
    }
}
