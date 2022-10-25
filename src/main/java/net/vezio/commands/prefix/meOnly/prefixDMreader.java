package net.vezio.commands.prefix.meOnly;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.vezio.tools.BotLog;

public class prefixDMreader {
    public static void dms(Message message, String[] args) {
        if (message.getAuthor().getId().equals("835691330725347379")) {
            if (args[1].equalsIgnoreCase("list")) {
                int amount = message.getJDA().getPrivateChannels().size();
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.RED);
                embed.setTitle("DM Channel List");
                while (amount > 0) {
                    PrivateChannel channel = message.getJDA().getPrivateChannels().get(amount - 1);
                    int messages = channel.getIterableHistory().complete().size();
                    if (messages >= 99) {
                        embed.addField("Channel " + amount + " (" + channel.getUser().getId() + ")", channel.getUser().getAsTag() + " (99+ messages)", false);
                        amount--;
                    } else {
                        embed.addField("Channel " + amount + " (" + channel.getUser().getId() + ")", channel.getUser().getAsTag() + " (" + channel.getIterableHistory().complete().size() + " messages)", false);
                        amount--;
                    }
                }
                message.replyEmbeds(embed.build()).queue();
            }
            if (args[1].equalsIgnoreCase("count")) {
                message.reply(String.valueOf(message.getJDA().openPrivateChannelById(args[2]).complete().getIterableHistory().complete().size())).queue();
            }
            if (args[1].equalsIgnoreCase("read")) {
                PrivateChannel channel = message.getJDA().openPrivateChannelById(args[2]).complete();
                int msg = Integer.parseInt(args[3]);
                Message privateMessage = channel.getIterableHistory().complete().get(msg - 1);
                long createTime = privateMessage.getTimeCreated().toEpochSecond();
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.RED);
                embed.setAuthor(privateMessage.getAuthor().getName(), null, privateMessage.getAuthor().getAvatarUrl());
                embed.setDescription(privateMessage.getContentRaw());
                message.replyEmbeds(embed.build()).addContent("ID: " + channel.getUser().getId() + "\r\n<t:" + createTime + ">").queue();
            }
            if (args[1].equalsIgnoreCase("send")) {
                PrivateChannel channel = message.getJDA().openPrivateChannelById(args[2]).complete();
                String content = message.getContentRaw().substring(args[0].length() + args[1].length() + args[2].length() + 3);
                String lowerCase = content.toLowerCase();
                if (!lowerCase.endsWith(".mp4") && !lowerCase.endsWith(".gif") && !lowerCase.endsWith(".png") && !lowerCase.endsWith(".jpg") && !lowerCase.endsWith(".jpeg")) {
                    int count = content.length();
                    channel.sendTyping().queue();
                    while (count > 0) {
                        try {
                            Thread.sleep(50);
                            if (count == 1) {
                                channel.sendMessage(content).queue();
                                break;
                            } else {
                                count--;
                            }
                        } catch (InterruptedException e) {
                            BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "DMreader", 4);
                        }
                        
                    }
                } else {
                    int count = message.getAttachments().size();
                    while (count > 0) {
                        content = content + "\r\n" + message.getAttachments().get(count - 1).getUrl();
                        if (count == 1) {
                            channel.sendMessage(content).queue();
                            break;
                        } else {
                            count--;
                        }
                    }
                }
            }
        }
    } 
}
