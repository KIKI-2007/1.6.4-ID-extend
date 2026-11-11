package com.zxl.betterid;

import com.zxl.lemonapi.LemonAPI;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Regular FML mod entry point. The coremod bootstrap remains independent so
 * LaunchWrapper can discover it before normal mod dependency resolution.
 */
@Mod(modid = "betteridextender", name = "Better ID Extender",
     version = "0.2.0", dependencies = "required-after:lemonapi@[0.1.0,)",
     acceptedMinecraftVersions = "[1.6.4]")
public final class BetterIDMod {
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (!LemonAPI.isCompatible(1, 1)) {
            throw new IllegalStateException("Better ID Extender requires Lemon API level 1.");
        }
        if (!"1.6.4".equals(LemonAPI.getMinecraftVersion())) {
            throw new IllegalStateException("Lemon API targets Minecraft "
                    + LemonAPI.getMinecraftVersion() + ", expected 1.6.4.");
        }
        System.out.println("[BetterIDExtender] Lemon API "
                + LemonAPI.getVersion() + " (API level "
                + LemonAPI.getApiLevel() + ") detected.");
    }
}
