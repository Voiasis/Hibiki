package net.realvezio.translation;

import de.linus.deepltranslator.TargetLanguage;
import net.realvezio.tools.BotLog;

public class targetLang {
    static TargetLanguage target(String langOutput) {
        String lang = langOutput.toLowerCase();
        BotLog.log("targetInput: " + lang, "LangChange", 3);
        if (lang.equals("bulgarian")) {
            return TargetLanguage.BULGARIAN;
        } else if (lang.equals("chinese")) {
            return TargetLanguage.CHINESE_SIMPLIFIED;
        } else if (lang.equals("czech")) {
            return TargetLanguage.CZECH;
        } else if (lang.equals("danish")) {
            return TargetLanguage.DANISH;
        } else if (lang.equals("dutch")) {
            return TargetLanguage.DANISH;
        } else if (lang.equals("english")) {
            return TargetLanguage.ENGLISH_AMERICAN;
        } else if (lang.equals("british")) {
            return TargetLanguage.ENGLISH_BRITISH;
        } else if (lang.equals("estonian")) {
            return TargetLanguage.ESTONIAN;
        } else if (lang.equals("finnish")) {
            return TargetLanguage.FINNISH;
        } else if (lang.equals("french")) {
            return TargetLanguage.FRENCH;
        } else if (lang.equals("german")) {
            return TargetLanguage.GERMAN;
        } else if (lang.equals("greek")) {
            return TargetLanguage.GREEK;
        } else if (lang.equals("hungarian")) {
            return TargetLanguage.HUNGARIAN;
        } else if (lang.equals("italian")) {
            return TargetLanguage.ITALIAN;
        } else if (lang.equals("japanese")) {
            return TargetLanguage.JAPANESE;
        } else if (lang.equals("latvian")) {
            return TargetLanguage.LATVIAN;
        } else if (lang.equals("lithuanian")) {
            return TargetLanguage.LITHUANIAN;
        } else if (lang.equals("polish")) {
            return TargetLanguage.POLISH;
        } else if (lang.equals("portuguese")) {
            return TargetLanguage.PORTUGUESE;
        } else if (lang.equals("brazilian")) {
            return TargetLanguage.PORTUGUESE_BRAZILIAN;
        } else if (lang.equals("romanian")) {
            return TargetLanguage.ROMANIAN;
        } else if (lang.equals("russian")) {
            return TargetLanguage.RUSSIAN;
        } else if (lang.equals("slovak")) {
            return TargetLanguage.SLOVAK;
        } else if (lang.equals("slovenian")) {
            return TargetLanguage.SLOVENIAN;
        } else if (lang.equals("spanish")) {
            return TargetLanguage.SPANISH;
        } else if (lang.equals("swedish")) {
            return TargetLanguage.SWEDISH;
        } else {
            return null;
        }
    }
}
