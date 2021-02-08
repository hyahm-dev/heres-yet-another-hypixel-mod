package com.hyahm.hooks;

import com.hyahm.Constants;
import com.hyahm.HyahmMain;
import com.hyahm.utils.Pair;
import com.hyahm.utils.ValueComparePair;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Pattern;

public class HookHandler {
    List<Pair<Object, Queue<ValueComparePair<Method, Integer>>>> hooks;

    public HookHandler() {
        hooks = new ArrayList<>();
    }

    public void hook(Object r) {
        Queue<ValueComparePair<Method, Integer>> al = new PriorityQueue<>();
        for (Method m: r.getClass().getDeclaredMethods()) {
            if(!m.isAnnotationPresent(Hook.class))
                continue;
            if(m.getParameterTypes().length != 1)
                throw new IllegalArgumentException("bruh");
            if(!m.getParameterTypes()[0].isAnnotationPresent(Event.class))
                throw new IllegalArgumentException("a");

            al.add(new ValueComparePair<>(m, m.getAnnotation(Hook.class).level()));
        }

        hooks.add(new Pair<>(r, al));
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onChatEvent(ClientChatReceivedEvent event)
    {
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

        for (Pair<Object, Queue<ValueComparePair<Method, Integer>>> handler: hooks) {
            for (Pair<Method, Integer> m : handler.getVal()) {
                if (m.getKey().getParameterTypes()[0] == GameEndEvent.class) {
                    try {
                        m.getKey().invoke(handler.getKey(), new GameEndEvent());
                    }
                    catch (Exception e) {
                        HyahmMain.logger.error("Failed to invoke target", e);
                    }
                }
            }
        }
    }
}
