package com.zxl.lemonsgui.client;

import com.zxl.lemonsgui.LemonsGuiCommonProxy;
import cpw.mods.fml.client.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/** Registers the optional F8 shortcut for the Lemon's GUI welcome screen. */
public final class LemonsGuiClientProxy extends LemonsGuiCommonProxy {
    @Override
    public void init() {
        TickRegistry.registerTickHandler(new LemonsGuiKeyHandler(), Side.CLIENT);
    }
}
