package net.voiasis.commands.prefix.moderation;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class prefixKick {
    public static void kick(Message message, String[] args) {
        if (message.isFromGuild()) {
            if (message.getMember().hasPermission(Permission.KICK_MEMBERS)) {
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
                        User user = message.getJDA().retrieveUserById(args[1]).complete();
                        String reason = message.getContentRaw().substring(args[0].length() + args[1].length() + 2);
                        message.getGuild().kick(user, reason).queue();
                        embed.addField("User kicked", user.getAsMention() + " has been kicked with reason \"" + reason +"\".", false);
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    } else {
                        User user = message.getJDA().retrieveUserById(args[1]).complete();
                        message.getGuild().kick(user).queue();
                        embed.addField("User kicked", user.getAsMention() + " has been kicked.", false);
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    }
                }
            }
        }
    }
}