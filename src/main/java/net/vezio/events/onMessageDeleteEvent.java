package net.vezio.events;

import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.vezio.auto.ServerLogs;

public class onMessageDeleteEvent extends ListenerAdapter {
    @Override
    public void onMessageDelete(MessageDeleteEvent event) {
        //ServerLogs.messageDeleted(event);
    }
}
