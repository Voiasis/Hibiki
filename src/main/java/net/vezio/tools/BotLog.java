package net.vezio.tools;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.JDA;

public class BotLog {
    public static void delete() {
        File botLog = new File("BotLog.txt"); 
        if (botLog.exists() && !botLog.isDirectory()) {
            botLog.delete();
            log("Deleted the file \"" + botLog.getName() + "\" successful.", "BotLog", 1);
        } else {
            log("Failed to delete \"" + botLog.getName() + "\" because it doesn't exist.", "BotLog", 2);
        }
    }
    public static void log(String logMessage, String location, int status) {
        String GOWE = "";
        int m = 0;
        switch (status) { //credit to maxdendy13#6472
            case 1: 
            GOWE = "GOOD";
            m = 32;
            break;
            case 2: 
            GOWE = "OKAY";
            m = 37;
            break;
            case 3:
            GOWE = "WARN";
            m = 33;
            break;
            case 4:
            GOWE = "ERROR";
            m = 35;
            break;
        }
        console("\u001B[36m" + time() + "\u001B[" + m + "m[" + GOWE + "] \u001B[31m[" + location + "] \u001B[" + m + "m" + logMessage + "\u001B[37m");
        file(time() + "[" + GOWE + "] [" + location + "] " + logMessage);
    }
    private static void console(String logMessage) {
        System.out.println(logMessage);
    }
    private static void file(String logMessage) {
        String logTxt = logMessage + "\r\n";
        try {
            File botLog = new File("BotLog.txt");
            if (botLog.exists() && !botLog.isDirectory()) {
                Files.write(Paths.get("BotLog.txt"), logTxt.getBytes(), StandardOpenOption.APPEND);
            } else {
                botLog.createNewFile();
                log("File created: " + botLog.getName(), "BotLog", 1);
                file(logMessage);
            }
        } catch (IOException e) {
            System.out.println("\u001B[35mAn error has occurred.");
            e.printStackTrace();
        }
    }
    public static String clock() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");  
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    public static String date() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    private static String time() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        return "[" + dtf.format(now) + "] ";
    }
    public static String timeAdv() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE, LLL dd. yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now) + " PST";
    }
    public static String getStackTraceString(@Nullable Throwable tr, JDA jda) {
        jda.getGuildById("1010933101939130462").getThreadChannelById("1010947434173694055").sendMessage("``" + timeAdv() + "`` <@835691330725347379>, the server room is on :fire::bangbang:").queue();
        if (tr == null) {
            return "";
        }
        Throwable t = tr;
        while (t != null) {
            if (t instanceof UnknownHostException) {
                return "";
            }
            t = t.getCause();
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, false);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }
}