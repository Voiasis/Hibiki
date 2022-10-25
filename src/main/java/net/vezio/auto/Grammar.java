package net.vezio.auto;

import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.spelling.SpellingCheckRule;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.vezio.tools.BotLog;

public class Grammar {
    public static void check(Message message) {
        if (message.getChannel().getId().equals("1010933102681542764")) {
            try {
                if (!message.getAuthor().isBot()) {
                    try {
                        String content = message.getContentRaw();
                        List<RuleMatch> matches;
                        JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
                        for (Rule rule : langTool.getAllActiveRules()) {
                            if (rule instanceof SpellingCheckRule) {
                                List<String> wordsToIgnore = Arrays.asList("lmao", "wdym", "idk", "wtf", "btw", "lol", "nvm", "tf");
                                ((SpellingCheckRule)rule).addIgnoreTokens(wordsToIgnore);
                            }
                        }
                        for (Rule rule : langTool.getAllActiveRules()) {
                            if (rule instanceof SpellingCheckRule) {
                                ((SpellingCheckRule)rule).acceptPhrases(Arrays.asList("lmao", "wdym", "idk", "wtf", "btw", "lol", "nvm", "tf"));
                            }
                        }
                        matches = langTool.check(content);
                        int count = matches.size();
                        int count2 = matches.size();
                        EmbedBuilder embed = new EmbedBuilder();
                        embed.setColor(Color.RED);
                        embed.setTitle("Grammar Checker");
                        //embed.setDescription(String.valueOf(count) + " potential grammar error(s) found.");
                        for (RuleMatch match : matches) {
                            String error = content.substring(match.getFromPos(), match.getToPos());
                            int count3 = count2 - count;
                            int from = match.getFromPos() + 1;
                            if (!match.getSuggestedReplacements().isEmpty()) {
                                embed.addField(String.valueOf(count3 + 1) + ": \"" + error + "\" at position " + from + "-" + match.getToPos(), match.getMessage().replace("<suggestion>", "\"").replace("</suggestion>", "\"") + "\r\n" + "Suggested correction: **" + match.getSuggestedReplacements().get(0) + "**", false);
                                if (count == 1) {
                                    embed.setDescription("**" + count2 + "** potential grammar " + grammar("error", count2) + " found.");
                                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                                    count--;
                                } else {
                                    count--;
                                }
                            }
                            
                        }
                    } catch (IOException e) {
                        BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "Grammar", 4);
                    }
                }
            } catch (Exception e) {
                BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "Grammar", 4);
            }
        }
    }
    public static String grammar(String word, long value) {
        if (value >= 2) {
            word = word + "s";
            return word;
        } else if (value == 0) {
            word = word + "s";
            return word;
        } else {
            return word;
        }
    }
    public static String grammarY(String word, long value) {
        if (value >= 2) {
            word = word.replace("y", "ies");
            return word;
        } else if (value == 0) {
            word = word.replace("y", "ies");
            return word;
        } else {
            return word;
        }
    }
}
