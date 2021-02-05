package com.hyahm.hooks;

import com.hyahm.Constants;
import com.hyahm.HyahmMain;
import javafx.util.Pair;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class HookHandler {
    List<Pair<Object, List<Method>>> hooks;

    public HookHandler() {
        hooks = new ArrayList<>();
    }

    public void hook(Object r) {
        List<Method> al = new ArrayList<>();
        for (Method m: r.getClass().getDeclaredMethods()) {
            if(!m.isAnnotationPresent(Hook.class))
                throw new IllegalArgumentException("unable to die");
            if(m.getParameterTypes().length != 1)
                throw new IllegalArgumentException("bruh");
            if(!m.getParameterTypes()[0].isAnnotationPresent(Event.class))
                throw new IllegalArgumentException("a");

            al.add(m);
        }

        hooks.add(new Pair<>(r, al));
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onChatEvent(ClientChatReceivedEvent event)
    {
        if(!HyahmMain.config.autoGGConfig.isEnabled)
            return;

        // verify its both a server and it is hypixel
        if(!Constants.isHypixel())
            return;

        // delete orphans because we all are technoblade
        String msg = (Pattern.compile("(?i)" + '\u00A7' + "[0-9A-FK-OR]"))
                .matcher(event.message.getUnformattedText())
                .replaceAll("");

        // anarchy
        if(Constants.AutoGGMatch.stream().noneMatch(msg::contains))
            return;

        for (Pattern expr : Constants.MatchNormal) {
            if(expr.matcher(msg).matches())
                return;
        }

        for (Pair<Object, List<Method>> handler: hooks) {
            for (Method m : handler.getValue()) {
                if (m.getParameterTypes()[0] == GameEndEvent.class) {
                    try {
                        m.invoke(handler.getKey(), new GameEndEvent());
                    }
                    catch (Exception e) {
                        HyahmMain.logger.error("Failed to invoke target", e);
                    }
                }
            }
        }
    }
}
