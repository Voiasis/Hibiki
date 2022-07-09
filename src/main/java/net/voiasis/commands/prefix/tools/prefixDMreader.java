package net.voiasis.commands.prefix.tools;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.voiasis.tools.BotLog;

public class prefixDMreader {
    public static void dms(Member member, Message message, String[] args) {
        if (member.getId().equals("472899069136601099")) {
            if (args[1].equalsIgnoreCase("list")) {
                /*int amount = member.getJDA().getPrivateChannels().size();
                BotLog.log(String.valueOf(amount), "DMreader", 3);
                //EmbedBuilder embed = new EmbedBuilder();
                //embed.setColor(Color.RED);
                //embed.setTitle("DM Channel List");
                while (amount > 0) {
                    PrivateChannel channel = member.getJDA().getPrivateChannels().get(amount);
                    //embed.addField("Channel " + amount + " (" + channel.getId() + ")", channel.getUser().getAsTag() + " (" + channel.getUser().getId() + ")", false);
                    message.reply(channel.getUser().getAsTag()).queue();
                    amount--;
                }
                //message.replyEmbeds(embed.build()).queue();*/
                message.reply(member.getJDA().getPrivateChannelById("957675828198658080").getUser().getAsTag()).queue();
            }
        }
    }
}
