package net.voiasis.commands.prefix.tools;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

public class prefixActivity {
    public static void activity(Member member, Message message, String[] args) {
        if (member.getId().equals("472899069136601099")) {
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
                    embed.addField("Activity Changed", "Activity has been set to \"Listening to " + msg + "\".", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                //} else if (args[1].equalsIgnoreCase("copy")) {
                    //String msg = event.getMember().getActivities().toString();
                    //message.reply(msg).queue();
                    //event.getJDA().getPresence().setActivity(Activity.listening(msg));
                }
                
            }
        }
    }
}
