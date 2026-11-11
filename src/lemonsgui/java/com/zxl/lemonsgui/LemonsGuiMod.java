package com.zxl.lemonsgui;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

/** Forge mod entry point for the separately distributable Lemon's GUI library. */
@Mod(modid = LemonGuiAPI.MOD_ID, name = LemonGuiAPI.NAME, version = LemonGuiAPI.VERSION,
     dependencies = "required-after:lemonapi@[0.1.0,)",
     acceptedMinecraftVersions = "[1.6.4]")
public final class LemonsGuiMod {
    @SidedProxy(clientSide = "com.zxl.lemonsgui.client.LemonsGuiClientProxy",
                serverSide = "com.zxl.lemonsgui.LemonsGuiCommonProxy")
    public static LemonsGuiCommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        System.out.println("[Lemon's GUI] " + LemonGuiAPI.VERSION
                + " loaded; Lemon API " + com.zxl.lemonapi.LemonAPI.getVersion()
                + " detected.");
    }
}
