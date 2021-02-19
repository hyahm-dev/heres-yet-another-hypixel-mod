package com.hyahm.mixins;

import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.ClientCommandHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientCommandHandler.class)
public class ClientCommandHandlerMixin {
    @Inject(remap = false, method = "executeCommand(Lnet/minecraft/command/ICommandSender;Ljava/lang/String;)I", at = @At("HEAD"), cancellable = true)
    private void executeCommand(ICommandSender sender, String message, CallbackInfoReturnable<Integer> cir) {
        if(!message.startsWith("/"))
            cir.setReturnValue(0);
    }
}
