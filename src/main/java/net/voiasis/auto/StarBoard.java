package net.voiasis.auto;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.requests.ErrorResponse;
import net.voiasis.tools.BotLog;
import net.voiasis.tools.SearchTools;

public class StarBoard {
    public static void system(MessageReactionAddEvent event) {
        //BotLog.log("system", "Starboard", 2);
        //if (event.getGuild().getId().equals("902397621015040020")) { //lock for testing ⭐ DateTimeFormatter.ofPattern("MM/dd/yyyy"); 
            //event.getChannel().sendMessage(event.retrieveMessage().complete().getTimeCreated().format(DateTimeFormatter.ofPattern("yyyyMMdd"))).queue();
        if (event.getEmoji().toString().equals("UnicodeEmoji(U+2b50)")) {
            try {
                if (starboardMessageId(event.retrieveMessage().complete().getId()) == null) {
                    if (starableMessage(event)) {
                        newStarBoard(event);
                    }
                } else {
                    Message message = event.retrieveMessage().complete();
                    int stars = message.getReaction(Emoji.fromUnicode("\u2B50")).getCount();
                    if (starboardMessageId(event.getMessageId()) != event.getMessageId() &&
                    currentStars(event.getMessageId(), event.getGuild()) != stars) {
                        String channelMention = event.getChannel().getAsMention();
                        Guild guild = event.getGuild();
                        editStarboardCount(message.getId(), stars, channelMention, guild);
                    } 
                }
            } catch (Exception e) {
                BotLog.log(BotLog.getStackTraceString(e, event.getJDA()), "StarBoard", 4);
            } 
        }
        //}
    }
    private static void newStarBoard(MessageReactionAddEvent event) {
        Message message = event.retrieveMessage().complete();
        int stars = message.getReaction(Emoji.fromUnicode("\u2B50")).getCount();
        String channelMention = event.getChannel().getAsMention();
        String authorUrl = message.getAuthor().getAvatarUrl();
        String authorName = message.getAuthor().getName();
        String messageText = message.getContentRaw();
        String messageUrl = message.getJumpUrl();
        String messageId = message.getId();
        String date = BotLog.date();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.YELLOW);
        embed.setAuthor(authorName, null, authorUrl);
        embed.setDescription(messageText);
        embed.addField("Source", "[Jump!](" + messageUrl + ")", false);
        embed.setFooter(messageId + " • " + date);
        if (!message.getAttachments().isEmpty()) {
            String imageUrl = message.getAttachments().get(0).getUrl();
            embed.setImage(imageUrl);
        event.getGuild().getTextChannelsByName("starboard", true).get(0)
        .sendMessageEmbeds(embed.build()).content(replyStar(stars) + " " + stars + " " + channelMention).queue(m -> {
            BotLog.log("New starboard added.", "StarBoard", 1);
            try {
                starLogAdd(messageId, m.getId());
            } catch (IOException e) {
                BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "StarBoard", 4);
            }
        });
        } else {
            event.getGuild().getTextChannelsByName("starboard", true).get(0)
            .sendMessageEmbeds(embed.build()).content(replyStar(stars) + " " + stars + " " + channelMention).queue(m -> {
                try {
                    starLogAdd(messageId, m.getId());
                } catch (IOException e) {
                    BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "StarBoard", 4);
                }
            }); 
        }
    }
    private static boolean starableMessage(MessageReactionAddEvent event) {
        //BotLog.log("starableMessage", "StarBoard", 2);
        int time = Integer.parseInt(event.retrieveMessage().complete().getTimeCreated().format(DateTimeFormatter.ofPattern("yyyyMMdd")).toString());
        if (!event.retrieveMessage().complete().getContentRaw().isBlank() &&
        !event.getChannel().getName().equals("starboard") &&
        event.retrieveMessage().complete().getReaction(Emoji.fromUnicode("\u2B50")).getCount() >= requiredStarAmount() &&
        !event.retrieveMessage().complete().getTextChannel().isNSFW() && time >= 20220629) {
            return true;
        } else if (!event.retrieveMessage().complete().getAttachments().isEmpty() &&
        !event.getChannel().getName().equals("starboard") &&
        event.retrieveMessage().complete().getReaction(Emoji.fromUnicode("\u2B50")).getCount() >= requiredStarAmount() &&
        !event.retrieveMessage().complete().getTextChannel().isNSFW() && time >= 20220629) {
            return true;
        } else {
            return false;
        }
    }
    private static int currentStars(String messageId, Guild guild) throws IOException {
        //BotLog.log("currentStars", "StarBoard", 2);
        TextChannel starboardChannel = guild.getTextChannelsByName("starboard", true).get(0);
        Message message = starboardChannel.retrieveMessageById(starboardMessageId(messageId)).complete();
        String msg = message.getContentRaw();
        String mention = message.getMentions().getChannels().get(0).getAsMention();
        String number = msg.replace("⭐", "").replace("🌟", "").replace("💫", "")
        .replace(mention, "").replaceAll(" ", "");
        int stars = Integer.parseInt(number);
        return stars;
    }
    private static void editStarboardCount(String reactedMessageId, int stars, String channelMention, Guild guild) throws IOException {
        TextChannel starboardChannel = guild.getTextChannelsByName("starboard", true).get(0);
        starboardChannel.retrieveMessageById(starboardMessageId(reactedMessageId)).queue((message) -> {
            try {
                if (!starboardMessageId(reactedMessageId).contains("=")) {
                    message.editMessage(replyStar(stars) + " " + stars + " " + channelMention).queue(m -> {
                        BotLog.log("Starboard edited.", "StarBoard", 1);
                    });
                }
            } catch (IOException e) {
                BotLog.log(BotLog.getStackTraceString(e, guild.getJDA()), "StarBoard", 4);
            }
        }, (failure) -> {
            if (failure instanceof ErrorResponseException) {
                ErrorResponseException ex = (ErrorResponseException) failure;
                if (ex.getErrorResponse() == ErrorResponse.UNKNOWN_MESSAGE) {
                    try {
                        starLogRemove(reactedMessageId);
                    } catch (IOException e) {
                        BotLog.log(BotLog.getStackTraceString(ex, guild.getJDA()), "StarBoard", 4);
                    }
                }
            }
        });
    }
    private static String replyStar(int starCount) {
        //BotLog.log("replyStar", "StarBoard", 2);
        String star;
        if (starCount >= 15) {
            return star = "💫";
        } else if (starCount >= 10) {
            return star = "🌟";
        } else {
            return star = "⭐";
        }
    }
    private static int requiredStarAmount() {
        //BotLog.log("requiredStarAmount", "StarBoard", 2);
        return 5;
    }
    private static String starboardMessageId(String reactedMessageId) throws IOException {
        //BotLog.log("starboardMessageId", "StarBoard", 2);
        String messageIdLine = SearchTools.lineSearch(reactedMessageId + "=", "StarBoard.txt");
        if (messageIdLine.equals("?")) {
            return null;
        } else {
            String replace = messageIdLine.replace(reactedMessageId, "").replace("=", "");
            BotLog.log("starboardMessageId: " + replace, "Starboard", 2);
            return replace;
        }
    }
    private static void starLogAdd(String reactedMessageId, String botMessageId) throws IOException {
        //BotLog.log("starLogAdd", "StarBoard", 2);
        String logTxt = reactedMessageId + "=" + botMessageId + "\r\n";
        File starLog = new File("StarBoard.txt");
        if (starLog.exists() && !starLog.isDirectory()) {
            Files.write(Paths.get("StarBoard.txt"), logTxt.getBytes(), StandardOpenOption.APPEND);
        } else {
            starLog.createNewFile();
            BotLog.log("File created: " + starLog.getName(), "StarBoard", 1);
            starLogAdd(reactedMessageId, botMessageId);
        }
    }
    private static void starLogRemove(String reactedMessageId) throws IOException {
        //BotLog.log("starLogRemove", "StarBoard", 2);
        BufferedReader file = new BufferedReader(new FileReader("StarBoard.txt"));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        while ((line = file.readLine()) != null) {
            inputBuffer.append(line);
            inputBuffer.append('\n');
        }
        file.close();
        String inputStr = inputBuffer.toString();
        inputStr = inputStr.replace(SearchTools.lineSearch(reactedMessageId + "=", "StarBoard.txt"), "");
        FileOutputStream fileOut = new FileOutputStream("StarBoard.txt");
        fileOut.write(inputStr.getBytes());
        fileOut.close();
        BotLog.log(line + " removed.", "StarBoard", 3);
    }
}
