package net.realvezio.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.realvezio.auto.DMcontroller;
import net.realvezio.auto.Grammar;
import net.realvezio.auto.MusicTranslator;
import net.realvezio.auto.ServerLogs;
import net.realvezio.auto.SpamFilter;
import net.realvezio.auto.Suggestions;
import net.realvezio.auto.UpdatePing;
import net.realvezio.auto.WordFilter;
import net.realvezio.commands.prefix.BotCommands;

public class onMessageReceivedEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        ServerLogs.messageCreated(event);
        WordFilter.filter(message);
        BotCommands.commands(message);
        SpamFilter.filterSpam(event);
        Suggestions.suggestions(message);
        Grammar.check(message);
        DMcontroller.controller(message);
        UpdatePing.ping(message);
        MusicTranslator.musicTranslator(message);
    }
}
