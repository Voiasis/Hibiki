package net.voiasis.commands.prefix.tools;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

public class prefixStop {
    public static void stop(Member member) {
        if (member.hasPermission(Permission.ADMINISTRATOR) && !member.getUser().isBot()) {
            member.getJDA().getGuildById("902397621015040020").getTextChannelById("953515276144619601").sendMessage("<@472899069136601099>, " + member.getUser().getAsTag() + " has used stop!")
            .queue(m -> {
                member.getJDA().getPresence().setStatus(OnlineStatus.INVISIBLE);
                member.getJDA().shutdown();
            });
           
        }
    }
}
