package net.voiasis.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.auto.SpamFilter;
import net.voiasis.auto.Suggestions;
import net.voiasis.auto.WordFilter;
import net.voiasis.auto.WordReplacer;
import net.voiasis.commands.prefix.BotCommands;

public class onMessageReceivedEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        WordFilter.filter(event.getMessage());
        WordReplacer.replacer(event.getMessage());
        BotCommands.commands(event.getMessage(), event.getAuthor(), event.getMember(), event.getGuild());
        SpamFilter.filterSpam(event);
        Suggestions.suggestions(event.getMessage());
    }
}
