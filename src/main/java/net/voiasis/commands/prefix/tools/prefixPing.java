package net.voiasis.commands.prefix.tools;

import java.awt.Color;
import java.time.temporal.ChronoUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

public class prefixPing {
    public static void pingCreate(Message message) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        long gw = message.getJDA().getGatewayPing();
        String gwp = Long.toString(gw);
        embed.addField("Pong!", "Ping: ... \nWebsocket: ...", false);
        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue(msg -> {
            embed.clear();
            embed.setColor(Color.RED);
            long ping = message.getTimeCreated().until(msg.getTimeCreated(), ChronoUnit.MILLIS);
            embed.addField("Pong!", "Ping: " + ping + " ms\nWebsocket: " + gwp + " ms", false);
            msg.editMessageEmbeds(embed.build()).mentionRepliedUser(false).queue();
        });
    }
    public static void pingEdit(Message message) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        long gw = message.getJDA().getGatewayPing();
        String gwp = Long.toString(gw);
        embed.addField("Pong!", "Ping: ... \nWebsocket: ...", false);
        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue(msg -> {
            embed.clear();
            embed.setColor(Color.RED);
            long ping = message.getTimeEdited().until(msg.getTimeCreated(), ChronoUnit.MILLIS);
            embed.addField("Pong!", "Ping: " + ping + " ms\nWebsocket: " + gwp + " ms", false);
            msg.editMessageEmbeds(embed.build()).mentionRepliedUser(false).queue();
        });
    }
}
