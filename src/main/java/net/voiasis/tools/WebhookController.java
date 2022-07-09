package net.voiasis.tools;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
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
            message.getChannel().sendMessage("**" + username + "** <:bot:995196612550869062> *Today at " + BotLog.clock() + "*\r\n" + content).queue();
        }
    }
    private static Webhook webhook(Message message) {
        if (message.getTextChannel().retrieveWebhooks().complete().isEmpty()) {
            Webhook webhook = message.getTextChannel().createWebhook("Ellie").complete();
            return webhook;
        } else {
            int count = message.getTextChannel().retrieveWebhooks().complete().size();
            while (count > 0) {
                if (message.getTextChannel().retrieveWebhooks().complete().get(count - 1).getOwnerAsUser() == message.getJDA().getSelfUser()) {
                    Webhook webhook = message.getTextChannel().retrieveWebhooks().complete().get(count - 1);
                    return webhook;
                } else {
                    if (count == 1) {
                        Webhook webhook = message.getTextChannel().createWebhook("Webhook").complete();
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
