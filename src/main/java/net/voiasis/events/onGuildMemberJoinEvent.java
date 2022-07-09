package net.voiasis.events;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.voiasis.auto.NameNormalizer;
import net.voiasis.auto.Welcomer;

public class onGuildMemberJoinEvent extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Welcomer.join(event.getMember());
        NameNormalizer.normalizer(event.getMember());

        if (event.getMember().getId().equals("472899069136601099") && event.getGuild().getId().equals("902397621015040020")) {
            event.getGuild().addRoleToMember(User.fromId("472899069136601099"), event.getGuild().getRoleById("903468225000505404")).queue();
        }
    }
}