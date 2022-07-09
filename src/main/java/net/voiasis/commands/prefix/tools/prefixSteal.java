package net.voiasis.commands.prefix.tools;

import java.io.InputStream;
import java.net.URL;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.voiasis.tools.BotLog;

public class prefixSteal {
    public static void steal(Guild guild, Member member, Message message, String[] args) {
        if (member.hasPermission(Permission.MANAGE_EMOJIS_AND_STICKERS)) {
            if (message.getContentRaw().toCharArray().length >= args[0].length() + args[1].length() + 2) {
                if (message.getMentions().getCustomEmojis().isEmpty()) {
                    try {
                        String newEmojiName = args[1];
                        InputStream inputstream = new URL(args[2]).openStream();
                        Icon icon = Icon.from(inputstream);
                        guild.createEmoji(newEmojiName, icon).queue(e -> message.reply(e.getAsMention() + " added with the name \"" + e.getName() + "\"").mentionRepliedUser(false).queue());
                    } catch(Exception ex) {
                        BotLog.log(BotLog.getStackTraceString(ex, guild.getJDA()), "Steal", 4);
                    }
                } else {
                    try {
                        String newEmojiName = args[1];
                        InputStream inputstream = new URL(message.getMentions().getCustomEmojis().get(0).getImageUrl()).openStream();
                        Icon icon = Icon.from(inputstream);
                        guild.createEmoji(newEmojiName, icon).queue(e -> message.reply(e.getAsMention() + " added with the name \"" + e.getName() + "\"").mentionRepliedUser(false).queue());
                    } catch(Exception ex) {
                        BotLog.log(BotLog.getStackTraceString(ex, guild.getJDA()), "Steal", 4);
                    }
                }
            } else if (!message.getAttachments().isEmpty() && message.getContentRaw().toCharArray().length >= args[0].length() + 1) {
                if (!message.getAttachments().get(0).getFileExtension().equalsIgnoreCase("JPEG") &&
                !message.getAttachments().get(0).getFileExtension().equalsIgnoreCase("PNG") && 
                !message.getAttachments().get(0).getFileExtension().equalsIgnoreCase("GIF") &&
                !message.getAttachments().get(0).getFileExtension().equalsIgnoreCase("JPG")) {
                } else {
                    try {
                        String newEmojiName = args[1];
                        InputStream inputstream = new URL(message.getAttachments().get(0).getUrl()).openStream();
                        Icon icon = Icon.from(inputstream);
                        guild.createEmoji(newEmojiName, icon).queue(e -> message.reply(e.getAsMention() + " added with the name \"" + e.getName() + "\"").mentionRepliedUser(false).queue());
                    } catch(Exception ex) {
                        BotLog.log(BotLog.getStackTraceString(ex, guild.getJDA()), "Steal", 4);
                    }
                }
            }
        }
    }
}
