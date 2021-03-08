package com.hyahm.utils;

import com.hyahm.utils.function.ArgumentOptionConsumer;
import com.hyahm.utils.function.ArgumentStringConsumer;
import net.minecraft.command.ICommandSender;

public class ArgsOption<T> {
    ArgumentOptionConsumer<T> consumer;
    ArgumentStringConsumer<T> converter;

    public ArgsOption(ArgumentOptionConsumer<T> consumer, ArgumentStringConsumer<T> converter) {
        this.consumer = consumer;
        this.converter = converter;
    }

    public boolean tryExec(ICommandSender sender, String str) {
        return consumer.consume(sender, converter.consume(str));
    }
}
