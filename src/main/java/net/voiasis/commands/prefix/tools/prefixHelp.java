package net.voiasis.commands.prefix.tools;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class prefixHelp {
    public static void help(Message message) {
        if (message.isFromGuild()) {
            if (!message.getChannel().getId().equals("995049819187990618") && !message.getChannel().getId().equals("994800774683054200") && !message.getChannel().getId().equals("903473272983814235")) {
                if (!message.getCategory().getId().equals("903472513290481664")) {
                    message.reply("Wrong channel. Please move to <#994800774683054200> first.").mentionRepliedUser(false).queue();
                } else {
                    message.reply("Wrong channel. Please move to <#903473272983814235> first.").mentionRepliedUser(false).queue();
                }
            } else {
                send(message);
            }
        } else {
            send(message);
        }
    }
    private static void send(Message message) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.setTitle("Main page");
        embed.setDescription("Tools - <:settings:994843742567673896>\r\nModeration - <:bans:994817734959431851>");
        message.replyEmbeds(embed.build()).setActionRow(
            Button.primary("tools:1", Emoji.fromCustom("settings", Long.parseLong("994843742567673896"), false)),
            Button.primary("mod:1", Emoji.fromCustom("bans", Long.parseLong("994817734959431851"), false)))
        .mentionRepliedUser(false).queue();
    }
}
