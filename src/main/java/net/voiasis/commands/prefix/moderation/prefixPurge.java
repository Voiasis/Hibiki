package net.voiasis.commands.prefix.moderation;

import java.util.List;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;

public class prefixPurge {
    public static void purge(Message message, String[] args) {
        if (message.isFromGuild()) {
            if (message.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                if (message.getContentRaw().toCharArray().length >= 8) {
                    try {
                        int values = Integer.parseInt(args[1]);
                        message.delete();
                        List<Message> messages = message.getTextChannel().getHistory().retrievePast(values + 1).complete();
                        message.getTextChannel().deleteMessages(messages).queue();
                    } catch (Exception ex) {
                    } 
                }
            }
        }
    }
}
