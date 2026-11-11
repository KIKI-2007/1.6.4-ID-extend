package com.zxl.betterid;

import com.zxl.lemonapi.LemonAPI;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Regular FML mod entry point. Both Lemon libraries are required dependencies.
 * Client GUI initialization is isolated behind the sided proxy.
 */
@Mod(modid = "betteridextender", name = "Better ID Extender",
     version = "0.3.0",
     dependencies = "required-after:lemonapi@[0.1.0,);required-after:lemonsgui@[0.1.0,)",
     acceptedMinecraftVersions = "[1.6.4]")
public final class BetterIDMod {
    @SidedProxy(clientSide = "com.zxl.betterid.BetterIDClientProxy",
                serverSide = "com.zxl.betterid.BetterIDCommonProxy")
    public static BetterIDCommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (!LemonAPI.isCompatible(1, 1)) {
            throw new IllegalStateException("Better ID Extender requires Lemon API level 1.");
        }
        if (!"1.6.4".equals(LemonAPI.getMinecraftVersion())) {
            throw new IllegalStateException("Lemon API targets Minecraft "
                    + LemonAPI.getMinecraftVersion() + ", expected 1.6.4.");
        }
        proxy.init();
        System.out.println("[BetterIDExtender] Lemon API "
                + LemonAPI.getVersion() + " (API level "
                + LemonAPI.getApiLevel() + ") detected.");
        System.out.println("[BetterIDExtender] Lemon's GUI is a required dependency.");
    }
}
