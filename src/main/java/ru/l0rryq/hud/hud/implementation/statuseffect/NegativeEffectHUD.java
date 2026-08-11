package ru.l0rryq.hud.hud.implementation.statuseffect;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.statuseffect.EffectSettings;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

public class NegativeEffectHUD extends AbstractEffectHUD {

    private static final EffectSettings SETTINGS = Main.settings.effectSettings.negativeSettings;

    public NegativeEffectHUD() {
        super(SETTINGS);
    }

    @Override
    public boolean isEffectAllowedToRender(Holder<MobEffect> registryEntry) {
        return !registryEntry.value().isBeneficial();
    }

    @Override
    public String getName() {
        return "Negative Effect HUD";
    }

    @Override
    public String getId() {
        return HUDId.NEGATIVE_EFFECT.toString();
    }
}
