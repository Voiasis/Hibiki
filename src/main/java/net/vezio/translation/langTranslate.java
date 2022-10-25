package net.vezio.translation;

import java.awt.Color;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;

import de.linus.deepltranslator.DeepLConfiguration;
import de.linus.deepltranslator.DeepLTranslator;
import de.linus.deepltranslator.SourceLanguage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.vezio.tools.BotLog;

public class langTranslate {
    public static void translate(Message message, User requestUser, String langInput, String langOutput, boolean reactionEvent, int substringLenth) {
        message.getChannel().sendTyping().queue(); //change to loading message that gets edited 
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        //embed.setDescription("Message from " + message.getAuthor().getAsTag() + " (" + message.getAuthor().getId() + ")");
        embed.setFooter("Translation requested by " + requestUser.getAsTag() + " (" + requestUser.getId() + ")");
        String langTo = iSO6391CodeToLang.translator(emojiFlagToISO6391Code.translator(langOutput, reactionEvent));
        if (reactionEvent) {
            String embedTitle = "Auto -> " + langTo.substring(0, 1).toUpperCase() + langTo.substring(1) + "";
            String embedText = translator(message.getContentRaw(), langInput, langOutput, message.getJDA(), reactionEvent);
            embed.setTitle(embedTitle);
            embed.setDescription(embedText);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
        } else {
            String langFrom = iSO6391CodeToLang.translator(emojiFlagToISO6391Code.translator(langInput, reactionEvent));
            String embedTitle = langFrom.substring(0, 1).toUpperCase() + langFrom.substring(1)
            + " -> " + langTo.substring(0, 1).toUpperCase() + langTo.substring(1) + "";
            String embedText = translator(message.getContentRaw().substring(substringLenth), langInput, langOutput, message.getJDA(), reactionEvent);
            embed.setTitle(embedTitle);
            embed.setDescription(embedText);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
        }
    }
    private static String translator(String wordInput, String langInput, String langOutput, JDA jda, boolean reactionEvent) {
        DeepLConfiguration deepLConfiguration = new DeepLConfiguration.Builder()
        .setTimeout(Duration.ofSeconds(10))
        //.setRepetitions(10)
        //.setRepetitionsDelay(retryNumber -> Duration.ofMillis(3000 + 5000 * retryNumber))
        //.setPostProcessing(true)
        .build();
        DeepLTranslator deepLTranslator = new DeepLTranslator(deepLConfiguration);
        String translation =  sync(deepLTranslator, wordInput, langInput, langOutput, jda, reactionEvent);
        /*try {
            while (deepLTranslator.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)) {
                DeepLTranslator.shutdown();
                Runtime rt = Runtime.getRuntime();
                rt.exec("taskkill /F /IM chromedriver.exe");
            }
        } catch (InterruptedException | IOException e) {
            BotLog.log(BotLog.getStackTraceString(e, jda), "LangChange", 4);
        }*/
        return translation;
    }
    private static String sync(DeepLTranslator deepLTranslator, String wordInput, String langInput, String langOutput, JDA jda, boolean reactionEvent) {
        String translation;
        try {
            if (!reactionEvent) {
                translation = deepLTranslator.translate(wordInput,
                sourceLang.source(iSO6391CodeToLang.translator(emojiFlagToISO6391Code.translator(langInput, reactionEvent))),
                targetLang.target(iSO6391CodeToLang.translator(emojiFlagToISO6391Code.translator(langOutput, reactionEvent))));
                return translation;
            } else {
                translation = deepLTranslator.translate(wordInput,
                SourceLanguage.AUTO_DETECT, targetLang.target(iSO6391CodeToLang.translator(emojiFlagToISO6391Code.translator(langOutput, reactionEvent))));
                return translation;
            }
        } catch (Exception e) {
            translation = " Translation failed.";
            BotLog.log(BotLog.getStackTraceString(e, jda), "LangChange", 4);
            return translation;
        }
    }
    public static void prefixTranslate(Message message, String[] args) {
        if (message.getAuthor().getId().equals("835691330725347379")) {
            translate(message, message.getAuthor(), args[1], args[2], false, args[0].length() + args[1].length() + args[2].length() + 3);
        }
    }
    public static void reactTranslate(MessageReactionAddEvent event) {
        if (event.getUserId().equals("835691330725347379")) {
            if (StringUtils.countMatches(event.getEmoji().toString(), "U+1f") == 2 && event.getEmoji().toString().contains("UnicodeEmoji")
            && !event.retrieveMessage().complete().getContentRaw().isBlank()) {
                translate(event.retrieveMessage().complete(), event.getUser(), "auto", event.getEmoji().getName(), true, 0);
            }
        }
    }
}
