package net.voiasis.events;

import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.auto.Grammar;
import net.voiasis.auto.ServerLogs;
import net.voiasis.auto.WordFilter;
import net.voiasis.commands.prefix.EditCommands;

public class onMessageUpdateEvent extends ListenerAdapter {
    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        ServerLogs.messageEdited(event);
        WordFilter.filter(event.getMessage());
        EditCommands.commands(event.getMessage());
        Grammar.check(event.getMessage());
    }
}
