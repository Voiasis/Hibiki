package net.voiasis.commands.prefix.moderation;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class prefixBan {
    public static void ban(Message message, String[] args) {
        if (message.isFromGuild()) {
            if (message.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.RED);
                if (message.getMentions().getMembers().toArray().length == 1) {
                    if (message.getContentRaw().toCharArray().length >= args[0].length() + args[1].length() + 2) {
                        Member mentioned = message.getGuild().getMember(message.getMentions().getMembers().get(0));
                        String reason = message.getContentRaw().substring(args[0].length() + args[1].length() + 2);
                        message.getGuild().ban(mentioned.getUser(), 0, TimeUnit.SECONDS).reason(reason).queue();
                        embed.addField("User Banned", "<:bans:1010954107135217794> " + mentioned.getAsMention() + " has been banned with reason \"" + reason +"\".", false);
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    } else {
                        Member mentioned = message.getGuild().getMember(message.getMentions().getMembers().get(0));
                        message.getGuild().ban(mentioned.getUser(), 0, TimeUnit.SECONDS).queue();
                        embed.addField("User Banned", "<:bans:1010954107135217794> " + mentioned.getAsMention() + " has been banned.", false);
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    }
                } else if (!args[1].isEmpty()) {
                    if (message.getContentRaw().toCharArray().length >= args[0].length() + args[1].length() + 2) {
                        User user = message.getJDA().retrieveUserById(args[1]).complete();
                        String reason = message.getContentRaw().substring(args[0].length() + args[1].length() + 2);
                        message.getGuild().ban(user, 0, TimeUnit.SECONDS).reason(reason).queue();
                        embed.addField("User Banned", "<:bans:1010954107135217794> " + user.getAsMention() + " has been banned with reason \"" + reason +"\".", false);
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    } else {
                        User user = message.getJDA().retrieveUserById(args[1]).complete();
                        message.getGuild().ban(user, 0, TimeUnit.SECONDS).queue();
                        embed.addField("User Banned", "<:bans:1010954107135217794> " + user.getAsMention() + " has been banned.", false);
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    }
                }
            }
        }
    }
}