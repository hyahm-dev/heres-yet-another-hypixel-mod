package com.hyahm.utils;

import org.jetbrains.annotations.NotNull;

public class ValueComparePair<K, V extends Comparable<V>> extends Pair<K, V> implements Comparable<ValueComparePair<K, V>> {
    public ValueComparePair() {
        super();
    }
    public ValueComparePair(K key, V val) {
        super(key, val);
    }

    @Override
    public int compareTo(@NotNull ValueComparePair<K, V> o) {
        return this.getVal().compareTo(o.getVal());
    }
}
