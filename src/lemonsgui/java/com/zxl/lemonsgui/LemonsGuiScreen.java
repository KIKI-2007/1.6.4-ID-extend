package com.zxl.lemonsgui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

/** Reusable base screen with Lemon's GUI visual framing and a standard close button. */
public class LemonsGuiScreen extends GuiScreen {
    private static final int BUTTON_CLOSE = 900;
    private final String screenTitle;

    public LemonsGuiScreen(String title) {
        this.screenTitle = title == null ? LemonGuiAPI.NAME : title;
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(BUTTON_CLOSE,
                this.width / 2 - 70, this.height - 30, 140, 20, "Done"));
    }

    protected void drawLemonsPanel(int left, int top, int right, int bottom) {
        drawRect(left, top, right, bottom, 0xD9101722);
        drawRect(left, top, right, top + 2, 0xFFB8E34B);
        drawRect(left, bottom - 1, right, bottom, 0xFF354253);
        drawRect(left, top, left + 1, bottom, 0xFF354253);
        drawRect(right - 1, top, right, bottom, 0xFF354253);
    }

    protected void drawLemonsHeader(String subtitle) {
        drawCenteredString(this.fontRenderer, screenTitle, this.width / 2, 14, 0xFFE8F2D0);
        if (subtitle != null && subtitle.length() > 0) {
            drawCenteredString(this.fontRenderer, subtitle, this.width / 2, 28, 0xFFB8C5D5);
        }
    }

    protected void drawLemonsText(String text, int x, int y, int color) {
        this.fontRenderer.drawString(text, x, y, color);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == BUTTON_CLOSE) {
            this.mc.displayGuiScreen(null);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
