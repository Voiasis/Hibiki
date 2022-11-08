package net.voiasis.events;

import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.auto.ServerLogs;

public class onMessageBulkDeleteEvent extends ListenerAdapter {
    @Override
    public void onMessageBulkDelete(MessageBulkDeleteEvent event) {
        //ServerLogs.bulkDeleted(event);
    }
}
