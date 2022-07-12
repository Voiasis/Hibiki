package net.voiasis.commands.prefix.moderation;

import net.dv8tion.jda.api.entities.Message;
import net.voiasis.tools.WebhookController;

public class prefixWebhook {
    public static void webhook(Message message, String[] args) {
        if (message.isFromGuild()) {
            if (message.getMember().getId().equals("472899069136601099")) {
                WebhookController.send(message, message.getAuthor().getName(), message.getAuthor().getAvatarUrl(), message.getContentRaw().substring(args[0].length()));
                message.delete().queue();
            }
        } else {
            message.reply("This command only works inside a guild.").mentionRepliedUser(false).queue();
        }
    }
}
