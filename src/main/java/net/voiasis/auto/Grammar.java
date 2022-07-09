package net.voiasis.auto;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.voiasis.tools.BotLog;

public class Grammar {
    public static void check(Message message) {
        if (message.getChannel().getId().equals("995434413238259762")) {
            if (!message.getAuthor().isBot()) {
                try {
                    String content = message.getContentRaw();
                    List<RuleMatch> matches;
                    JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
                    matches = langTool.check(content);
                    int count = matches.size();
                    int count2 = matches.size();
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setColor(Color.RED);
                    embed.setTitle("Grammar Checker");
                    embed.setDescription(String.valueOf(count) + " potential grammar error(s) found.");
                    for (RuleMatch match : matches) {
                        String error = content.substring(match.getFromPos(), match.getToPos());
                        int count3 = count2 - count;
                        embed.addField(String.valueOf(count3 + 1) + ": \"" + error + "\" at position " + match.getFromPos() + "-" + match.getToPos(), match.getMessage().replace("<suggestion>", "\"").replace("</suggestion>", "\"") + "\r\n" + "Suggested correction: **" + match.getSuggestedReplacements().get(0) + "**", false);
                        if (count == 1) {
                            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                            count--;
                        } else {
                            count--;
                        }
                    }
                } catch (IOException e) {
                    BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "WordReplacer", 4);
                }
            }
        }
    }
}
