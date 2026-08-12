package ru.l0rryq.hud.condition;

import ru.l0rryq.hud.mixin.accessor.AccessorChatHud;
import net.minecraft.client.Minecraft;

public class ChatHUD {
    private static final Minecraft CLIENT = Minecraft.getInstance();

    public static boolean isShown(String ignored) {
        return CLIENT.gui.hud.getChat().isChatFocused();
    }

    public static int getWidth() {
        return ((AccessorChatHud) CLIENT.gui.hud.getChat()).lryq_hud$getWidth();
    }

    public static int getHeight() {
        return ((AccessorChatHud) CLIENT.gui.hud.getChat()).lryq_hud$getHeight();
    }
}
