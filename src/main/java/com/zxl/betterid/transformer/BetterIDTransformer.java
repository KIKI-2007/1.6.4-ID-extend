package com.zxl.betterid.transformer;

import net.minecraft.launchwrapper.IClassTransformer;

/**
 * Diagnostic-only placeholder. Returning the original bytecode is deliberate
 * until target classes and patch invariants are verified against Forge 1.6.4.
 */
public class BetterIDTransformer implements IClassTransformer {
    private static boolean logged;

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (!logged) {
            logged = true;
            System.out.println("[BetterIDExtender] Coremod loaded; diagnostic-only transformer active.");
            System.out.println("[BetterIDExtender] No registry or save-format patches are applied.");
        }
        return basicClass;
    }
}
