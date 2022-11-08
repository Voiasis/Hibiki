package net.voiasis.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.auto.DMcontroller;
import net.voiasis.auto.Grammar;
import net.voiasis.auto.Levels;
import net.voiasis.auto.MusicTranslator;
import net.voiasis.auto.SpamFilter;
import net.voiasis.auto.Suggestions;
import net.voiasis.commands.music.Music;
import net.voiasis.commands.prefix.BotCommands;

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
        MusicTranslator.musicTranslator(message);
        Music.onMessageReceived(event);
        //Levels levels = new Levels();
        //levels.levels(message);
    }
}
