package net.voiasis.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.commands.prefix.BotCommands;
import net.voiasis.tools.AutoTasks;
import net.voiasis.tools.NameNormalizer;
import net.voiasis.tools.SpamFilter;

public class onMessageReceivedEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        BotCommands.commands(event.getMessage(), event.getAuthor(), event.getMember(), event.getGuild());
        AutoTasks.tasks(event.getMessage(), event.getChannel(), event.getAuthor(), event.getMember(), event.getGuild());
        SpamFilter.filterSpam(event);
        NameNormalizer.normalizer(event.getMember(), event.getGuild());
    }
}
