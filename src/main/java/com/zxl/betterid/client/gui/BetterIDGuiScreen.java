package com.zxl.betterid.client.gui;

import com.zxl.betterid.transformer.BetterIDTransformer;
import com.zxl.lemonapi.LemonAPI;
import com.zxl.lemonsgui.LemonGuiAPI;
import com.zxl.lemonsgui.LemonsGuiScreen;
import net.minecraft.client.gui.GuiButton;

/** First in-game dashboard for Better ID Extender, rendered by Lemon's GUI. */
public final class BetterIDGuiScreen extends LemonsGuiScreen {
    private static final int TAB_OVERVIEW = 1;
    private static final int TAB_RANGES = 2;
    private static final int TAB_DIAGNOSTICS = 3;
    private int selectedTab = TAB_OVERVIEW;

    public BetterIDGuiScreen() {
        super("Better ID Extender");
    }

    @Override
    public void initGui() {
        super.initGui();
        int center = this.width / 2;
        this.buttonList.add(new GuiButton(TAB_OVERVIEW, center - 144, 42, 92, 20, "Overview"));
        this.buttonList.add(new GuiButton(TAB_RANGES, center - 46, 42, 92, 20, "ID ranges"));
        this.buttonList.add(new GuiButton(TAB_DIAGNOSTICS, center + 52, 42, 92, 20, "Diagnostics"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == TAB_OVERVIEW || button.id == TAB_RANGES
                || button.id == TAB_DIAGNOSTICS) {
            selectedTab = button.id;
            return;
        }
        super.actionPerformed(button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        int left = this.width / 2 - 155;
        int top = 72;
        drawLemonsPanel(left, top, left + 310, this.height - 42);
        drawLemonsHeader("Minecraft 1.6.4  |  Forge 9.11.1.964");

        if (selectedTab == TAB_OVERVIEW) {
            drawLemonsText("Compatibility status", left + 14, top + 18, 0xFFB8E34B);
            drawLemonsText("Lemon API: " + LemonAPI.getVersion()
                    + " / API " + LemonAPI.getApiLevel(), left + 14, top + 39, 0xFFFFFFFF);
            drawLemonsText("Lemon's GUI: " + LemonGuiAPI.getVersion()
                    + " / API " + LemonGuiAPI.getApiLevel(), left + 14, top + 58, 0xFFFFFFFF);
            drawLemonsText("GUI framework: loaded", left + 14, top + 82, 0xFFB8E34B);
            drawLemonsText("ID registry expansion: not implemented yet", left + 14, top + 101, 0xFFFFC36B);
        } else if (selectedTab == TAB_RANGES) {
            drawLemonsText("Legacy block storage", left + 14, top + 18, 0xFFB8E34B);
            drawLemonsText("Anvil block ID storage ceiling: 4095", left + 14, top + 40, 0xFFFFFFFF);
            drawLemonsText("Allocator reservations are currently in-memory only.", left + 14, top + 60, 0xFFD6DCE5);
            drawLemonsText("No registry remapping or save migration is active.", left + 14, top + 80, 0xFFFFC36B);
        } else {
            drawLemonsText("Diagnostics", left + 14, top + 18, 0xFFB8E34B);
            drawLemonsText("Coremod transformer: " + BetterIDTransformer.class.getSimpleName(), left + 14, top + 40, 0xFFFFFFFF);
            drawLemonsText("Current transformer behavior: pass-through", left + 14, top + 60, 0xFFFFC36B);
            drawLemonsText("No live Minecraft integration test has been completed.", left + 14, top + 80, 0xFFD6DCE5);
        }
        drawLemonsText("F7 opens this screen  |  F8 opens Lemon's GUI", left + 14,
                this.height - 56, 0xFF9EACBC);
    }
}
