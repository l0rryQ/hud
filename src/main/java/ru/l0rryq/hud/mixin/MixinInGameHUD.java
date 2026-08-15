package ru.l0rryq.hud.mixin;

import ru.l0rryq.hud.condition.HeldItemTooltip;
import ru.l0rryq.hud.condition.ScoreboardHUD;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Hud.class, priority = 500)
public class MixinInGameHUD {

    @org.spongepowered.asm.mixin.injection.Inject(
            method = "extractBossOverlay",
            at = @At("HEAD")
    )
    private void lryq$beginBossOverlay(GuiGraphicsExtractor context, net.minecraft.client.DeltaTracker deltaTracker, org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        int screenWidth = net.minecraft.client.Minecraft.getInstance().getWindow().getGuiScaledWidth();
        ru.l0rryq.hud.helper.VanillaTracker.begin(context, "VANILLA_BOSS_BAR", (screenWidth / 2.0f) - 91.0f, 12.0f);
    }

    @org.spongepowered.asm.mixin.injection.Inject(
            method = "extractBossOverlay",
            at = @At("RETURN")
    )
    private void lryq$endBossOverlay(GuiGraphicsExtractor context, net.minecraft.client.DeltaTracker deltaTracker, org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        ru.l0rryq.hud.helper.VanillaTracker.end(context, "VANILLA_BOSS_BAR");
    }

    @org.spongepowered.asm.mixin.injection.Inject(
            method = "extractOverlayMessage",
            at = @At("HEAD")
    )
    private void lryq$beginOverlayMessage(GuiGraphicsExtractor context, net.minecraft.client.DeltaTracker deltaTracker, org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        int screenWidth = net.minecraft.client.Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int screenHeight = net.minecraft.client.Minecraft.getInstance().getWindow().getGuiScaledHeight();
        ru.l0rryq.hud.helper.VanillaTracker.begin(context, "VANILLA_ACTION_BAR", (screenWidth / 2.0f) - 100.0f, screenHeight - 68.0f);
    }

    @org.spongepowered.asm.mixin.injection.Inject(
            method = "extractOverlayMessage",
            at = @At("RETURN")
    )
    private void lryq$endOverlayMessage(GuiGraphicsExtractor context, net.minecraft.client.DeltaTracker deltaTracker, org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        ru.l0rryq.hud.helper.VanillaTracker.end(context, "VANILLA_ACTION_BAR");
    }

    @org.spongepowered.asm.mixin.injection.Inject(
            method = "extractSubtitleOverlay",
            at = @At("HEAD")
    )
    private void lryq$beginSubtitleOverlay(GuiGraphicsExtractor context, boolean bl, org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        int screenWidth = net.minecraft.client.Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int screenHeight = net.minecraft.client.Minecraft.getInstance().getWindow().getGuiScaledHeight();
        ru.l0rryq.hud.helper.VanillaTracker.begin(context, "VANILLA_CLOSED_CAPTION", screenWidth - 160.0f, screenHeight - 110.0f);
    }

    @org.spongepowered.asm.mixin.injection.Inject(
            method = "extractSubtitleOverlay",
            at = @At("RETURN")
    )
    private void lryq$endSubtitleOverlay(GuiGraphicsExtractor context, boolean bl, org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        ru.l0rryq.hud.helper.VanillaTracker.end(context, "VANILLA_CLOSED_CAPTION");
    }

    @org.spongepowered.asm.mixin.injection.Inject(
            method = "extractItemHotbar",
            at = @At("HEAD")
    )
    private void lryq$beginItemHotbar(GuiGraphicsExtractor context, net.minecraft.client.DeltaTracker deltaTracker, org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        int screenWidth = net.minecraft.client.Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int screenHeight = net.minecraft.client.Minecraft.getInstance().getWindow().getGuiScaledHeight();
        ru.l0rryq.hud.helper.VanillaTracker.begin(context, "VANILLA_HOTBAR_GROUP", (screenWidth / 2.0f) - 91.0f, screenHeight - 22.0f);
    }

    @org.spongepowered.asm.mixin.injection.Inject(
            method = "extractItemHotbar",
            at = @At("RETURN")
    )
    private void lryq$endItemHotbar(GuiGraphicsExtractor context, net.minecraft.client.DeltaTracker deltaTracker, org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        ru.l0rryq.hud.helper.VanillaTracker.end(context, "VANILLA_HOTBAR_GROUP");
    }

    @org.spongepowered.asm.mixin.injection.Inject(
            method = "extractTabList",
            at = @At("HEAD")
    )
    private void lryq$beginTabList(GuiGraphicsExtractor context, net.minecraft.client.DeltaTracker deltaTracker, org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        int screenWidth = net.minecraft.client.Minecraft.getInstance().getWindow().getGuiScaledWidth();
        ru.l0rryq.hud.helper.VanillaTracker.begin(context, "VANILLA_PLAYER_LIST", (screenWidth / 2.0f) - 125.0f, 10.0f);
    }

    @org.spongepowered.asm.mixin.injection.Inject(
            method = "extractTabList",
            at = @At("RETURN")
    )
    private void lryq$endTabList(GuiGraphicsExtractor context, net.minecraft.client.DeltaTracker deltaTracker, org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        ru.l0rryq.hud.helper.VanillaTracker.end(context, "VANILLA_PLAYER_LIST");
    }

    @org.spongepowered.asm.mixin.injection.Inject(
            method = "extractScoreboardSidebar",
            at = @At("HEAD")
    )
    private void lryq$beginScoreboardSidebar(GuiGraphicsExtractor context, net.minecraft.client.DeltaTracker deltaTracker, org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        int screenWidth = net.minecraft.client.Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int screenHeight = net.minecraft.client.Minecraft.getInstance().getWindow().getGuiScaledHeight();
        ru.l0rryq.hud.helper.VanillaTracker.begin(context, "VANILLA_SCOREBOARD", screenWidth - 130.0f, (screenHeight / 2.0f) - 75.0f);
    }

    @org.spongepowered.asm.mixin.injection.Inject(
            method = "extractScoreboardSidebar",
            at = @At("RETURN")
    )
    private void lryq$endScoreboardSidebar(GuiGraphicsExtractor context, net.minecraft.client.DeltaTracker deltaTracker, org.spongepowered.asm.mixin.injection.callback.CallbackInfo ci) {
        ru.l0rryq.hud.helper.VanillaTracker.end(context, "VANILLA_SCOREBOARD");
    }

    @Redirect(
            method = "displayScoreboardSidebar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V",
                    ordinal = 1
            ),
            require = 0
    )
    private void captureScoreboardFill(GuiGraphicsExtractor instance, int x1, int y1, int x2, int y2, int color) {
        ScoreboardHUD.captureBoundingBox(x1, y1 - 9, x2, y2); // -9 due to the first fill call is for header, which has 9 additional offset
        instance.fill(x1, y1, x2 ,y2 , color);
    }

    @Redirect(
            method = "extractSelectedItemName",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;textWithBackdrop(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIII)V"
            ),
            require = 0
    )
    private void captureTooltipBox(GuiGraphicsExtractor instance, Font textRenderer, Component text, int x, int y, int width, int color) {
        HeldItemTooltip.setBoundingBox(x, y, width, 2 + 9 + 2);
        instance.textWithBackdrop(textRenderer, text, x, y, width, color);
    }
}
