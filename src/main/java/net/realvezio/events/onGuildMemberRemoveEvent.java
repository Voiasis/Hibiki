package net.realvezio.events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.realvezio.auto.MemberCounter;
import net.realvezio.auto.Welcomer;

public class onGuildMemberRemoveEvent extends ListenerAdapter {
    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        Welcomer.leave(event.getMember());
        MemberCounter.RemoveMember(event);
    }
}
