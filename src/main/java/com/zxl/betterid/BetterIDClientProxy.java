package com.zxl.betterid;

import com.zxl.betterid.client.BetterIDKeyHandler;
import cpw.mods.fml.client.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/** Client-only initialization for the Better ID Extender GUI shortcut. */
public final class BetterIDClientProxy extends BetterIDCommonProxy {
    @Override
    public void init() {
        TickRegistry.registerTickHandler(new BetterIDKeyHandler(), Side.CLIENT);
    }
}
