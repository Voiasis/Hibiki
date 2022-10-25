package net.vezio.auto;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;

public class Welcomer {
    public static void join(Member member) {
        TextChannel channel = member.getJDA().getGuildById("1010933101939130462").getTextChannelById("1010933102207582231");
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.addField("Welcome!", member.getUser().getAsTag() + " has joined the server.", false);
        embed.setFooter("User ID: " + member.getId(), member.getUser().getAvatarUrl());
        channel.sendMessageEmbeds(embed.build()).queue();
        embed.clear();
        if (member.getUser().isBot()) {
            Role role = member.getGuild().getRoleById("1010933101939130467");
            UserSnowflake user = User.fromId(member.getUser().getId());
            member.getGuild().addRoleToMember(user, role).queue();
        } else {
            Role role = member.getGuild().getRoleById("1010933101939130468");
            UserSnowflake user = User.fromId(member.getUser().getId());
            member.getGuild().addRoleToMember(user, role).queue();
        }
    }
    public static void leave(Member member) {
        TextChannel channel = member.getJDA().getGuildById("1010933101939130462").getTextChannelById("1010933102207582231");
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.addField("Goodbye!", member.getUser().getAsTag() + " has left the server.", false);
        embed.setFooter("User ID: " + member.getId(), member.getUser().getAvatarUrl());
        channel.sendMessageEmbeds(embed.build()).queue();
        embed.clear();
    }
}