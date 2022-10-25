package net.vezio.events;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.vezio.auto.MemberCounter;
import net.vezio.auto.NameNormalizer;
import net.vezio.auto.Welcomer;

public class onGuildMemberJoinEvent extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Welcomer.join(event.getMember());
        NameNormalizer.normalizer(event.getMember());
        MemberCounter.addMember(event);

        if (event.getMember().getId().equals("472899069136601099") && event.getGuild().getId().equals("1010933101939130462")) {
            event.getGuild().addRoleToMember(User.fromId("472899069136601099"), event.getGuild().getRoleById("903468225000505404")).queue();
        }
    }
}