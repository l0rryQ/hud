package ru.l0rryq.hud.init;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.GeneralSettings;
import ru.l0rryq.hud.config.Settings;
import ru.l0rryq.hud.helper.AttackTracker;
import ru.l0rryq.hud.hud.HUDComponent;
import ru.l0rryq.hud.screen.EditHUDScreen;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class EventInit {

    private static final GeneralSettings.InGameHUDSettings SETTINGS = Main.settings.generalSettings.inGameSettings;

    public static void init() {

        // register AttackTracker events, for combo and reach.
        AttackEntityCallback.EVENT.register(AttackTracker::onAttack);
        ClientTickEvents.END_CLIENT_TICK.register(AttackTracker::onEndTick);

        // register keybinding event, on openEditHUDKey pressed -> move screen to edit hud screen.
        ClientTickEvents.END_CLIENT_TICK.register(EventInit::onOpenEditHUDKeyPressed);
        ClientTickEvents.END_CLIENT_TICK.register(EventInit::onToggleHUDKeyPressed);

        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> AutoConfig.getConfigHolder(Settings.class).save());

        // register hud element into before hotbar. I hope this was safe enough.
        HudElementRegistry.attachElementBefore(VanillaHudElements.HOTBAR, Identifier.withDefaultNamespace("lryq-hud"), EventInit::onHUDRender);
    }

    public static void onOpenEditHUDKeyPressed(Minecraft client) {
        while (Main.openEditHUDKey.consumeClick()) {
            client.setScreenAndShow(new EditHUDScreen(Component.nullToEmpty("Edit HUD"), client.gui.screen()));
        }
    }

    public static void onToggleHUDKeyPressed(Minecraft client) {
        while (Main.toggleHUDKey.consumeClick()) {
            Main.settings.generalSettings.inGameSettings.disableHUDRendering = !Main.settings.generalSettings.inGameSettings.disableHUDRendering;
        }
    }

    public static void onHUDRender(GuiGraphicsExtractor context, DeltaTracker tickCounter) {
        if (SETTINGS.disableHUDRendering) return;
        if (Minecraft.getInstance().gui.hud.isHidden()) return;
        if (Minecraft.getInstance().gui.screen() instanceof EditHUDScreen) return;

        HUDComponent.getInstance().collectAll();
        HUDComponent.getInstance().renderAll(context);
    }


}
