package net.voiasis.commands.prefix.moderation;

import java.util.List;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

public class prefixPurge {
    public static void purge(Member member, Guild guild, Message message, String[] args) {
        if (member.hasPermission(Permission.MESSAGE_MANAGE)) {
            if (message.getContentRaw().toCharArray().length >= 8) {
                try {
                    int values = Integer.parseInt(args[1]);
                    message.delete();
                    List<Message> messages = message.getTextChannel().getHistory().retrievePast(values).complete();
                    message.getTextChannel().deleteMessages(messages).queue();
                } catch (Exception ex) {
                } 
            }
        }
    }
}
