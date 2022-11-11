package net.voiasis.auto;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.UserSnowflake;

public class Welcomer {
    public static void join(Member member) {
        if (member.getUser().isBot()) {
            Role role = member.getGuild().getRolesByName("Robots", false).get(0);
            UserSnowflake user = member.getUser();
            member.getGuild().addRoleToMember(user, role).queue();
        } else {
            TextChannel channel = member.getGuild().getTextChannelsByName("joins-leaves", false).get(0);
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            embed.addField("Welcome!", member.getUser().getAsTag() + " has joined the server.", false);
            embed.setFooter("User ID: " + member.getId(), member.getUser().getAvatarUrl());
            channel.sendMessageEmbeds(embed.build()).queue();
            if (member.getGuild().getId().equals("1039784854495186975")) {
                Role role = member.getGuild().getRoleById("1039800092670038036");
                UserSnowflake user = member.getUser();
                member.getGuild().addRoleToMember(user, role).queue();
            }
        }
    }
    public static void leave(Member member) {
        if (!member.getUser().isBot()) {
            TextChannel channel = member.getGuild().getTextChannelsByName("joins-leaves", false).get(0);
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            embed.addField("Goodbye!", member.getUser().getAsTag() + " has left the server.", false);
            embed.setFooter("User ID: " + member.getId(), member.getUser().getAvatarUrl());
            channel.sendMessageEmbeds(embed.build()).queue();
        }
    }
}