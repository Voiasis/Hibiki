package net.realvezio.commands.prefix.tools;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class prefixHelp {
    public static void help(Message message) {
        if (message.isFromGuild()) {
            if (!message.getChannel().getId().equals("1010947434173694055") /* vez dev thread */ && !message.getChannel().getId().equals("1010933102807363708") && !message.getChannel().getId().equals("1010933102949965905")) {
                if (!message.getCategory().getId().equals("903472513290481664")) { //mod
                    message.reply("Wrong channel. Please move to <#1010933102807363708> first.").mentionRepliedUser(false).queue(); //bot-commands
                } else {
                    message.reply("Wrong channel. Please move to <#1010933102949965905> first.").mentionRepliedUser(false).queue(); //mod
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
        embed.setDescription("Tools - <:settings:1010953881963986944>\r\nModeration - <:bans:1010954107135217794>");
        embed.setFooter("Programmed by realVezio#0363");
        message.replyEmbeds(embed.build()).setActionRow(
            Button.primary("tools:1", Emoji.fromCustom("settings", Long.parseLong("1010953881963986944"), false)),
            Button.primary("mod:1", Emoji.fromCustom("bans", Long.parseLong("1010954107135217794"), false)))
        .mentionRepliedUser(false).queue();
    }
}
