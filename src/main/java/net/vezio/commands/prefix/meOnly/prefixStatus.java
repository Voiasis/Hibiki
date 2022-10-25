package net.vezio.commands.prefix.meOnly;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Message;

public class prefixStatus {
    public static void status(Message message, String[] args) {
        if (message.getAuthor().getId().equals("472899069136601099")) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            try {
                if (args[1].equalsIgnoreCase("DND")) {
                    message.getJDA().getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
                    embed.addField("Status Changed", "<:dnd:995012472954171402> Status has been set to \"Do Not Disturb\".", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else if (args[1].equalsIgnoreCase("IDLE")) {
                    message.getJDA().getPresence().setStatus(OnlineStatus.IDLE);
                    embed.addField("Status Changed", "<:idle:995012370411823104> Status has been set to \"Idle\".", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else if (args[1].equalsIgnoreCase("OFF")) {
                    message.getJDA().getPresence().setStatus(OnlineStatus.INVISIBLE);
                    embed.addField("Status Changed", "<:offline:995012909669285998> Status has been set to \"Offline\".", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else if (args[1].equalsIgnoreCase("ON")) {
                    message.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
                    embed.addField("Status Changed", "<:online:995013089613328434> Status has been set to \"Online\".", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                }
            } catch(Exception ex) {
            }   
        }
    }
}