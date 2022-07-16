package net.voiasis.commands.prefix.moderation;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

public class prefixRules {
    public static void rules(Message message) {
        if (message.isFromGuild()) {
            if (message.getMember().hasAccess(message.getGuild().getGuildChannelById("903473047221202984"))) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.RED);
                embed.setTitle("Server Rules");
                embed.setDescription("**English Only**\r\nThis is a English speaking server so please keep conversations in English so the moderators can properly do their jobs.\r\n\r\n" +
                "**NSFW is not allowed**\r\nNSFW content such as nudity and erotic conversation are not allowed.\r\n\r\n" +
                "**Please refrain from pinging staff**\r\nYou can only ping staff as part of the active conversation (or if they're a friend). If you need help, you can <#927678238560489532>.\r\n\r\n" +
                "**Keep topics in the correct channels**\r\nPlease try your best to keep conversations in the correct channel. If you don't know what you can talk about in a channel, check the channel description.\r\n\r\n" +
                "**Keep arguments to a minimum**\r\nArguing is allowed as it's normal for humans to get in disagreements but please try to keep it at a minimum and __do not argue with staff about rules and punishments__.\r\n\r\n" +
                "**Follow [Discord's Community Guidelines](https://discord.com/guidelines)**\r\nAs this is a community server, it needs to be a safe environment for everyone.");
                message.getChannel().sendMessageEmbeds(embed.build()).mentionRepliedUser(false).queue();
            }
        }
    }
}
