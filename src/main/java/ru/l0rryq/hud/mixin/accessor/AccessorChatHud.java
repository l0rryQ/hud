package ru.l0rryq.hud.mixin.accessor;

import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ChatComponent.class)
public interface AccessorChatHud {

    @Invoker("getHeight")
    int lryq_hud$getHeight();
    @Invoker("getWidth")
    int lryq_hud$getWidth();

}
