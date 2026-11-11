package com.zxl.lemonapi;

/**
 * Stable entry point for mods that depend on Lemon API.
 *
 * This API provides compatibility metadata and registry-neutral ID allocation
 * primitives. It does not patch Minecraft registries or change world formats.
 */
public final class LemonAPI {
    public static final String MOD_ID = "lemonapi";
    public static final String NAME = "Lemon API";
    public static final String VERSION = "0.1.0";
    public static final String MINECRAFT_VERSION = "1.6.4";
    public static final int API_LEVEL = 1;

    private LemonAPI() { }

    public static String getVersion() {
        return VERSION;
    }

    public static String getMinecraftVersion() {
        return MINECRAFT_VERSION;
    }

    public static int getApiLevel() {
        return API_LEVEL;
    }

    public static boolean isCompatible(int minimumApiLevel, int maximumApiLevel) {
        return minimumApiLevel <= API_LEVEL && API_LEVEL <= maximumApiLevel;
    }

    public static IdAllocator createIdAllocator(int minimum, int maximum) {
        return new IdAllocator(new IdRange(minimum, maximum));
    }
}
