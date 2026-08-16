package ru.l0rryq.hud.mixin;

import ru.l0rryq.hud.helper.VanillaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.toasts.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToastManager.class)
public class MixinToastManager {

    @Inject(
            method = "extractRenderState",
            at = @At("HEAD")
    )
    private void lryq$beginToastRender(GuiGraphicsExtractor context, CallbackInfo ci) {
        int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        VanillaTracker.begin(context, "VANILLA_TOAST_MESSAGE", screenWidth - 160.0f, 0.0f);
    }

    @Inject(
            method = "extractRenderState",
            at = @At("RETURN")
    )
    private void lryq$endToastRender(GuiGraphicsExtractor context, CallbackInfo ci) {
        VanillaTracker.end(context, "VANILLA_TOAST_MESSAGE");
    }
}
