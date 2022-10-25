package net.vezio.commands.prefix.moderation;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

public class prefixMute {
    public static void mute(Message message, String[] args) {
        if (message.isFromGuild()) {
            if (message.getMember().hasPermission(Permission.MODERATE_MEMBERS)) {
                if (!args[1].isBlank() && !args[2].isBlank()) {
                    int time = Integer.parseInt(args[2].substring(0, args[2].length() - 1));
                    String letter = args[2].substring(args[2].length() - 1);
                    if (message.getContentRaw().toCharArray().length >= args[0].length() + args[1].length() + args[2].length() + 3) {
                        String reason = message.getContentRaw().substring(args[0].length() + args[1].length() + args[2].length() + 3);
                        if (message.getMentions().getMembers().toArray().length == 1) {
                            Member mentioned = message.getGuild().getMember(message.getMentions().getMembers().get(0));
                            timeoutYes(letter, time, reason, mentioned, message);
                        } else {
                            Member mentioned = message.getGuild().getMemberById(args[1]);
                            timeoutYes(letter, time, reason, mentioned, message);
                        }
                    } else {
                        if (message.getMentions().getMembers().toArray().length == 1) {
                            Member mentioned = message.getGuild().getMember(message.getMentions().getMembers().get(0));
                            timeoutNo(letter, time, mentioned, message);
                        } else {
                            Member mentioned = message.getGuild().getMemberById(args[1]);
                            timeoutNo(letter, time, mentioned, message);
                        }
                    }  
                }
            } 
        } 
    }
    private static void timeoutYes(String letter, int time, String reason, Member mentioned, Message message) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        switch (letter) {
            case "s" :
            mentioned.timeoutFor(time, TimeUnit.SECONDS).reason(reason).queue();
            embed.addField("User Muted", mentioned.getAsMention() + " has been muted for " + time + " seconds with reason \"" + reason + "\".", false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "m" :
            mentioned.timeoutFor(time, TimeUnit.MINUTES).reason(reason).queue();
            embed.addField("User Muted", mentioned.getAsMention() + " has been muted for " + time + " minutes with reason \"" + reason + "\".", false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "h" :
            mentioned.timeoutFor(time, TimeUnit.HOURS).reason(reason).queue();
            embed.addField("User Muted", mentioned.getAsMention() + " has been muted for " + time + " hours with reason \"" + reason + "\".", false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "d" :
            mentioned.timeoutFor(time, TimeUnit.DAYS).reason(reason).queue();
            embed.addField("User Muted", mentioned.getAsMention() + " has been muted for " + time + " days with reason \"" + reason + "\".", false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "w" :
            mentioned.timeoutFor(time * 7, TimeUnit.DAYS).reason(reason).queue();
            embed.addField("User Muted", mentioned.getAsMention() + " has been muted for " + time + " weeks with reason \"" + reason + "\".", false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
        }
    }
    private static void timeoutNo(String letter, int time, Member mentioned, Message message) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        switch (letter) {
            case "s" :
            mentioned.timeoutFor(time, TimeUnit.SECONDS).queue();
            embed.addField("User Muted", mentioned.getAsMention() + " has been muted for " + time + " seconds.", false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "m" :
            mentioned.timeoutFor(time, TimeUnit.MINUTES).queue();
            embed.addField("User Muted", mentioned.getAsMention() + " has been muted for " + time + " minutes.", false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "h" :
            mentioned.timeoutFor(time, TimeUnit.HOURS).queue();
            embed.addField("User Muted", mentioned.getAsMention() + " has been muted for " + time + " hours.", false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "d" :
            mentioned.timeoutFor(time, TimeUnit.DAYS).queue();
            embed.addField("User Muted", mentioned.getAsMention() + " has been muted for " + time + " days.", false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "w" :
            mentioned.timeoutFor(time * 7, TimeUnit.DAYS).queue();
            embed.addField("User Muted", mentioned.getAsMention() + " has been muted for " + time + " weeks.", false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
        }
    }
}