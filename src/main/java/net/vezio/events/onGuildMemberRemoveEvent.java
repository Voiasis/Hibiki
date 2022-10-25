package net.vezio.events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.vezio.auto.MemberCounter;
import net.vezio.auto.Welcomer;

public class onGuildMemberRemoveEvent extends ListenerAdapter {
    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        Welcomer.leave(event.getMember());
        MemberCounter.RemoveMember(event);
    }
}
