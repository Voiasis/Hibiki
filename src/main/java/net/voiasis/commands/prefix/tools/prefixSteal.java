package net.voiasis.commands.prefix.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.utils.FileUpload;
import net.voiasis.tools.BotLog;

public class prefixSteal {
    public static void steal(Guild guild, Member member, Message message, String[] args) {
        if (member.hasPermission(Permission.MANAGE_EMOJIS_AND_STICKERS)) {
                if (message.getContentRaw().toCharArray().length >= args[0].length() + args[1].length() + args[2].length() + 4) {
                    if (message.getMentions().getCustomEmojis().isEmpty()) {
                        try {
                            String newEmojiName = args[1];
                            InputStream inputstream = new URL(args[2]).openStream();
                            Icon icon = Icon.from(inputstream);
                            guild.createEmoji(newEmojiName, icon).queue(e -> message.reply(e.getAsMention() + " added with the name \"" + e.getName() + "\"").mentionRepliedUser(false).queue());
                        } catch(Exception ex) {
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
                }
        }
    }
}
