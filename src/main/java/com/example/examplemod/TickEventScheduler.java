package com.example.examplemod;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;

public class TickEventScheduler {
    private static class Task {
        int tick;
        Runnable r;
    }
    private static class RepeatingTask extends Task {
        int delayBetween;
    }
    private static class DelayedTask extends Task {

    }
    private int current_tick;
    private HashMap<Integer, Task> tasks;

    TickEventScheduler() {
        current_tick = 0;
        tasks = new HashMap<>();
    }

    int scheduleSyncDelayedTask(Runnable r, int delay) {
        DelayedTask t = new DelayedTask();
        t.tick = delay+current_tick;
        t.r = r;

        int id = 0;
        while (tasks.containsKey(id)) id++;

        tasks.put(id, t);
        return id;
    }
    int scheduleSyncRepeatingTask(Runnable r, int initial_delay, int delay_between) {
        RepeatingTask t = new RepeatingTask();
        t.tick = initial_delay+current_tick;
        t.r = r;
        t.delayBetween = delay_between;

        int id = 0;
        while (tasks.containsKey(id)) id++;

        tasks.put(id, t);
        return id;
    }
    boolean cancelTask(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            return true;
        }
        return false;
    }
    @SubscribeEvent
    public void onClientTickEvent(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            int tick_save = current_tick;
            current_tick++;
            for (int i : tasks.keySet()) {
                Task t = tasks.get(i);
                if (t.tick == tick_save) {
                    if (t instanceof DelayedTask) {
                        t.r.run();
                        tasks.remove(i);
                    } else if (t instanceof RepeatingTask) {
                        t.r.run();
                        t.tick = t.tick+((RepeatingTask) t).delayBetween;
                    } else {
                        throw new IllegalStateException("Not possible");
                    }
                }
            }
        }
    }

}
