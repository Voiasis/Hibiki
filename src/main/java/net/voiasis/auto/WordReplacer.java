package net.voiasis.auto;

import net.dv8tion.jda.api.entities.Message;
import net.voiasis.tools.SearchTools;
import net.voiasis.tools.WebhookController;

public class WordReplacer {
    public static void replacer(Message message) {
        String msg = " " + message.getContentRaw().toLowerCase() + " ";
        if (msg.contains(" ur ")) {
            if (SearchTools.intRegexSearchStart(" ur ", msg) == 0) {
                String msgNew = msg.replace(" ur ", "your ");
                message.delete().queue();
                WebhookController.send(message, message.getAuthor().getName(), message.getAuthor().getAvatarUrl(), msgNew);
            } else {
                String msgNew = msg.replace(" ur ", " your ");
                message.delete().queue();
                WebhookController.send(message, message.getAuthor().getName(), message.getAuthor().getAvatarUrl(), msgNew);
            }
        }
    }
}
