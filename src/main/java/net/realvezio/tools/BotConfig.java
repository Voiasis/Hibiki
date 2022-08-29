package net.realvezio.tools;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;

public class BotConfig {
    private static final Dotenv dotenv = Dotenv.load();
    public static String get(String key) {
        //BotLog.log("Config loaded: " + key, "BotConfig", 1);
        return dotenv.get(key);
    }
    public static boolean botConfig(String configOption) throws IOException {
        if (SearchTools.booleanRegexSearch(configOption + "1", SearchTools.lineSearch(configOption, "BotConfig.txt"))) {
            return true;
        } else {
            return false;
        }
    }
    public static void config(Message message, String[] args) {
        if (message.getAuthor().getId().equals("835691330725347379") && !args[1].isBlank()) {  
            try {
                BufferedReader file = new BufferedReader(new FileReader("BotConfig.txt"));
                StringBuffer inputBuffer = new StringBuffer();
                String line;
                while ((line = file.readLine()) != null) {
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }//➡️
                file.close();
                String inputStr = inputBuffer.toString();
                if (SearchTools.booleanRegexSearch(args[1] + "0", inputStr)) {
                    inputStr = inputStr.replace(args[1] + "0", args[1] + "1");
                    BotLog.log("Config updated.", "BotCommands", 1);
                    message.addReaction(message.getJDA().getEmojiById("956621588084695120")).queue(m1 -> 
                    message.addReaction(Emoji.fromUnicode("\u27A1\uFE0F")).queue(m2 ->
                    message.addReaction(message.getJDA().getEmojiById("956621420560011354")).queue(m3 -> {
                        if (message.getGuild().getMemberById("957675828198658080").hasPermission(Permission.MESSAGE_MANAGE)) {
                            message.delete().queueAfter(3, TimeUnit.SECONDS);
                        }})));
                } else if (SearchTools.booleanRegexSearch(args[1] + "1", inputStr)) {
                    inputStr = inputStr.replace(args[1] + "1", args[1] + "0");
                    BotLog.log("Config updated.", "BotCommands", 1);
                    message.addReaction(message.getJDA().getEmojiById("956621420560011354")).queue(m1 -> 
                    message.addReaction(Emoji.fromUnicode("\u27A1\uFE0F")).queue(m2 ->
                    message.addReaction(message.getJDA().getEmojiById("956621588084695120")).queue(m3 -> {
                        if (message.getGuild().getMemberById("957675828198658080").hasPermission(Permission.MESSAGE_MANAGE)) {
                            message.delete().queueAfter(3, TimeUnit.SECONDS);
                        }})));
                }
                FileOutputStream fileOut = new FileOutputStream("BotConfig.txt");
                fileOut.write(inputStr.getBytes());
                fileOut.close();
            } catch (Exception e) {
                BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "BotCommands", 4);
            }
        }
    }
}
