package com.zxl.lemonsgui.client;

import com.zxl.lemonsgui.client.gui.LemonsGuiWelcomeScreen;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import java.util.EnumSet;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

/** Opens Lemon's GUI welcome screen on the F8 rising edge. */
public final class LemonsGuiKeyHandler implements ITickHandler {
    private boolean wasDown;

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        boolean down = Keyboard.isKeyDown(Keyboard.KEY_F8);
        if (down && !wasDown) {
            Minecraft minecraft = Minecraft.getMinecraft();
            if (minecraft.currentScreen == null) {
                minecraft.displayGuiScreen(new LemonsGuiWelcomeScreen());
            }
        }
        wasDown = down;
    }

    @Override public void tickEnd(EnumSet<TickType> type, Object... tickData) { }
    @Override public EnumSet<TickType> ticks() { return EnumSet.of(TickType.CLIENT); }
    @Override public String getLabel() { return "LemonsGuiKeyHandler"; }
}
