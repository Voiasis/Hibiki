package net.voiasis.events;

import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.tools.NameNormalizer;

public class onMessageReactionRemoveEvent extends ListenerAdapter {
    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
        NameNormalizer.normalizer(event.getMember(), event.getGuild());
    }
}
