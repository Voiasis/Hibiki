package net.realvezio.commands.prefix.moderation;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.realvezio.tools.BotLog;

public class prefixSlowmode {
    public static void slowmode(Message message, String[] args) {
        if (message.isFromGuild()) {
            if (message.getMember().hasPermission(Permission.MANAGE_CHANNEL)) {
                if (message.getContentRaw().toCharArray().length >= args[0].length() + 1) {
                    String msgc = args[1];
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setColor(Color.RED);
                    if (msgc.endsWith("s")) {
                        String msgt = msgc.replace("s", "");
                        int time = Integer.parseInt(msgt);
                        message.getChannel().asTextChannel().getManager().setSlowmode(time).queue(s -> {
                            try {
                                Thread.sleep(1000);
                                embed.addField("Slowmode Updated", "<:slowmode:994820838916370512> Channel slowmode set to " + message.getChannel().asTextChannel().getSlowmode() + " seconds.", false);
                                message.replyEmbeds(embed.build()).queue();
                            } catch (InterruptedException e) {
                                BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "Slowmode", 4);
                            }
                        });
                    } else if (msgc.endsWith("m")) {
                        String time = msgc.replace("m", "");
                        int timeSeconds = Integer.parseInt(time);
                        int minute = timeSeconds * 60;
                        message.getChannel().asTextChannel().getManager().setSlowmode(minute).queue(s -> {
                            try {
                                Thread.sleep(1000);
                                embed.addField("Slowmode Updated", "<:slowmode:994820838916370512> Channel slowmode set to " + message.getChannel().asTextChannel().getSlowmode() + " minutes.", false);
                                message.replyEmbeds(embed.build()).queue();
                            } catch (InterruptedException e) {
                                BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "Slowmode", 4);
                            }
                        });
                    } else if (msgc.endsWith("h")) {
                        String time = msgc.replace("h", "");
                        int timeSeconds = Integer.parseInt(time);
                        int hour = timeSeconds * 3600;
                        message.getChannel().asTextChannel().getManager().setSlowmode(hour).queue(s -> {
                            try {
                                Thread.sleep(1000);
                                embed.addField("Slowmode Updated", "<:slowmode:994820838916370512> Channel slowmode set to " + message.getChannel().asTextChannel().getSlowmode() + " hours.", false);
                                message.replyEmbeds(embed.build()).queue();
                            } catch (InterruptedException e) {
                                BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "Slowmode", 4);
                            }
                        });
                    }
                } 
            }
        }
    }
}
