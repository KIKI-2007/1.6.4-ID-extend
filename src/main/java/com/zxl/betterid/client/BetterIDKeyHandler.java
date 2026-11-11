package com.zxl.betterid.client;

import com.zxl.betterid.client.gui.BetterIDGuiScreen;
import com.zxl.lemonsgui.LemonGuiAPI;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import java.util.EnumSet;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

/** Opens the Better ID Extender management screen on the F7 rising edge. */
public final class BetterIDKeyHandler implements ITickHandler {
    private boolean wasDown;

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        boolean down = Keyboard.isKeyDown(Keyboard.KEY_F7);
        if (down && !wasDown) {
            Minecraft minecraft = Minecraft.getMinecraft();
            if (minecraft.currentScreen == null) {
                LemonGuiAPI.openScreen(minecraft, new BetterIDGuiScreen());
            }
        }
        wasDown = down;
    }

    @Override public void tickEnd(EnumSet<TickType> type, Object... tickData) { }
    @Override public EnumSet<TickType> ticks() { return EnumSet.of(TickType.CLIENT); }
    @Override public String getLabel() { return "BetterIDKeyHandler"; }
}
