package net.voiasis.events;

import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.auto.ServerLogs;

public class onMessageDeleteEvent extends ListenerAdapter {
    @Override
    public void onMessageDelete(MessageDeleteEvent event) {
        //ServerLogs.messageDeleted(event);
    }
}
