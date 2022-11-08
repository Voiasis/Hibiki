package net.voiasis.events;

import net.dv8tion.jda.api.events.channel.ChannelCreateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.auto.ForumNameFilter;

public class onChannelCreateEvent extends ListenerAdapter {
    @Override
    public void onChannelCreate(ChannelCreateEvent event) {
        ForumNameFilter.ForumNameFilter(event);
    }
}
