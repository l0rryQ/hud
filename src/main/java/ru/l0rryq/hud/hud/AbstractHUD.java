package ru.l0rryq.hud.hud;

import ru.l0rryq.hud.config.BaseHUDSettings;
import ru.l0rryq.hud.config.ConditionalSettings;
import ru.l0rryq.hud.helper.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public abstract class AbstractHUD implements HUDInterface {

    protected final BaseHUDSettings baseHUDSettings;

    private int baseX;
    private int baseY;

    private int totalXOffset;
    private int totalYOffset;

    private int startDragX;
    private int startDragY;
    private ScreenAlignmentX startDragAlignmentX;
    private ScreenAlignmentY startDragAlignmentY;
    private GrowthDirectionX startDragGrowthX;
    private GrowthDirectionY startDragGrowthY;

    protected final Box boundingBox = new Box(-1, -1, -1, -1);

    public String groupId = null;

    public AbstractHUD(BaseHUDSettings baseHUDSettings) {
        this.baseHUDSettings = baseHUDSettings;
    }

    @Override
    public boolean shouldRender() {
        return getSettings().shouldRender();
    }

    // we update every HUD's x and y points here.
    @Override
    public void update() {
        baseX = getSettings().getCalculatedPosX();
        baseY = getSettings().getCalculatedPosY();

        if (!isInGroup()) {
            updatePos();
        }
    }

    @Override
    public boolean render(GuiGraphicsExtractor context) {
        if (!isScaled())
            return renderHUD(context, getX(), getY(), shouldDrawBackground(), shouldDrawTextShadow());

        // this is so we can change the scale for one hud but not the others.
        context.pose().pushMatrix();
        scaleHUD(context);

        boolean result = renderHUD(context, getX(), getY(), shouldDrawBackground(), shouldDrawTextShadow());
        context.pose().popMatrix();

        return result;
    }

    @Override
    public boolean collect() {
        if (!collectHUDInformation())
            return false;

        modifyXY();

        updatePos();
        return true;
    }

    // collect what is needed for the hud to render.
    // the true purpose of collectHUDInformation is to collect the width and height during data collection,
    // this is to ensure that the width and height can be used before the rendering
    // returns false if the HUD cannot be rendered
    // returns true if the HUD is ready to be rendered.
    public abstract boolean collectHUDInformation();

    // this is where the hud is rendered. Where we put the rendering logic.
    // it is highly discouraged to put information collecting in this function.
    // for information collecting please refer to collectHUDInformation()
    public abstract boolean renderHUD(GuiGraphicsExtractor context, int x, int y, boolean drawBackground, boolean drawTextShadow);

    public abstract String getName();

    public void scaleHUD(GuiGraphicsExtractor context) {
        float scaleFactor = getScale();
        context.pose().translate(getX(), getY());
        context.pose().scale(scaleFactor, scaleFactor);
        context.pose().translate(-getX(), -getY());
    }

    public void updatePos() {
        setScale(getSettings().getScale());
        setXY(baseX + totalXOffset - getGrowthDirectionHorizontal((int) (getWidth() * getScale())), baseY + totalYOffset - getGrowthDirectionVertical((int) (getHeight() * getScale())));
    }

    public void modifyXY() {
        int xOffset = 0, yOffset = 0;

        float guiScale = Minecraft.getInstance().getWindow().getGuiScale();
        for (ConditionalSettings condition : baseHUDSettings.getConditions()) {
            if (condition.renderMode != ConditionalSettings.RenderMode.HIDE && condition.isConditionMet()) {
                xOffset += condition.getXOffset(guiScale);
                yOffset += condition.getYOffset(guiScale);
            }
        }

        totalXOffset = xOffset;
        totalYOffset = yOffset;
    }

    public boolean isScaled() {
        return true;
    }

    public boolean isInGroup() {
        return groupId != null;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public BaseHUDSettings getSettings() {
        return baseHUDSettings;
    }

    public int getGrowthDirectionHorizontal(int dynamicWidth) {
        return getSettings().getGrowthDirectionHorizontal(dynamicWidth);
    }

    public int getGrowthDirectionVertical(int dynamicHeight) {
        return getSettings().getGrowthDirectionVertical(dynamicHeight);
    }

    public boolean shouldDrawBackground() {
        return getSettings().drawBackground;
    }

    public boolean shouldDrawTextShadow() {
        return getSettings().drawTextShadow;
    }

    // bounding box attribute will return 0 if HUD is not rendered once.
    // the HUD must be rendered at least once to update the bounding box.

    public void setWidthHeight(int width, int height) {
        this.boundingBox.setWidthHeight(width, height);
    }

    public void setWidthHeightColor(int width, int height, int color) {
        this.boundingBox.setWidthHeightColor(width, height, color);
    }

    public void setXY(int x, int y) {
        this.boundingBox.setX(x);
        this.boundingBox.setY(y);
    }

    public void setScale(float scale) {
        this.boundingBox.setScale(scale);
    }

    public int getX() {
        return getBoundingBox().getX();
    }

    public int getY() {
        return getBoundingBox().getY();
    }

    public int getWidth() {
        return getBoundingBox().getWidth();
    }

    public int getHeight() {
        return getBoundingBox().getHeight();
    }

    public int getTrueWidth() {
        return (int) (getWidth() * getScale());
    }

    public int getTrueHeight() {
        return (int) (getHeight() * getScale());
    }

    public int getColor() {
        return getBoundingBox().getColor();
    }

    public float getScale() {
        return getBoundingBox().getScale();
    }

    public Box getBoundingBox() {
        return boundingBox;
    }

    public void copyBoundingBox(Box boundingBox) {
        if (boundingBox != null)
            this.boundingBox.copyFrom(boundingBox);
    }

    public void setBoundingBox(int x, int y, int width, int height, int color) {
        this.boundingBox.setBoundingBox(x, y, width, height, color);
    }

    public void setBoundingBox(int x, int y, int width, int height) {
        this.boundingBox.setBoundingBox(x, y, width, height);
    }

    public void setStartDragX(int startDragX) {
        this.startDragX = startDragX;
    }

    public void setStartDragY(int startDragY) {
        this.startDragY = startDragY;
    }

    public void setStartDragAlignmentX(ScreenAlignmentX startDragAlignmentX) {
        this.startDragAlignmentX = startDragAlignmentX;
    }

    public void setStartDragAlignmentY(ScreenAlignmentY startDragAlignmentY) {
        this.startDragAlignmentY = startDragAlignmentY;
    }

    public void setStartDragGrowthX(GrowthDirectionX startDragGrowthX) {
        this.startDragGrowthX = startDragGrowthX;
    }

    public void setStartDragGrowthY(GrowthDirectionY startDragGrowthY) {
        this.startDragGrowthY = startDragGrowthY;
    }

    public int getStartDragX() {
        return this.startDragX;
    }

    public int getStartDragY() {
        return this.startDragY;
    }

    public ScreenAlignmentX getStartDragAlignmentX() {
        return startDragAlignmentX;
    }

    public ScreenAlignmentY getStartDragAlignmentY() {
        return startDragAlignmentY;
    }

    public GrowthDirectionX getStartDragGrowthX() {
        return startDragGrowthX;
    }

    public GrowthDirectionY getStartDragGrowthY() {
        return startDragGrowthY;
    }

    public void setupStartDrag() {
        setStartDragX(getSettings().getX());
        setStartDragY(getSettings().getY());
        setStartDragAlignmentX(getSettings().getOriginX());
        setStartDragAlignmentY(getSettings().getOriginY());
        setStartDragGrowthX(getSettings().getGrowthDirectionX());
        setStartDragGrowthY(getSettings().getGrowthDirectionY());
    }

    public boolean isHovered(double mouseX, double mouseY) {
        final float scale = Minecraft.getInstance().getWindow().getGuiScale();

        mouseX = (int) (mouseX * scale);
        mouseY = (int) (mouseY * scale);

        int x = getX();
        int y = getY();
        int width = getTrueWidth();
        int height = getTrueHeight();

        return (mouseX >= x && mouseX <= (x + width))
                && (mouseY >= y && mouseY <= (y + height));

    }

    public boolean intersects(int x1, int y1, int x2, int y2) {
        final float scale = Minecraft.getInstance().getWindow().getGuiScale();

        x1 = (int) (x1 * scale);
        y1 = (int) (y1 * scale);
        x2 = (int) (x2 * scale);
        y2 = (int) (y2 * scale);

        int hudLeft   = getX();
        int hudTop    = getY();
        int hudRight  = getX() + getTrueWidth();
        int hudBottom = getY() + getTrueHeight();

        return hudRight >= Math.min(x1, x2) &&
                hudLeft  <= Math.max(x1, x2) &&
                hudBottom >= Math.min(y1, y2) &&
                hudTop    <= Math.max(y1, y2);
    }

    // dont go out of bounds please
    public boolean clampPos() {
        int windowWidth = Minecraft.getInstance().getWindow().getWidth();
        int windowHeight = Minecraft.getInstance().getWindow().getHeight();

        int x1 = 0;
        int y1 = 0;
        int x2 = windowWidth;
        int y2 = windowHeight;

        int hudLeft   = getX();
        int hudTop    = getY();
        int hudRight  = hudLeft + getTrueWidth();
        int hudBottom = hudTop + getTrueHeight();

        int xOffset = 0, yOffset = 0;

        if (hudLeft < x1) {
            xOffset = x1 - hudLeft;
        } else if (hudRight > x2) {
            xOffset = x2 - hudRight;
        }

        if (hudTop < y1) {
            yOffset = y1 - hudTop;
        } else if (hudBottom > y2) {
            yOffset = y2 - hudBottom;
        }

        if (xOffset != 0 || yOffset != 0) {
            getSettings().x += xOffset;
            getSettings().y += yOffset;

            baseX = getSettings().getCalculatedPosX();
            baseY = getSettings().getCalculatedPosY();
            updatePos();

            return true;
        }

        return false;
    }

    public String getFormattedText(String defaultValue) {
        String template = getSettings().textTemplate;
        if (template == null || template.isEmpty()) {
            return defaultValue;
        }

        String result = template;

        if (result.contains("%fps%")) {
            result = result.replace("%fps%", String.valueOf(Minecraft.getInstance().getFps()));
        }
        if (result.contains("%tps%")) {
            result = result.replace("%tps%", String.format(java.util.Locale.ROOT, "%.1f", ru.l0rryq.hud.helper.TPSTracker.getTPS()));
        }
        if (result.contains("%ping%")) {
            int ping = 0;
            if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.connection.getPlayerInfo(Minecraft.getInstance().player.getUUID()) != null) {
                ping = Minecraft.getInstance().player.connection.getPlayerInfo(Minecraft.getInstance().player.getUUID()).getLatency();
            }
            result = result.replace("%ping%", String.valueOf(ping));
        }
        if (result.contains("%biome%")) {
            String biomeName = "Unknown";
            if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
                net.minecraft.core.Holder<net.minecraft.world.level.biome.Biome> biome = Minecraft.getInstance().level.getBiome(Minecraft.getInstance().player.getOnPos());
                biomeName = biome.unwrapKey().map(k -> k.identifier().getPath()).orElse("Unknown");
                biomeName = ru.l0rryq.hud.Helper.idNameFormatter(biomeName);
            }
            result = result.replace("%biome%", biomeName);
        }
        if (result.contains("%day%")) {
            long day = 0;
            if (Minecraft.getInstance().level != null) {
                day = Minecraft.getInstance().level.getDefaultClockTime() / 24000L;
            }
            result = result.replace("%day%", String.valueOf(day));
        }
        if (result.contains("%ip%") || result.contains("%server%")) {
            String server = Minecraft.getInstance().isLocalServer() ? "Singleplayer" : (Minecraft.getInstance().getCurrentServer() != null ? Minecraft.getInstance().getCurrentServer().ip : "Unknown");
            result = result.replace("%ip%", server).replace("%server%", server);
        }
        if (result.contains("%player_count%")) {
            int count = 1;
            if (!Minecraft.getInstance().isLocalServer() && Minecraft.getInstance().player != null) {
                count = Minecraft.getInstance().player.connection.getListedOnlinePlayers().size();
            } else if (Minecraft.getInstance().getSingleplayerServer() != null) {
                count = Minecraft.getInstance().getSingleplayerServer().getPlayerCount();
            }
            result = result.replace("%player_count%", String.valueOf(count));
        }
        if (result.contains("%nether_x%")) {
            int nx = Minecraft.getInstance().player != null ? (int) (Minecraft.getInstance().player.getX() / 8) : 0;
            result = result.replace("%nether_x%", String.valueOf(nx));
        }
        if (result.contains("%nether_y%")) {
            int ny = Minecraft.getInstance().player != null ? (int) Minecraft.getInstance().player.getY() : 0;
            result = result.replace("%nether_y%", String.valueOf(ny));
        }
        if (result.contains("%nether_z%")) {
            int nz = Minecraft.getInstance().player != null ? (int) (Minecraft.getInstance().player.getZ() / 8) : 0;
            result = result.replace("%nether_z%", String.valueOf(nz));
        }
        if (result.contains("%x%")) {
            int x = Minecraft.getInstance().player != null ? (int) Minecraft.getInstance().player.getX() : 0;
            result = result.replace("%x%", String.valueOf(x));
        }
        if (result.contains("%y%")) {
            int y = Minecraft.getInstance().player != null ? (int) Minecraft.getInstance().player.getY() : 0;
            result = result.replace("%y%", String.valueOf(y));
        }
        if (result.contains("%z%")) {
            int z = Minecraft.getInstance().player != null ? (int) Minecraft.getInstance().player.getZ() : 0;
            result = result.replace("%z%", String.valueOf(z));
        }

        return result;
    }
}
