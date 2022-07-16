package net.voiasis.auto;

import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.Message;

public class WordFilter {
    public static void filter(Message message) {
        String msgContent = message.getContentRaw();
        String msgLower = msgContent.toLowerCase();
        String msgSpaced = " " + msgLower + " ";
        String msgFrontSpaced = " " + msgLower;
        String msgRearSpaced = msgLower + " ";

        if (msgRearSpaced.contains("retard ")) {
            message.delete().queue();
            message.getMember().timeoutFor(10, TimeUnit.MINUTES).queue();
        }
        if (msgLower.contains("retarded")) {
            message.delete().queue();
            message.getMember().timeoutFor(10, TimeUnit.MINUTES).queue();
        }
        if (msgRearSpaced.contains("nigga ")) {
            message.delete().queue();
            message.getMember().timeoutFor(1, TimeUnit.DAYS).queue();
        }
        if (msgLower.contains("nigge")) {
            message.delete().queue();
            message.getMember().timeoutFor(1, TimeUnit.DAYS).queue();
        }
        if (msgContent.contains("discord.gg/")) {
            message.delete().queue();
            message.getMember().timeoutFor(10, TimeUnit.MINUTES).queue();
        }
    }
}
