package net.realvezio.commands.prefix.tools;

import net.dv8tion.jda.api.entities.Message;

public class prefixJumbo {
    public static void jumbo(Message message) {
        if (message.getMentions().getCustomEmojis().size() == 1) {
            String emojilink = message.getMentions().getCustomEmojis().get(0).getImageUrl();
            message.reply(emojilink).mentionRepliedUser(false).queue();
        }
    }
}
