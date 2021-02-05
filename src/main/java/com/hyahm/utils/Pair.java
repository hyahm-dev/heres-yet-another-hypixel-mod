package com.hyahm.utils;

import org.jetbrains.annotations.NotNull;

public class Pair <K, V> {
    private K key;
    private V val;

    public Pair(K k, V v) {
        this.key = k;
        this.val = v;
    }

    public Pair() {
        this.key = null;
        this.val = null;
    }

    public K getKey() {
        return key;
    }

    public V getVal() {
        return val;
    }

    public void setKey(@NotNull K k ) {
        this.key = k;
    }

    public void setVal(@NotNull V v) {
        this.val = v;
    }

}
