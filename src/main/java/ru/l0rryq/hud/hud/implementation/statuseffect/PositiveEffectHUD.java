package ru.l0rryq.hud.hud.implementation.statuseffect;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.statuseffect.EffectSettings;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

public class PositiveEffectHUD extends AbstractEffectHUD {

    private static final EffectSettings SETTINGS = Main.settings.effectSettings.positiveSettings;

    public PositiveEffectHUD() {
        super(SETTINGS);
    }

    @Override
    public boolean isEffectAllowedToRender(Holder<MobEffect> registryEntry) {
        return registryEntry.value().isBeneficial();
    }

    @Override
    public String getName() {
        return "Positive Effect HUD";
    }

    @Override
    public String getId() {
        return HUDId.POSITIVE_EFFECT.toString();
    }
}
