package net.voiasis.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.auto.DMcontroller;
import net.voiasis.auto.Grammar;
import net.voiasis.auto.MusicTranslator;
import net.voiasis.auto.ServerLogs;
import net.voiasis.auto.SpamFilter;
import net.voiasis.auto.Suggestions;
import net.voiasis.auto.UpdatePing;
import net.voiasis.auto.WordFilter;
import net.voiasis.commands.prefix.BotCommands;

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
