package net.voiasis.commands.prefix.meOnly;

import net.dv8tion.jda.api.entities.Message;

public class prefixNickname {
    public static void nickname(Message message, String[] args) {
        if (message.isFromGuild()) {
            if (message.getAuthor().getId().equals("472899069136601099")) {
                if (message.getContentRaw().length() > args[0].length() + 1) {
                    message.getGuild().modifyNickname(message.getGuild().getSelfMember(), message.getContentRaw().substring(args[0].length() + 1)).queue();
                }
            }
        }
    }
}
