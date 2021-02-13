package com.hyahm.hooks;

@Event
public class GameEndEvent {
    private boolean isCancel = false;
    private String str = "";

    GameEndEvent(String chat) {
        str = chat;
    }

    public void cancel() {
        isCancel = true;
    }

    public boolean isCancelled() {
        return isCancel;
    }

    public String getChatMessage() {
        return str;
    }
}
