package ru.l0rryq.hud.mixin.accessor;

import net.minecraft.client.gui.components.PlayerTabOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PlayerTabOverlay.class)
public interface AccessorPlayerListHud {

    @Accessor("visible")
    boolean isVisible();
}
