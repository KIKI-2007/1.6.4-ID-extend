package com.zxl.lemonapi;

/** Immutable inclusive integer range used by Lemon API allocation helpers. */
public final class IdRange {
    private final int minimum;
    private final int maximum;

    public IdRange(int minimum, int maximum) {
        if (minimum < 0 || maximum < minimum) {
            throw new IllegalArgumentException("Invalid ID range: " + minimum + ".." + maximum);
        }
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getMinimum() { return minimum; }
    public int getMaximum() { return maximum; }
    public int size() { return maximum - minimum + 1; }

    public boolean contains(int id) {
        return id >= minimum && id <= maximum;
    }

    @Override
    public String toString() {
        return minimum + ".." + maximum;
    }
}
