package net.voiasis.commands.prefix.moderation;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;

public class prefixBan {
    public static void ban(Member member, Message message, String[] args) {
        if (member.hasPermission(Permission.BAN_MEMBERS)) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            if (message.getMentions().getMembers().toArray().length == 1) {
                if (message.getContentRaw().toCharArray().length >= args[0].length() + args[1].length() + 2) {
                    Member mentioned = message.getGuild().getMember(message.getMentions().getMembers().get(0));
                    String reason = message.getContentRaw().substring(args[0].length() + args[1].length() + 2);
                    message.getGuild().ban(mentioned, 0, reason).queue();
                    embed.addField("User Banned", "<:bans:994817734959431851> " + mentioned.getAsMention() + " has been banned with reason \"" + reason +"\".", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else {
                    Member mentioned = message.getGuild().getMember(message.getMentions().getMembers().get(0));
                    message.getGuild().ban(mentioned, 0).queue();
                    embed.addField("User Banned", "<:bans:994817734959431851> " + mentioned.getAsMention() + " has been banned.", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                }
            } else if (!args[1].isEmpty()) {
                if (message.getContentRaw().toCharArray().length >= args[0].length() + args[1].length() + 2) {
                    UserSnowflake mentioned = User.fromId(args[1]);
                    String reason = message.getContentRaw().substring(args[0].length() + args[1].length() + 2);
                    message.getGuild().ban(mentioned, 0, reason).queue();
                    embed.addField("User Banned", "<:bans:994817734959431851> " + mentioned.getAsMention() + " has been banned with reason \"" + reason +"\".", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else {
                    UserSnowflake mentioned = User.fromId(args[1]);
                    message.getGuild().ban(mentioned, 0).queue();
                    embed.addField("User Banned", "<:bans:994817734959431851> " + mentioned.getAsMention() + " has been banned.", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                }
            }
        }
    }
}