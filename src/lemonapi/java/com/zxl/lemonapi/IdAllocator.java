package com.zxl.lemonapi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Thread-safe, registry-neutral ID reservation utility.
 * Reservations are in-memory only and are not connected to Forge registries.
 */
public final class IdAllocator {
    private final IdRange range;
    private final Map<Integer, String> owners = new HashMap<Integer, String>();

    public IdAllocator(IdRange range) {
        if (range == null) {
            throw new IllegalArgumentException("range cannot be null");
        }
        this.range = range;
    }

    public synchronized boolean reserve(int id, String owner) {
        validateOwner(owner);
        if (!range.contains(id) || owners.containsKey(Integer.valueOf(id))) {
            return false;
        }
        owners.put(Integer.valueOf(id), owner);
        return true;
    }

    public synchronized int allocateFirstFree(String owner) {
        validateOwner(owner);
        for (int id = range.getMinimum(); id <= range.getMaximum(); id++) {
            if (!owners.containsKey(Integer.valueOf(id))) {
                owners.put(Integer.valueOf(id), owner);
                return id;
            }
        }
        throw new IllegalStateException("No free IDs in range " + range);
    }

    public synchronized boolean isReserved(int id) {
        return owners.containsKey(Integer.valueOf(id));
    }

    public synchronized String getOwner(int id) {
        return owners.get(Integer.valueOf(id));
    }

    public synchronized int getReservedCount() {
        return owners.size();
    }

    public IdRange getRange() {
        return range;
    }

    public synchronized Map<Integer, String> snapshot() {
        return Collections.unmodifiableMap(new HashMap<Integer, String>(owners));
    }

    private static void validateOwner(String owner) {
        if (owner == null || owner.trim().length() == 0) {
            throw new IllegalArgumentException("owner must not be empty");
        }
    }
}
