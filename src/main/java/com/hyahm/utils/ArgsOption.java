package com.hyahm.utils;

import java.util.function.Consumer;
import java.util.function.Function;

public class ArgsOption<T> {
    Consumer<T> consumer;
    Function<String, T> converter;

    public ArgsOption(Consumer<T> consumer, Function<String, T> converter) {
        this.consumer = consumer;
        this.converter = converter;
    }

    public void tryExec(String str) {
        consumer.accept(converter.apply(str));
    }
}
