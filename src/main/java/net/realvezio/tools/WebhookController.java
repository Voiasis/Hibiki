package net.realvezio.tools;

import java.awt.Color;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Webhook;

public class WebhookController {
    public static void send(Message message, String username, String avatarURL, String content) {
        try {
            WebhookClient client = WebhookClient.withUrl(webhook(message).getUrl());
            WebhookMessageBuilder builder = new WebhookMessageBuilder();
            builder.setUsername(username);
            builder.setAvatarUrl(avatarURL.replaceFirst("gif", "png") + "?size=512");
            builder.setContent(content);
            client.send(builder.build());
        } catch (IllegalStateException e) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            embed.setAuthor(username, avatarURL, avatarURL);
            embed.setDescription(content);
            message.getChannel().sendMessageEmbeds(embed.build()).queue();
        }
    }
    private static Webhook webhook(Message message) {
        if (message.getChannel().asTextChannel().retrieveWebhooks().complete().isEmpty()) {
            Webhook webhook = message.getChannel().asTextChannel().createWebhook("Ellie").complete();
            return webhook;
        } else {
            int count = message.getChannel().asTextChannel().retrieveWebhooks().complete().size();
            while (count > 0) {
                if (message.getChannel().asTextChannel().retrieveWebhooks().complete().get(count - 1).getOwnerAsUser() == message.getJDA().getSelfUser()) {
                    Webhook webhook = message.getChannel().asTextChannel().retrieveWebhooks().complete().get(count - 1);
                    return webhook;
                } else {
                    if (count == 1) {
                        Webhook webhook = message.getChannel().asTextChannel().createWebhook("Webhook").complete();
                        return webhook;
                    } else {
                        count--;
                    }
                }
            }
            return null;
        }
    }
}
