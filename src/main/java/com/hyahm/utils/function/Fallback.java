package com.hyahm.utils.function;

import net.minecraft.command.ICommandSender;

@FunctionalInterface
public interface Fallback {
    void fallback(ICommandSender sender);
}
