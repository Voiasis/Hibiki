package net.voiasis.auto;

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
        if (guild.getVoiceChannels().get(0).getName().contains("Members")) {
            guild.getVoiceChannels().get(0).getManager().setName(humans + " Members").queue();
        }
    }
}