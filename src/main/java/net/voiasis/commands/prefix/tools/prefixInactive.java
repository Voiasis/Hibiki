package net.voiasis.commands.prefix.tools;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.voiasis.tools.BotLog;

public class prefixInactive {
    public static void inactive(Message message, User author) {
        if (author.getId().equals("472899069136601099")) {
            BotLog.inactive(message.getJDA());
        }
    }
}
