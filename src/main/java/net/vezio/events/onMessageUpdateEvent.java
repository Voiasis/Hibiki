package net.vezio.events;

import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.vezio.auto.Grammar;
import net.vezio.auto.ServerLogs;
import net.vezio.commands.prefix.EditCommands;

public class onMessageUpdateEvent extends ListenerAdapter {
    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        //ServerLogs.messageEdited(event);
        EditCommands.commands(event.getMessage());
        Grammar.check(event.getMessage());
    }
}
