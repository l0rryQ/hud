package ru.l0rryq.hud.hud.implementation.other;

import ru.l0rryq.hud.Main;
import ru.l0rryq.hud.config.hud.other.InventorySpaceSettings;
import ru.l0rryq.hud.helper.HUDDisplayMode;
import ru.l0rryq.hud.helper.RenderUtils;
import ru.l0rryq.hud.hud.AbstractHUD;
import ru.l0rryq.hud.hud.HUDId;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public class InventorySpaceHUD extends AbstractHUD {

    private static final Minecraft CLIENT = Minecraft.getInstance();
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("lryq-hud", "hud/inventory_space.png");
    private static final InventorySpaceSettings SETTINGS = Main.settings.inventorySpaceSettings;

    private static final int TEXTURE_WIDTH = 13;
    private static final int TEXTURE_HEIGHT = 13;
    private static final int ICON_WIDTH = 13;
    private static final int ICON_HEIGHT = 13;

    private String str;
    private HUDDisplayMode displayMode;

    public InventorySpaceHUD() {
        super(SETTINGS.base);
    }

    @Override
    public boolean collectHUDInformation() {
        if (CLIENT.player == null) return false;

        displayMode = getSettings().getDisplayMode();

        int filledSlot = 0;
        for (ItemStack stack : CLIENT.player.getInventory().getNonEquipmentItems())
            if (!stack.isEmpty())
                ++filledSlot;

        int maxSlot = CLIENT.player.getInventory().getNonEquipmentItems().size();
        int emptySlot = maxSlot - filledSlot;

        int slot = SETTINGS.showRemaining ? emptySlot : filledSlot;
        if (SETTINGS.showMaxSlot)
            str = slot + "/" + maxSlot;
        else
            str = Integer.toString(slot);

        int strWidth = CLIENT.font.width(str) - 1;

        int width = displayMode.calculateWidth(ICON_WIDTH, strWidth);
        int color = SETTINGS.color | 0xFF000000;

        setWidthHeightColor(width, TEXTURE_HEIGHT, color);

        return str != null;
    }

    @Override
    public boolean renderHUD(GuiGraphicsExtractor context, int x, int y, boolean drawBackground, boolean drawTextShadow) {
        int w = getWidth(), h = getHeight(), c = getColor();

        return RenderUtils.drawSmallHUD(
                context,
                str,
                x, y,
                w, h,
                TEXTURE,
                0, 0,
                TEXTURE_WIDTH, TEXTURE_HEIGHT,
                ICON_WIDTH, ICON_HEIGHT,
                c,
                displayMode,
                drawBackground,
                drawTextShadow
        );
    }

    @Override
    public String getName() {
        return "Inventory Space HUD";
    }

    @Override
    public String getId() {
        return HUDId.INVENTORY_SPACE.toString();
    }
}
