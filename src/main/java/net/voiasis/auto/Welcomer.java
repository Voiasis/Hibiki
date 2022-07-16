package net.voiasis.auto;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;

public class Welcomer {
    public static void join(Member member) {
        TextChannel channel = member.getJDA().getGuildById("902397621015040020").getTextChannelById("954498679887233065");
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.addField("Welcome!", member.getUser().getAsTag() + " has joined the server.", false);
        embed.setFooter("User ID: " + member.getId(), member.getUser().getAvatarUrl());
        channel.sendMessageEmbeds(embed.build()).queue();
        embed.clear();
        if (!member.getUser().isBot()) {
            Role role = member.getGuild().getRoleById("995455726547914812");
            UserSnowflake user = User.fromId(member.getUser().getId());
            member.getGuild().addRoleToMember(user, role).queue();
        } else {
            Role role = member.getGuild().getRoleById("903466755127656489");
            UserSnowflake user = User.fromId(member.getUser().getId());
            member.getGuild().addRoleToMember(user, role).queue();
        }
    }
    public static void leave(Member member) {
        TextChannel channel = member.getJDA().getGuildById("902397621015040020").getTextChannelById("954498679887233065");
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.addField("Goodbye!", member.getUser().getAsTag() + " has left the server.", false);
        embed.setFooter("User ID: " + member.getId(), member.getUser().getAvatarUrl());
        channel.sendMessageEmbeds(embed.build()).queue();
        embed.clear();
    }
}