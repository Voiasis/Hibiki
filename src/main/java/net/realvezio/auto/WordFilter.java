package net.realvezio.auto;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import net.dv8tion.jda.api.entities.Message;
import net.realvezio.tools.BotLog;
import net.realvezio.tools.WebhookController;

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

        if (msgSpaced.contains(" ur ")) {
            message.delete().queue();
            try {
                List<RuleMatch> matches;
                JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
                matches = langTool.check(msgContent);
                for (RuleMatch match : matches) {
                    String error = msgContent.substring(match.getFromPos(), match.getToPos());
                    String replace = match.getSuggestedReplacements().get(0);
                    WebhookController.send(message, message.getAuthor().getName(), message.getAuthor().getAvatarUrl(), msgContent.replace(error, replace));
                }
            } catch (Exception e) {
                BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "Grammar", 4);
            }
        }
    }
}
