package net.voiasis.commands.prefix.tools;

import java.awt.Color;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

public class prefixUptime {
    public static void uptime(Message message){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        long ut = rb.getUptime();
        long second = (ut / 1000) % 60;
        long minute = (ut / (1000 * 60)) % 60;
        long hour = (ut / (1000 * 60 * 60)) % 24;
        long day = (ut / (1000 * 60 * 60 * 60)) % 24;
        String uptime = day + " Days, " + hour + " Hours, " + minute + " Minutes, and " + second + " Seconds. ";
        embed.addField("Ellie's Uptime", "<:settings:994843742567673896> " + uptime, false);
        message.replyEmbeds(embed.build()).queue();
    }
}
