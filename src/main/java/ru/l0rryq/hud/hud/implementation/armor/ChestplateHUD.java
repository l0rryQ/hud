package ru.l0rryq.hud.hud.implementation.armor;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.armor.ArmorSettings;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;

public class ChestplateHUD extends AbstractArmorHUD {
    private static final ArmorSettings SETTINGS = Main.settings.armorSettings.chestplate;
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/chestplate.png");

    public ChestplateHUD() {
        super(SETTINGS, TEXTURE, EquipmentSlot.CHEST);
    }

    @Override
    public String getName() {
        return "Chestplate HUD";
    }

    @Override
    public String getId() {
        return HUDId.CHESTPLATE.toString();
    }
}
