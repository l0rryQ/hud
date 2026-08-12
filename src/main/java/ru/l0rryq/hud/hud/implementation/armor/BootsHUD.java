package ru.l0rryq.hud.hud.implementation.armor;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.armor.ArmorSettings;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;

public class BootsHUD extends AbstractArmorHUD {

    private static final ArmorSettings SETTINGS = Main.settings.armorSettings.boots;
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/boots.png");

    public BootsHUD() {
        super(SETTINGS, TEXTURE, EquipmentSlot.FEET);
    }

    @Override
    public String getName() {
        return "Boots HUD";
    }

    @Override
    public String getId() {
        return HUDId.BOOTS.toString();
    }

}
