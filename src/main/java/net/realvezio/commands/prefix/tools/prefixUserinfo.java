package net.realvezio.commands.prefix.tools;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.joda.time.Days;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.realvezio.auto.Grammar;
import net.realvezio.tools.BotLog;

public class prefixUserinfo {
    public static void userinfo(Message message, String[] args) {
        if (message.getMentions().getUsers().toArray().length == 1) {
            send(message, message.getMentions().getUsers().get(0));
        } else if (message.getContentRaw().length() > args[0].length()) {
            try {
                User user = message.getJDA().retrieveUserById(args[1]).complete();
                send(message, user);
            } catch (Exception e) {
                BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "test", 4);
                message.reply("Invaild user ID provided or can't find that user.").mentionRepliedUser(false).queue();
            }
        } else {
            send(message, message.getAuthor());
        }
    }
    private static void send(Message message, User user) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime jdTime = user.getTimeCreated().toLocalDateTime();
        long jdDiff = ChronoUnit.DAYS.between(jdTime, now);
        long createTime = user.getTimeCreated().toEpochSecond();
        embed.setTitle(user.getAsTag());
        embed.addField("User ID", "<:idblock:1010954027120480307> " + user.getId(), false);
        embed.addField("Joined Discord", "<:discord:1010954055067127978> " + "<t:" + createTime + ":d> <t:" + createTime + ":t> (" + jdDiff + " " + Grammar.grammar("day", jdDiff) + " ago)", false);
        if (user.getAvatarUrl() != null) {
            embed.setThumbnail(user.getAvatarUrl() + "?size=512");
            if (message.isFromGuild()) {
                if (message.getGuild().getMember(user) != null) {
                    LocalDateTime jsTime = message.getMember().getTimeJoined().toLocalDateTime();
                    long jsDiff = ChronoUnit.DAYS.between(jsTime, now);
                    long joinTime = message.getMember().getTimeJoined().toEpochSecond();
                    embed.addField("Joined Server", "<:time:1010953759175749683> " + "<t:" + joinTime + ":d> <t:" + joinTime + ":t> (" + jsDiff + " " + Grammar.grammar("day", jsDiff) + " ago)", false);
                    if (user.retrieveProfile().complete().getBanner() != null) {
                        embed.setImage(user.retrieveProfile().complete().getBannerUrl() + "?size=512");
                        embed.setDescription("<:mention:1010953969155178509> " + user.getAsMention());
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    } else {
                        embed.setDescription("<:mention:1010953969155178509> " + user.getAsMention());
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    }
                } else {
                    if (user.retrieveProfile().complete().getBanner() != null) {
                        embed.setImage(user.retrieveProfile().complete().getBannerUrl() + "?size=512");
                        embed.setDescription("<:mention:1010953969155178509> " + user.getAsMention());
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    } else {
                        embed.setDescription("<:mention:1010953969155178509> " + user.getAsMention());
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    }
                }
            } else {
                if (user.retrieveProfile().complete().getBanner() != null) {
                    embed.setImage(user.retrieveProfile().complete().getBannerUrl() + "?size=512");
                    embed.setDescription("<:mention:1010953969155178509> " + user.getAsMention());
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else {
                    embed.setDescription("<:mention:1010953969155178509> " + user.getAsMention());
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                }
            }
        } else {
            embed.setThumbnail(user.getDefaultAvatar().getUrl());
            if (message.isFromGuild()) {
                if (message.getGuild().getMember(user) != null) {
                    LocalDateTime jsTime = message.getMember().getTimeJoined().toLocalDateTime();
                    long jsDiff = ChronoUnit.DAYS.between(jsTime, now);
                    long joinTime = message.getMember().getTimeJoined().toEpochSecond();
                    embed.addField("Joined Server", "<:time:1010953759175749683> " + "<t:" + joinTime + ":d> <t:" + joinTime + ":t> (" + jsDiff + " " + Grammar.grammar("day", jsDiff) + " ago)", false);
                    if (user.retrieveProfile().complete().getBanner() != null) {
                        embed.setImage(user.retrieveProfile().complete().getBannerUrl() + "?size=512");
                        embed.setDescription("<:mention:1010953969155178509> " + user.getAsMention());
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    } else {
                        embed.setDescription("<:mention:1010953969155178509> " + user.getAsMention());
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    }
                } else {
                    if (user.retrieveProfile().complete().getBanner() != null) {
                        embed.setImage(user.retrieveProfile().complete().getBannerUrl() + "?size=512");
                        embed.setDescription("<:mention:1010953969155178509> " + user.getAsMention());
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    } else {
                        embed.setDescription("<:mention:1010953969155178509> " + user.getAsMention());
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    }
                }
            } else {
                if (user.retrieveProfile().complete().getBanner() != null) {
                    embed.setImage(user.retrieveProfile().complete().getBannerUrl() + "?size=512");
                    embed.setDescription("<:mention:1010953969155178509> " + user.getAsMention());
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else {
                    embed.setDescription("<:mention:1010953969155178509> " + user.getAsMention());
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                }
            }
        }
    }
}
