package net.voiasis.auto;

import net.dv8tion.jda.api.entities.Message;

public class UpdatePing {
    public static void ping(Message message) {
        if (!message.getAuthor().isBot()) {
            String id = message.getChannel().getId();
            switch (id) {
                case "996107057541415014" :
                message.reply("<@&996266715342184509> ||Don't want to be pinged? Remove your role in <#927707232093761616>||").mentionRepliedUser(false).queue();
                break;
                case "954491245575692458" :
                message.reply("<@&996263081149726750> ||Don't want to be pinged? Remove your role in <#927707232093761616>||").mentionRepliedUser(false).queue();
                break;
                case "954491288613425173" :
                message.reply("<@&996263318157279282> ||Don't want to be pinged? Remove your role in <#927707232093761616>||").mentionRepliedUser(false).queue();
                break;
            }
        }
    }
}
