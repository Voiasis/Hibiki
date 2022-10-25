package net.vezio.commands.prefix.meOnly;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;

public class prefixActivity {
    public static void activity(Message message, String[] args) {
        if (message.getAuthor().getId().equals("472899069136601099")) {
            if (message.getContentRaw().toCharArray().length >= args[0].length() + 3) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.RED);
                if (args[1].equalsIgnoreCase("p")) {
                    String msg = message.getContentRaw().substring(args[0].length() + 3);
                    message.getJDA().getPresence().setActivity(Activity.playing(msg));
                    embed.addField("Activity Changed", "Activity has been set to \"Playing " + msg + "\".", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else if (args[1].equalsIgnoreCase("w")) {
                    String msg = message.getContentRaw().substring(args[0].length() + 3);
                    message.getJDA().getPresence().setActivity(Activity.watching(msg));
                    embed.addField("Activity Changed", "Activity has been set to \"Watching " + msg + "\".", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else if (args[1].equalsIgnoreCase("l")) {
                    String msg = message.getContentRaw().substring(args[0].length() + 3);
                    message.getJDA().getPresence().setActivity(Activity.listening(msg));
                    embed.addField("Activity Changed", "Activity has been set to \"Listening " + msg + "\".", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else if (args[1].equalsIgnoreCase("c")) {
                    String msg = message.getContentRaw().substring(args[0].length() + 3);
                    message.getJDA().getPresence().setActivity(Activity.competing(msg));
                    embed.addField("Activity Changed", "Activity has been set to \"Competing in " + msg + "\".", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else if (args[1].equalsIgnoreCase("s")) {
                    String msg = message.getContentRaw().substring(args[0].length() + args[2].length() + 4);
                    message.getJDA().getPresence().setActivity(Activity.streaming(msg, args[2]));
                    embed.addField("Activity Changed", "Activity has been set to \"Streaming " + msg + "\".", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                }
                
            }
        }
    }
}
