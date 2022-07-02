package net.voiasis.events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.tools.NameNormalizer;

public class onGuildMemberJoinEvent extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        NameNormalizer.normalizer(event.getMember(), event.getGuild());
    }
}
