package com.hyahm.utils.function;

@FunctionalInterface
public interface ArgumentStringConsumer <T>{
    T consume(String str);
}
