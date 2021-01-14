package com.hyahm;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.HashSet;

public class TickEventScheduler {
    private static class Task {
        int tick;
        Runnable r;
    }

    private HashSet<Task> tasks;

    TickEventScheduler() {
        tasks = new HashSet<>();
    }

    public void enqueueEvent(int ticks, Runnable r) {
        HyahmMain.logger.info("enqueued event");
        Task t = new Task();
        t.r = r;
        t.tick = ticks;
        tasks.add(t);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onClientTickEvent(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            for (Task t: tasks) {
                t.tick--;
                if(t.tick == 0) {
                    t.r.run();
                    tasks.remove(t);
                }
            }
        }
    }
}