package net.voiasis.commands.prefix;

import net.dv8tion.jda.api.entities.Message;
import net.voiasis.tools.WebhookController;

public class prefixTest {
    public static void test(Message message, String[] args) {
        if (message.getAuthor().getId().equals("472899069136601099")) {
            WebhookController.send(message, message.getAuthor().getName(), message.getAuthor().getAvatarUrl(), message.getContentRaw().substring(args[0].length()));
            message.delete().queue();
        }
    }
}
