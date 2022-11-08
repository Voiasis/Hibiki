package net.voiasis.commands.prefix.tools;

import java.awt.Color;
import java.io.IOException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.voiasis.auto.Levels;
import net.voiasis.tools.BotLog;
import net.voiasis.tools.SearchTools;

public class prefixLevel {
    public static void level(Message message, String[] args) {
        if (message.getMentions().getUsers().toArray().length == 1) {
            idkwhattonamethissoitgetsareallylongstupidname(message, message.getMentions().getUsers().get(0));
        } else if (message.getContentRaw().length() > args[0].length()) {
            try {
                User user = message.getJDA().retrieveUserById(args[1]).complete();
                idkwhattonamethissoitgetsareallylongstupidname(message, user);
            } catch (Exception e) {
                message.reply("Invaild user ID provided or can't find that user.").mentionRepliedUser(false).queue();
            }
        } else {
            idkwhattonamethissoitgetsareallylongstupidname(message, message.getAuthor());
        }
    }
    private static void idkwhattonamethissoitgetsareallylongstupidname(Message message, User user) {
        if (!user.isBot()) {
            String userId = user.getId();
            Member member = message.getJDA().getGuildById("1010933101939130462").getMember(user);
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            embed.setThumbnail(user.getAvatarUrl());
            try {
                String xpLine = SearchTools.lineSearch(userId, "Levels.txt").replace(userId + "=", "");
                if (xpLine == "?") {
                    embed.setTitle(user.getName());
                    embed.setDescription("<:level:1013966274881142794> Level: 0\n<:time:1010953759175749683> Progress: 0/100\n<:boosts:1010954093826670727> XP Boost: " + Levels.boosting(member));
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else {
                    int currentXp = Integer.parseInt(xpLine);
                    embed.setTitle(user.getName());
                    embed.setDescription("<:level:1013966274881142794> Level: " + Levels.xpToLevels(currentXp) + "\n<:time:1010953759175749683> Progress: " + Levels.remainingXp(currentXp) + "/" + Levels.xpToNextLevel(Levels.xpToLevels(currentXp)) + "\n<:boosts:1010954093826670727> XP Boost: " + Levels.boosting(member));
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                }   
            } catch (NumberFormatException | IOException e) {
                BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "level", 4);
            }
        }
    }
}
