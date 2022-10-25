package net.vezio.auto;

import net.dv8tion.jda.api.entities.Message;

public class UpdatePing {
    public static void ping(Message message) {
        if (!message.getAuthor().isBot()) {
            String id = message.getChannel().getId();
            switch (id) {
                case "1010935260604805171" :
                message.reply("<@&1010933101939130465> ||Don't want to be pinged? Remove your role in <#1010933102425681923>||").mentionRepliedUser(false).queue();
                break;
                case "1010933102425681925" :
                message.reply("<@&1010933101939130464> ||Don't want to be pinged? Remove your role in <#1010933102425681923>||").mentionRepliedUser(false).queue();
                break;
                case "1010933102425681926" :
                message.reply("<@&1010933101939130463> ||Don't want to be pinged? Remove your role in <#1010933102425681923>||").mentionRepliedUser(false).queue();
                break;
            }
        }
    }
}
