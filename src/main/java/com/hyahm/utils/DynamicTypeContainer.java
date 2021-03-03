package com.hyahm.utils;

// just here to chill
public class DynamicTypeContainer {
    private Object container;

    public <T> T get(Class<T> c) {
        return c.cast(container);
    }

    public <T> void set(Object o) {
        container = o;
    }
}
