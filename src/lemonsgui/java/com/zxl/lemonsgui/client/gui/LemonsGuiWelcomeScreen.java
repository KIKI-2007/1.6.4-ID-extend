package com.zxl.lemonsgui.client.gui;

import com.zxl.lemonapi.LemonAPI;
import com.zxl.lemonsgui.LemonGuiAPI;
import com.zxl.lemonsgui.LemonsGuiScreen;

public final class LemonsGuiWelcomeScreen extends LemonsGuiScreen {
    public LemonsGuiWelcomeScreen() {
        super("Lemon's GUI");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        int left = this.width / 2 - 145;
        int top = 52;
        drawLemonsPanel(left, top, left + 290, top + 112);
        drawLemonsHeader("Shared interface framework for legacy Forge mods");
        drawLemonsText("Lemon's GUI API: " + LemonGuiAPI.getVersion()
                + " (level " + LemonGuiAPI.getApiLevel() + ")", left + 14, top + 20, 0xFFFFFFFF);
        drawLemonsText("Lemon API: " + LemonAPI.getVersion()
                + " (level " + LemonAPI.getApiLevel() + ")", left + 14, top + 39, 0xFFB8E34B);
        drawLemonsText("This screen confirms that the GUI library is loaded.", left + 14, top + 67, 0xFFD6DCE5);
        drawLemonsText("Press F8 to reopen this screen.", left + 14, top + 85, 0xFF9EACBC);
    }
}
