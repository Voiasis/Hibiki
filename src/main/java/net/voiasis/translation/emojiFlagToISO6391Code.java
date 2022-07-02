package net.voiasis.translation;

import net.voiasis.tools.BotLog;

public class emojiFlagToISO6391Code {
    static String translator(String input, boolean reactionEvent) {
        String emoji = input;
        BotLog.log("emojiFlagToISO6391CodeInput: " + emoji, "LangChange", 3);
        String lang;
        if (emoji.contains("🇧🇬")) {
            return lang = "BG";
        } else if (emoji.contains("🇨🇳")) {
            return lang = "CN";
        } else if (emoji.contains("🇨🇿")) {
            return lang = "CZ";
        } else if (emoji.contains("🇩🇰")) {
            return lang = "DK";
        } else if (emoji.contains("🇳🇱")) {
            return lang = "NL";
        } else if (emoji.contains("🇺🇸")) {
            return lang = "US";
        } else if (emoji.contains("🇬🇧")) {
            return lang = "GB";
        } else if (emoji.contains("🇪🇪")) {
            return lang = "EE";
        } else if (emoji.contains("🇫🇮")) {
            return lang = "FI";
        } else if (emoji.contains("🇫🇷")) {
            return lang = "FR";
        } else if (emoji.contains("🇩🇪")) {
            return lang = "DE";
        } else if (emoji.contains("🇬🇷")) {
            return lang = "GR";
        } else if (emoji.contains("🇭🇺")) {
            return lang = "HU";
        } else if (emoji.contains("🇮🇹")) {
            return lang = "IT";
        } else if (emoji.contains("🇯🇵")) {
            return lang = "JP";
        } else if (emoji.contains("🇱🇻")) {
            return lang = "LV";
        } else if (emoji.contains("🇱🇹")) {
            return lang = "LT";
        } else if (emoji.contains("🇵🇱")) {
            return lang = "PL";
        } else if (emoji.contains("🇵🇹")) {
            return lang = "PT";
        } else if (emoji.contains("🇧🇷")) {
            return lang = "BR";
        } else if (emoji.contains("🇷🇴")) {
            return lang = "RO";
        } else if (emoji.contains("🇷🇺")) {
            return lang = "RU";
        } else if (emoji.contains("🇸🇰")) {
            return lang = "SK";
        } else if (emoji.contains("🇸🇮")) {
            return lang = "SI";
        } else if (emoji.contains("🇪🇸")) {
            return lang = "ES";
        } else if (emoji.contains("🇸🇪")) {
            return lang = "SE";
        } else {
            if (reactionEvent) {
                return "auto";
            } else {
                return input;
            }
        }
    }
}
