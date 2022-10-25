package net.vezio.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.vezio.auto.DMcontroller;
import net.vezio.auto.Grammar;
import net.vezio.auto.Levels;
import net.vezio.auto.MusicTranslator;
import net.vezio.auto.ServerLogs;
import net.vezio.auto.SpamFilter;
import net.vezio.auto.Suggestions;
import net.vezio.auto.UpdatePing;
import net.vezio.commands.prefix.BotCommands;

public class onMessageReceivedEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        //ServerLogs.messageCreated(event);
        BotCommands.commands(message);
        SpamFilter.filterSpam(event);
        Suggestions.suggestions(message);
        Grammar.check(message);
        DMcontroller.controller(message);
        UpdatePing.ping(message);
        MusicTranslator.musicTranslator(message);
        Levels.levels(message);
    }
}
