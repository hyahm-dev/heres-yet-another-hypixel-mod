package com.hyahm.mixins;

import com.hyahm.HyahmMain;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Scoreboard.class)
public class ScoreboardMixin {
    @Inject(method = "removeTeam(Lnet/minecraft/scoreboard/ScorePlayerTeam;)V", at = @At("HEAD"), cancellable = true)
    private void removeTeam(ScorePlayerTeam p_96511_1_, CallbackInfo ci) {
        if(p_96511_1_ == null)
            ci.cancel();
    }
}
