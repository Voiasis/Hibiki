package net.vezio.auto;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;

public class MemberCounter {
    public static void addMember(GuildMemberJoinEvent event) {
        update(event.getGuild());
    }
    public static void RemoveMember(GuildMemberRemoveEvent event) {
        update(event.getGuild());
    }
    private static void update(Guild guild) {
        long bots = guild.getMembers().stream().filter(m -> m.getUser().isBot()).count();
        long humans = guild.getMemberCount() - bots;
        guild.getVoiceChannelById("1010933102425681920").getManager().setName(humans + " Members").queue();
    }
}
