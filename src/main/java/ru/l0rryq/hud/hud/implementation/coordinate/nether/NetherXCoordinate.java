package ru.l0rryq.hud.hud.implementation.coordinate.nether;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.coordinate.CoordSettings;
import ru.l0rryq.hud.hud.HUDId;
import ru.l0rryq.hud.hud.implementation.coordinate.AbstractCoordinateHUD;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NetherXCoordinate extends AbstractCoordinateHUD {

    private static final Minecraft CLIENT = Minecraft.getInstance();
    private static final CoordSettings SETTINGS = Main.settings.coordSettings.netherX;
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/coordinate_x.png");

    public NetherXCoordinate() {
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
            return (int) (pos.x * 8);
        } else if (world.dimension() == Level.OVERWORLD) {
            return (int) (pos.x / 8);
        } else {
            return (int) pos.x;
        }
    }

    @Override
    public String getName() {
        return "Nether X Coordinate";
    }

    @Override
    public String getId() {
        return HUDId.NETHER_X_COORDINATE.toString();
    }
}
