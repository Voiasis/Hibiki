package net.voiasis.events;

import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.commands.prefix.BotCommands;
import net.voiasis.tools.AutoTasks;
import net.voiasis.tools.NameNormalizer;

public class onMessageUpdateEvent extends ListenerAdapter {
    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        BotCommands.commands(event.getMessage(), event.getAuthor(), event.getMember(), event.getGuild());
        AutoTasks.tasks(event.getMessage(), event.getChannel(), event.getAuthor(), event.getMember(), event.getGuild());
        NameNormalizer.normalizer(event.getMember(), event.getGuild());
    }
}
