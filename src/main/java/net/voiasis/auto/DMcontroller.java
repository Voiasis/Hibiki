package net.voiasis.auto;

import java.awt.Color;
import java.io.IOException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.voiasis.tools.BotLog;
import net.voiasis.tools.SearchTools;

public class DMcontroller {
    public static void controller(Message message) {
        if (message.isFromType(ChannelType.PRIVATE)) {
            if (!message.getAuthor().getId().equals("957675828198658080")) {
                if (message.getAuthor().getId().equals("472899069136601099")) {
                    if (message.getMessageReference() != null) {
                        Message repliedMessage = message.getMessageReference().getMessage();
                        String content = message.getContentRaw();
                        if (repliedMessage.getAuthor().getId().equals("957675828198658080")) {
                            try {
                                String userID = SearchTools.lineSearch3("ID: ", repliedMessage.getContentRaw()).replace("ID: ", "");
                                PrivateChannel channel = message.getJDA().openPrivateChannelById(userID).complete();
                                String lowerCase = content.toLowerCase();
                                if (!lowerCase.endsWith(".mp4") && !lowerCase.endsWith(".gif") && !lowerCase.endsWith(".png") && !lowerCase.endsWith(".jpg") && !lowerCase.endsWith(".jpeg")) {
                                    int count = content.length();
                                    channel.sendTyping().queue();
                                    while (count > 0) {
                                        Thread.sleep(50);
                                        if (count == 1) {
                                            channel.sendMessage(content).queue();
                                            break;
                                        } else {
                                            count--;
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
                            } catch (IOException | InterruptedException e) {
                                BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "DMcontroller", 4);
                            }
                        }
                    }
                } else {
                    String content = message.getContentRaw();
                    User author = message.getAuthor();
                    PrivateChannel notificationChannel = message.getJDA().openPrivateChannelById("472899069136601099").complete();
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setColor(Color.RED);
                    if (message.getMessageReference() != null) {
                        Message repliedMessage = message.getReferencedMessage();
                        embed.setAuthor(repliedMessage.getAuthor().getAsTag(), null, repliedMessage.getAuthor().getAvatarUrl());
                        embed.setDescription(repliedMessage.getContentRaw());
                        notificationChannel.sendMessageEmbeds(embed.build()).content("ID: " + author.getId()).queue(m1 -> {
                            embed.clear();
                            embed.setColor(Color.RED);
                            embed.setAuthor(author.getAsTag(), null, author.getAvatarUrl());
                            embed.setDescription(content);
                            m1.replyEmbeds(embed.build()).content("ID: " + author.getId()).queue(m2 -> {
                                attachments(message, m2);
                            });
                        });
                    } else {
                        embed.setAuthor(author.getAsTag(), null, author.getAvatarUrl());
                        embed.setDescription(content);
                        notificationChannel.sendMessageEmbeds(embed.build()).content("ID: " + author.getId()).queue(m2 -> {
                            attachments(message, m2);
                        });
                    }
                }
            } 
        }
    }
    private static void attachments(Message message, Message reply) {
        int amount = message.getAttachments().size();
        while (amount > 0) {
            reply.reply(message.getAttachments().get(amount - 1).getUrl()).queue();
            amount--;
        }
    }
}
