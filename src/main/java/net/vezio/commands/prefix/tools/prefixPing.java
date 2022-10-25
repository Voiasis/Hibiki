package net.vezio.commands.prefix.tools;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.vezio.tools.BotLog;

public class prefixPing {
    public static void pingCreate(Message message) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.addField("Pong!", "Reply Speed: <a:typing:995510544813543545>ms\r\nGateway Ping: <a:typing:995510544813543545>ms\r\nInternet Ping: <a:typing:995510544813543545>ms", false);
        message.replyEmbeds(embed.build()).mentionRepliedUser(false).addContent("Collecting Infomation.. (Reply Speed)").queue(msg1 -> {
            embed.clear();
            embed.setColor(Color.RED);
            long reply = message.getTimeCreated().until(msg1.getTimeCreated(), ChronoUnit.MILLIS);
            embed.addField("Pong!", "Reply Speed: " + reply + "ms\r\nGateway Ping: <a:typing:995510544813543545>ms\r\nInternet Ping: <a:typing:995510544813543545>ms", false);
            msg1.editMessage("Collecting Infomation.. (Gateway Ping)").setEmbeds(embed.build()).queue(msg2 -> {
            //msg1.editMessageEmbeds(embed.build()).mentionRepliedUser(false).content("Collecting Infomation.. (Gateway Ping)").queue(msg2 -> {
                embed.clear();
                embed.setColor(Color.RED);
                long gwp = message.getJDA().getGatewayPing();
                embed.addField("Pong! Pong!", "Reply Speed: " + reply + "ms\r\nGateway Ping: " + gwp + "ms\r\nInternet Ping: <a:typing:995510544813543545>ms", false);
                msg2.editMessage("Collecting Infomation.. (Internet Ping)").setEmbeds(embed.build()).queue(msg3 -> {
                //msg2.editMessageEmbeds(embed.build()).mentionRepliedUser(false).content("Collecting Infomation.. (Internet Ping)").queue(msg3 -> {
                    try {
                        embed.clear();
                        embed.setColor(Color.RED);
                        int ping = (int)checkPing("1.1.1.1");
                        BotLog.log(String.valueOf(ping), "test", 3);
                        embed.addField("Pong! Pong! Pong!", "Reply Speed: " + reply + "ms\r\nGateway Ping: " + gwp + "ms\r\nInternet Ping: " + ping + "ms", false);
                        msg3.editMessage("Done!").setEmbeds(embed.build()).queue();
                        //msg3.editMessageEmbeds(embed.build()).mentionRepliedUser(false).content("Done!").queue();
                    } catch (IOException e) {
                        BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "Ping", 4);
                    }
                });
            });
        });
    }
    public static void pingEdit(Message message) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.addField("Pong!", "Reply Speed: <a:typing:995510544813543545>ms\r\nGateway Ping: <a:typing:995510544813543545>ms\r\nInternet Ping: <a:typing:995510544813543545>ms", false);
        message.replyEmbeds(embed.build()).mentionRepliedUser(false).addContent("Collecting Infomation.. (Reply Speed)").queue(msg1 -> {
            embed.clear();
            embed.setColor(Color.RED);
            long reply = message.getTimeEdited().until(msg1.getTimeCreated(), ChronoUnit.MILLIS);
            embed.addField("Pong!", "Reply Speed: " + reply + "ms\r\nGateway Ping: <a:typing:995510544813543545>ms\r\nInternet Ping: <a:typing:995510544813543545>ms", false);
            msg1.editMessage("Collecting Infomation.. (Gateway Ping)").setEmbeds(embed.build()).queue(msg2 -> {
            //msg1.editMessageEmbeds(embed.build()).mentionRepliedUser(false).content("Collecting Infomation.. (Gateway Ping)").queue(msg2 -> {
                embed.clear();
                embed.setColor(Color.RED);
                long gwp = message.getJDA().getGatewayPing();
                embed.addField("Pong! Pong!", "Reply Speed: " + reply + "ms\r\nGateway Ping: " + gwp + "ms\r\nInternet Ping: <a:typing:995510544813543545>ms", false);
                msg2.editMessage("Collecting Infomation.. (Internet Ping)").setEmbeds(embed.build()).queue(msg3 -> {
                //msg2.editMessageEmbeds(embed.build()).mentionRepliedUser(false).content("Collecting Infomation.. (Internet Ping)").queue(msg3 -> {
                    try {
                        embed.clear();
                        embed.setColor(Color.RED);
                        int ping = (int)checkPing("1.1.1.1");
                        BotLog.log(String.valueOf(ping), "test", 3);
                        embed.addField("Pong! Pong! Pong!", "Reply Speed: " + reply + "ms\r\nGateway Ping: " + gwp + "ms\r\nInternet Ping: " + ping + "ms", false);
                        msg3.editMessage("Done!").setEmbeds(embed.build()).queue();
                        //msg3.editMessageEmbeds(embed.build()).mentionRepliedUser(false).content("Done!").queue();
                    } catch (IOException e) {
                        BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "Ping", 4);
                    }
                });
            });
        });
    }
    private static double checkPing(String hostName) throws IOException {
        BotLog.log(String.valueOf(hostName), "test", 3);
        String[] command = { "cmd.exe", "cd C:\\Windows\\system32", "/C", "ping " + hostName };
        Process p = Runtime.getRuntime().exec(command);
        BufferedReader buff = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String readline;
        double ping = 0;
        List<Integer> intList = new ArrayList<Integer>();
        ArrayList<String> StringAr = new ArrayList<String>();
        while ((readline = buff.readLine()) != null) {
            if (readline.contains("Reply")) {
                StringAr.add(readline.substring(readline.indexOf("time=") + 5, readline.indexOf("ms")));
                intList.add(Integer.valueOf(readline.substring(readline.indexOf("time=") + 5, readline.indexOf("ms"))));
            }
        }
        int[] intAr = intList.stream().mapToInt(Integer::intValue).toArray();
        BotLog.log(String.valueOf(intAr), "test", 3);
        ping = average(intAr);
        BotLog.log(String.valueOf(ping), "test", 3);
        return ping;  
    }
    private static double average(int[] array) {
        int length = array.length;
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        if (sum != 0 && length != 0) {
            double average = sum / length;
            return average;
        } else {
            double average = -1;
            return average;
        }
        
    } 
}
