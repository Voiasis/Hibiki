package net.voiasis.commands.prefix.moderation;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

public class prefixNsfw {
    public static void nsfw(Member member, Message message) {
        if (member.hasPermission(Permission.MANAGE_CHANNEL)) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            if (message.getTextChannel().isNSFW()) {
                message.getTextChannel().getManager().setNSFW(false).queue();
                embed.addField("NSFW Updated", "NSFW has been disabled.", false);
                message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            } else {
                message.getTextChannel().getManager().setNSFW(true).queue();
                embed.addField("NSFW Updated", "NSFW has been enabled.", false);
                message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            }
        }
    }
}
