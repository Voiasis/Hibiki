package net.voiasis.auto;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.voiasis.tools.BotLog;
import net.voiasis.tools.SearchTools;

public class ServerLogs {
    //member-moderation

    //messages
    public static void messageCreated(MessageReceivedEvent event) {
        if (event.isFromGuild() && !event.getAuthor().isBot()) {
            String messageID = event.getMessageId();
            String authorID = event.getAuthor().getId();
            String contents = event.getMessage().getContentRaw().replace("\n", "[LINEBREAK-N]").replace("\r", "[LINEBREAK-R]");
            String fileText = messageID + "=" + authorID + "=" + contents + "\r\n";
            try {
                Files.write(Paths.get("ServerMessages.txt"), fileText.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                BotLog.log(BotLog.getStackTraceString(e, event.getJDA()), "ServerLogs", 4);
            }
        } 
    }
    public static void messageDeleted(MessageDeleteEvent event) {
        if (event.isFromGuild()) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            TextChannel logChannel = event.getJDA().getGuildById("902397621015040020").getTextChannelById("927700474986111016");
            Channel eventChannel = event.getChannel();
            String messageID = event.getMessageId();
            try {
                String logLine = SearchTools.lineSearch(messageID, "ServerMessages.txt");
                if (logLine.length() >= 39) {
                    int pos1 = 19;
                    int pos2 = 37;
                    String authorID = logLine.substring(pos1, pos2);
                    User eventUser = event.getGuild().getMemberById(authorID).getUser();
                    if (!eventUser.isBot()) {
                        int pos3 = 38;
                        String content = logLine.substring(pos3).replace("[LINEBREAK-N]", "\n").replace("[LINEBREAK-R]", "\r");
                        embed.setAuthor(eventUser.getAsTag(), eventUser.getAvatarUrl(), eventUser.getAvatarUrl());
                        embed.setDescription("**Message sent by " + eventUser.getAsMention() + " deleted in " + eventChannel.getAsMention() + "**");
                        embed.setFooter("Author: " + authorID + " | Message ID: " + messageID);
                        embed.addField(" ", content, false);
                        logChannel.sendMessageEmbeds(embed.build()).queue();
                        BufferedReader file = new BufferedReader(new FileReader("ServerMessages.txt"));
                        StringBuffer inputBuffer = new StringBuffer();
                        String line;
                        while ((line = file.readLine()) != null) {
                            inputBuffer.append(line);
                            inputBuffer.append('\n');
                        }
                        file.close();
                        String inputStr = inputBuffer.toString();
                        inputStr = inputStr.replace(logLine, "");
                        FileOutputStream fileOut = new FileOutputStream("ServerMessages.txt");
                        fileOut.write(inputStr.getBytes());
                        fileOut.close();
                    }
                }
            } catch (IOException e) {
                BotLog.log(BotLog.getStackTraceString(e, event.getJDA()), "ServerLogs", 4);
            }
        }
    }
    public static void messageEdited(MessageUpdateEvent event) {
        if (event.isFromGuild() && !event.getAuthor().isBot()) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            TextChannel logChannel = event.getJDA().getGuildById("902397621015040020").getTextChannelById("927700474986111016");
            Channel eventChannel = event.getChannel();
            Message eventMessage = event.getMessage();
            String messageID = event.getMessageId();
            User eventUser = event.getAuthor();
            embed.setAuthor(eventUser.getAsTag(), eventUser.getAvatarUrl(), eventUser.getAvatarUrl());
            embed.setDescription("**Message edited in " + eventChannel.getAsMention() + "** [Jump to Message](" + eventMessage.getJumpUrl() + ")");
            embed.setFooter("User ID: " + event.getAuthor().getId());
            try {
                String logLine = SearchTools.lineSearch(messageID, "ServerMessages.txt");
                if (logLine.length() >= 39) {
                    int pos3 = 38;
                    String content = logLine.substring(pos3).replace("[LINEBREAK-N]", "\n").replace("[LINEBREAK-R]", "\r");
                    embed.addField("Before", content, false);
                    embed.addField("After", eventMessage.getContentRaw(), false);
                    logChannel.sendMessageEmbeds(embed.build()).queue();
                    BufferedReader file = new BufferedReader(new FileReader("ServerMessages.txt"));
                    StringBuffer inputBuffer = new StringBuffer();
                    String line;
                    while ((line = file.readLine()) != null) {
                        inputBuffer.append(line);
                        inputBuffer.append('\n');
                    }
                    file.close();
                    String inputStr = inputBuffer.toString();
                    inputStr = inputStr.replace(logLine, "");
                    FileOutputStream fileOut = new FileOutputStream("ServerMessages.txt");
                    fileOut.write(inputStr.getBytes());
                    fileOut.close();
                    String fileText = messageID + "=" + event.getAuthor().getId() + "=" + eventMessage.getContentRaw().replace("\n", "[LINEBREAK-N]").replace("\r", "[LINEBREAK-R]") + "\r\n";
                    Files.write(Paths.get("ServerMessages.txt"), fileText.getBytes(), StandardOpenOption.APPEND);
                }
            } catch (IOException e) {
                BotLog.log(BotLog.getStackTraceString(e, event.getJDA()), "ServerLogs", 4);
            }
            
        }
    }
    public static void bulkDeleted(MessageBulkDeleteEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        TextChannel logChannel = event.getJDA().getGuildById("902397621015040020").getTextChannelById("927700474986111016");
        Channel eventChannel = event.getChannel();
        //TODO  
    }
    //server-changes

    //member changes

}
