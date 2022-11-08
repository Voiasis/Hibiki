package net.voiasis.events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.auto.MemberCounter;
import net.voiasis.auto.Welcomer;

public class onGuildMemberRemoveEvent extends ListenerAdapter {
    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        Welcomer.leave(event.getMember());
        MemberCounter.RemoveMember(event);
    }
}
