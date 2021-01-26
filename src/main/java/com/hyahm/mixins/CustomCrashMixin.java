package com.hyahm.mixins;

import com.hyahm.Constants;
import com.hyahm.HyahmMain;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mixin(value = CrashReport.class, priority = 69420)
public abstract class CustomCrashMixin {
    @Shadow @Final private String description;
    @Shadow @Final Throwable cause;
    @Shadow @Final private List<CrashReportCategory> crashReportSections;
    @Shadow
    public void getSectionsInStringBuilder(StringBuilder builder) {
        throw new IllegalStateException("Mixin failed to shadow getSectionsInStringBuilder()");
    }

    @Shadow private static String getWittyComment() {
        throw new IllegalStateException("Mixin failed to shadow getSectionsInStringBuilder()");
    }

    @Inject(method = "getCompleteReport()Ljava/lang/String;", at = @At("HEAD"), cancellable = true)
    public void getCompleteReport(CallbackInfoReturnable<String> cir)
    {
        StringWriter writer = new StringWriter();
        cause.printStackTrace(new PrintWriter(writer));
        String stacktrace = writer.toString();

        String wittyComment = this.getWittyComment();
        boolean isHyahm = false;

        if(stacktrace.contains("hyahm")) {
            String[] buf = new String[] {"I swear it worked on my computer!", "Blame joshiepillow!"};
            try {
                wittyComment = buf[(int)(System.nanoTime() % (long)buf.length)];
            } catch (Throwable t) {}
            if(description.equals("You asked for it!"))
                wittyComment = "Your own fault";
            isHyahm = true;
        }


        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("---- Minecraft Crash Report ----\n")
                .append("Modified by HYAHM\n")
                .append("// ").append(wittyComment)
                .append("\n\n")
                .append("Time: ").append(new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss z").format(new Date())).append("\n")
                .append("Description: ").append(description)
                .append("\n\n")
                .append(stacktrace)
                .append("\n\nA detailed walkthrough of the error, its code path and all known details is as follows:\n");

        for (int i = 0; i < 87; i++) {
            stringBuilder.append("-");
        }

        stringBuilder.append("\n\n");
        this.getSectionsInStringBuilder(stringBuilder);

        if(isHyahm) {
            final RequestConfig rcf = RequestConfig.custom()
                    .setConnectionRequestTimeout(5000)
                    .setSocketTimeout(5000)
                    .build();

            HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(rcf).build();

            try {
                HttpPost request = new HttpPost("http://localhost:1000");
                StringEntity params = new StringEntity(stringBuilder.toString());
                request.addHeader("content-type", "plain/text");
                request.setEntity(params);
                httpClient.execute(request);
            } catch (Exception ex) {
                // handle exception here
            } finally {

            }
        }

        cir.setReturnValue(stringBuilder.toString());
    }
}
