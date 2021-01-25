package com.hyahm.mixins;
import net.minecraft.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import java.lang.String;

@Mixin(CrashReport.class)
public class CustomCrash
{
    @Inject(method = "getWittyComment()Ljava/lang/String;", at = @At("HEAD"))
    public String getWittyComment() {
        return "Example witty comment";
    }

}