package com.zxl.lemonapi;

import cpw.mods.fml.common.Mod;

/** Forge 1.6.4 mod entry point for the Lemon API prerequisite. */
@Mod(modid = LemonAPI.MOD_ID, name = LemonAPI.NAME, version = LemonAPI.VERSION,
     acceptedMinecraftVersions = "[1.6.4]")
public final class LemonApiMod {
    // The public API is stateless; this class exists to let FML discover the prerequisite.
}
