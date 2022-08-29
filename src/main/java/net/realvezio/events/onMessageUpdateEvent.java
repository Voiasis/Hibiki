package net.realvezio.events;

import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.realvezio.auto.Grammar;
import net.realvezio.auto.ServerLogs;
import net.realvezio.auto.WordFilter;
import net.realvezio.commands.prefix.EditCommands;

public class onMessageUpdateEvent extends ListenerAdapter {
    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        ServerLogs.messageEdited(event);
        WordFilter.filter(event.getMessage());
        EditCommands.commands(event.getMessage());
        Grammar.check(event.getMessage());
    }
}
