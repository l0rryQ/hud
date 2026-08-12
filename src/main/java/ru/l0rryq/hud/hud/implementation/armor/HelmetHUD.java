package ru.l0rryq.hud.hud.implementation.armor;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.armor.ArmorSettings;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;

public class HelmetHUD extends AbstractArmorHUD {

    private static final ArmorSettings SETTINGS = Main.settings.armorSettings.helmet;
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/helmet.png");

    public HelmetHUD() {
        super(SETTINGS, TEXTURE, EquipmentSlot.HEAD);
    }

    @Override
    public String getName() {
        return "Helmet HUD";
    }

    @Override
    public String getId() {
        return HUDId.HELMET.toString();
    }
}
