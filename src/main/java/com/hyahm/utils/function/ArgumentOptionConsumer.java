package com.hyahm.utils.function;

import net.minecraft.command.ICommandSender;

@FunctionalInterface
public interface ArgumentOptionConsumer <T>{
    Boolean consume(ICommandSender sender, T data);
}
