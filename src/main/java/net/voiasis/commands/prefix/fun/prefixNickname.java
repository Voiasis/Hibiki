package net.voiasis.commands.prefix.fun;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

public class prefixNickname {
    public static void nickname(Member member, Message message, Guild guild, String[] args) {
        if (member.getId().equals("472899069136601099")) {
            if (message.getContentRaw().length() > args[0].length() + 1) {
                guild.modifyNickname(guild.getSelfMember(), message.getContentRaw().substring(args[0].length() + 1)).queue();
            }
        }
    }
}
