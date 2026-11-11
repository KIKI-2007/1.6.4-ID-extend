package com.zxl.lemonsgui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 * Public client-side entry point for mods that build screens with Lemon's GUI.
 * Keep calls to this class inside client-only code.
 */
public final class LemonGuiAPI {
    public static final String MOD_ID = "lemonsgui";
    public static final String NAME = "Lemon's GUI";
    public static final String VERSION = "0.1.0";
    public static final int API_LEVEL = 1;

    private LemonGuiAPI() { }

    public static String getVersion() { return VERSION; }
    public static int getApiLevel() { return API_LEVEL; }

    public static boolean isCompatible(int minimum, int maximum) {
        return minimum <= API_LEVEL && API_LEVEL <= maximum;
    }

    public static void openScreen(Minecraft minecraft, GuiScreen screen) {
        if (minecraft == null) {
            throw new IllegalArgumentException("minecraft cannot be null");
        }
        if (screen == null) {
            throw new IllegalArgumentException("screen cannot be null");
        }
        minecraft.displayGuiScreen(screen);
    }
}
