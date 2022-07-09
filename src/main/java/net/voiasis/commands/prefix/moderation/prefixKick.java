package net.voiasis.commands.prefix.moderation;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;

public class prefixKick {
    public static void kick(Member member, Message message, String[] args) {
        if (member.hasPermission(Permission.KICK_MEMBERS)) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            if (message.getMentions().getMembers().toArray().length == 1) {
                if (message.getContentRaw().toCharArray().length >= args[0].length() + args[1].length() + 2) {
                    Member mentioned = message.getGuild().getMember(message.getMentions().getMembers().get(0));
                    String reason = message.getContentRaw().substring(args[0].length() + args[1].length() + 2);
                    message.getGuild().kick(mentioned, reason).queue();
                    embed.addField("User kicked", mentioned.getAsMention() + " has been kicked with reason \"" + reason +"\".", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else {
                    Member mentioned = message.getGuild().getMember(message.getMentions().getMembers().get(0));
                    message.getGuild().kick(mentioned).queue();
                    embed.addField("User kicked", mentioned.getAsMention() + " has been kicked.", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                }
            } else if (!args[1].isEmpty()) {
                if (message.getContentRaw().toCharArray().length >= args[0].length() + args[1].length() + 2) {
                    UserSnowflake mentioned = User.fromId(args[1]);
                    String reason = message.getContentRaw().substring(args[0].length() + args[1].length() + 2);
                    message.getGuild().kick(mentioned, reason).queue();
                    embed.addField("User kicked", mentioned.getAsMention() + " has been kicked with reason \"" + reason +"\".", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else {
                    UserSnowflake mentioned = User.fromId(args[1]);
                    message.getGuild().kick(mentioned).queue();
                    embed.addField("User kicked", mentioned.getAsMention() + " has been kicked.", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                }
            }
        }
    }
}