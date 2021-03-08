package com.hyahm;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.HashMap;
import java.util.Map;

public class TickEventScheduler {
    private static class Task {
        int tick;
        Runnable r;
    }

    private final Map<Integer, Task> tasks = new HashMap<>();
    int id = 0;

    public int enqueueEvent(int ticks, Runnable r) {
        HyahmMain.logger.info("enqueued event");
        Task t = new Task();
        t.r = r;
        t.tick = ticks;
        tasks.put(id, t);
        return id++;
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onClientTickEvent(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            for (Map.Entry<Integer, Task> a: tasks.entrySet()) {
                Task t= a.getValue();
                t.tick--;
                if(t.tick == 0) {
                    t.r.run();
                    tasks.remove(a.getKey());
                }
            }
        }
    }
}