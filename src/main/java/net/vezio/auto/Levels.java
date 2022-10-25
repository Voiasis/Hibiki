package net.vezio.auto;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import net.vezio.tools.BotLog;
import net.vezio.tools.SearchTools;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;

public class Levels {
    public static void levels(Message message) {
        /*if (message.isFromGuild()) {
            Member member = message.getMember();
            User user = message.getAuthor();
            if (message.getChannelType().name().contains("THREAD")) {
                ThreadChannel threadChannel = message.getChannel().asThreadChannel();
                if (message.isFromGuild() && !message.getAuthor().isBot()) {
                    List<Message> history = threadChannel.getIterableHistory().complete().stream().limit(10).filter(msg -> !msg.equals(message)).collect(Collectors.toList());
                    int spam = history.stream().filter(m1 -> m1.getAuthor().equals(user) && !message.getAuthor().isBot()).filter(m4 -> (
                        message.getTimeCreated().toEpochSecond() - m4.getTimeCreated().toEpochSecond()) < 10).collect(Collectors.toList()).size();
                    //BotLog.log(String.valueOf(spam), "test", 3);
                    if (spam > 2) {
                        //BotLog.log("too fast", "test", 3);  
                    } else {
                        thingsandstuff(message, member);
                    } 
                }
            } else {
                TextChannel textChannel = message.getChannel().asTextChannel();
                if (message.isFromGuild() && !message.getAuthor().isBot()) {
                    List<Message> history = textChannel.getIterableHistory().complete().stream().limit(10).filter(msg -> !msg.equals(message)).collect(Collectors.toList());
                    int spam = history.stream().filter(m1 -> m1.getAuthor().equals(user) && !message.getAuthor().isBot()).filter(m4 -> (
                        message.getTimeCreated().toEpochSecond() - m4.getTimeCreated().toEpochSecond()) < 10).collect(Collectors.toList()).size();
                    //BotLog.log(String.valueOf(spam), "test", 3);
                    if (spam > 2) {
                        //BotLog.log("too fast", "test", 3);  
                    } else {
                        thingsandstuff(message, member);
                    } 
                }
            }
        }*/
    }
    private static void thingsandstuff(Message message, Member member) {
        String userId = message.getAuthor().getId();
        try {
            String wholeFile = Files.readString(Path.of("Levels.txt"));
            if (SearchTools.booleanRegexSearch(userId, wholeFile)) {
                int currentXp = Integer.parseInt(SearchTools.lineSearch(userId, "Levels.txt").replace(userId + "=", ""));
                //BotLog.log(String.valueOf(currentXp), "test", 3);
                int newXp = currentXp + nitroCheck(message);
                //BotLog.log(String.valueOf(newXp), "test", 3);
                String fileText = userId + "=" + newXp;
                BufferedReader file = new BufferedReader(new FileReader("Levels.txt"));
                StringBuffer inputBuffer = new StringBuffer();
                String line;
                while ((line = file.readLine()) != null) {
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
                file.close();
                String inputStr = inputBuffer.toString();
                inputStr = inputStr.replace(userId + "=" + currentXp, fileText + "\n");
                FileOutputStream fileOut = new FileOutputStream("Levels.txt");
                fileOut.write(inputStr.getBytes());
                fileOut.close();
                int oldLevel = xpToLevels(currentXp);
                //BotLog.log(String.valueOf(oldLevel), "test", 3);
                int newLevel = xpToLevels(newXp);
                //BotLog.log(String.valueOf(newLevel), "test", 3);
                if (newLevel > oldLevel) {
                    TextChannel channel = message.getJDA().getGuildById("1010933101939130462").getTextChannelById("1013943702730833920");
                    channel.sendMessage(member.getAsMention() + " has just leveled up to lvl " + newLevel + "!").queue();
                    if (newLevel == 5) {
                        Role role = member.getGuild().getRoleById("1010933101939130468");
                        UserSnowflake userSF = User.fromId(member.getUser().getId());
                        member.getGuild().addRoleToMember(userSF, role).queue();
                    }
                }
            } else {
                String fileText = userId + "=" + nitroCheck(message);
                Files.write(Paths.get("Levels.txt"), fileText.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "Levels", 4);
        }
    }
    public static boolean boosting(Member member) {
        if (String.valueOf(member.getRoles()).contains("1011205459455250514")) {
            return true;
        } else {
            return false;
        }
    }
    public static long xpToNextLevel(int level) {
        return 5 * (((long) Math.pow(level, 2)) + 10 * level + 20);
    }

    private static long levelsToXp(int levels) {
        long xp = 0;

        for (int level = 0; level <= levels; level++) {
            xp += xpToNextLevel(level);
        }

        return xp;
    }

    public static int xpToLevels(long totalXp) {
        boolean calculating = true;
        int level = 0;

        while (calculating) {
            long xp = levelsToXp(level);

            if (totalXp < xp) {
                calculating = false;
            } else {
                level++;
            }
        }

        return level;
    }

    public static long remainingXp(long totalXp) {
        int level = xpToLevels(totalXp);

        if (level == 0) return totalXp;

        long xp = levelsToXp(level);

        return totalXp - xp + xpToNextLevel(level);
    }

    public static int randomXp(int min, int max) {
        Random random = new Random();

        return random.nextInt((max - min) + 1) + min;
    }

    private static int nitroCheck(Message message) {
        Member member = message.getMember();
        if (boosting(member)) {
            return randomXp(15, 20);
        } else {
            return randomXp(5, 10);
        }
    }
}
