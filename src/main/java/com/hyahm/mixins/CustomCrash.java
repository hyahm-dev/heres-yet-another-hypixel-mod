package com.hyahm.mixins;
import net.minecraft.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrashReport.class)
public class CustomCrash
{
    @Inject(method = "getWittyComment()Ljava/lang/String;", at = @At("HEAD"), cancellable = true)
    private static void getWittyComment(CallbackInfoReturnable<String> cir) {
       cir.setReturnValue("Why does this always happen?");
    }

}