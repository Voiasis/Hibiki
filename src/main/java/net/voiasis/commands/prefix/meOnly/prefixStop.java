package net.voiasis.commands.prefix.meOnly;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Message;

public class prefixStop {
    public static void stop(Message message) {
        if (message.getAuthor().getId().equals("472899069136601099")) {
            message.getJDA().getGuildById("1010933101939130462").getTextChannelById("953515276144619601").sendMessage("<@472899069136601099>, " + message.getAuthor().getAsTag() + " has used stop!")
            .queue(m -> {
                message.getJDA().getPresence().setStatus(OnlineStatus.INVISIBLE);
                message.getJDA().shutdown();
            });
           
        }
    }
}
