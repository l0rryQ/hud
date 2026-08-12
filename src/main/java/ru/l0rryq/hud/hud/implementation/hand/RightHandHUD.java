package ru.l0rryq.hud.hud.implementation.hand;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.hand.HandSettings;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.HumanoidArm;

public class RightHandHUD extends AbstractHandHUD {

    private static final HandSettings RIGHT_HAND_SETTINGS = Main.settings.handSettings.rightHandSettings;
    private static final Identifier RIGHT_HAND_TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/hand_right.png");

    public RightHandHUD() {
        super(RIGHT_HAND_SETTINGS, HumanoidArm.RIGHT, RIGHT_HAND_TEXTURE);
    }

    @Override
    public String getName() {
        return "Right Hand HUD";
    }

    @Override
    public String getId() {
        return HUDId.RIGHT_HAND.toString();
    }

    @Override
    public int getIconColor() {
        return RIGHT_HAND_SETTINGS.color | 0xFF000000;
    }
}
