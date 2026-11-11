package com.zxl.betterid.registry;

import java.util.HashSet;
import java.util.Set;

/**
 * Registry-neutral ID reservation helper. This class is not wired into
 * Minecraft or Forge registration and does not guarantee save/network support.
 */
public final class IdRangeAllocator {
    private final int minimum;
    private final int maximum;
    private final Set<Integer> reserved = new HashSet<Integer>();

    public IdRangeAllocator(int minimum, int maximum) {
        if (minimum < 0 || maximum < minimum) {
            throw new IllegalArgumentException("Invalid ID range: " + minimum + ".." + maximum);
        }
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public synchronized boolean reserve(int id) {
        return id >= minimum && id <= maximum && reserved.add(Integer.valueOf(id));
    }

    public synchronized int allocateFirstFree() {
        for (int id = minimum; id <= maximum; id++) {
            if (!reserved.contains(Integer.valueOf(id))) {
                reserved.add(Integer.valueOf(id));
                return id;
            }
        }
        throw new IllegalStateException("No free IDs in range " + minimum + ".." + maximum);
    }

    public synchronized boolean isReserved(int id) {
        return reserved.contains(Integer.valueOf(id));
    }

    public synchronized int getReservedCount() {
        return reserved.size();
    }

    public int getMinimum() { return minimum; }
    public int getMaximum() { return maximum; }
}
