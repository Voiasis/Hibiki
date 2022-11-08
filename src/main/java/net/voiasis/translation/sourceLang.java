package net.voiasis.translation;

import de.linus.deepltranslator.SourceLanguage;
import net.voiasis.tools.BotLog;

public class sourceLang {
    static SourceLanguage source(String langInput) {
        String lang = langInput.toLowerCase();
        BotLog.log("sourceInput: " + lang, "LangChange", 3);
        if (lang.equals("auto")) {
            return SourceLanguage.AUTO_DETECT;
        } else if (lang.equals("bulgarian")) {
            return SourceLanguage.BULGARIAN;
        } else if (lang.equals("chinese")) {
            return SourceLanguage.CHINESE;
        } else if (lang.equals("czech")) {
            return SourceLanguage.CZECH;
        } else if (lang.equals("danish")) {
            return SourceLanguage.DANISH;
        } else if (lang.equals("dutch")) {
            return SourceLanguage.DANISH;
        } else if (lang.equals("english")) {
            return SourceLanguage.ENGLISH;
        } else if (lang.equals("estonian")) {
            return SourceLanguage.ESTONIAN;
        } else if (lang.equals("finnish")) {
            return SourceLanguage.FINNISH;
        } else if (lang.equals("french")) {
            return SourceLanguage.FRENCH;
        } else if (lang.equals("german")) {
            return SourceLanguage.GERMAN;
        } else if (lang.equals("greek")) {
            return SourceLanguage.GREEK;
        } else if (lang.equals("hungarian")) {
            return SourceLanguage.HUNGARIAN;
        } else if (lang.equals("japanese")) {
            return SourceLanguage.JAPANESE;
        } else if (lang.equals("latvian")) {
            return SourceLanguage.LATVIAN;
        } else if (lang.equals("lithuanian")) {
            return SourceLanguage.LITHUANIAN;
        } else if (lang.equals("polish")) {
            return SourceLanguage.POLISH;
        } else if (lang.equals("portuguese")) {
            return SourceLanguage.PORTUGUESE;
        } else if (lang.equals("romanian")) {
            return SourceLanguage.ROMANIAN;
        } else if (lang.equals("russian")) {
            return SourceLanguage.RUSSIAN;
        } else if (lang.equals("slovak")) {
            return SourceLanguage.SLOVAK;
        } else if (lang.equals("slovenian")) {
            return SourceLanguage.SLOVENIAN;
        } else if (lang.equals("spanish")) {
            return SourceLanguage.SPANISH;
        } else if (lang.equals("swedish")) {
            return SourceLanguage.SWEDISH;
        } else {
            return SourceLanguage.AUTO_DETECT;
        }
    }
}
