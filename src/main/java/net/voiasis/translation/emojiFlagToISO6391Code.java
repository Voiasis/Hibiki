package net.voiasis.translation;

import net.voiasis.tools.BotLog;

public class emojiFlagToISO6391Code {
    static String translator(String input, boolean reactionEvent) {
        String emoji = input;
        BotLog.log("emojiFlagToISO6391CodeInput: " + emoji, "LangChange", 3);
        if (emoji.contains("🇧🇬")) {
            return "BG";
        } else if (emoji.contains("🇨🇳")) {
            return "CN";
        } else if (emoji.contains("🇨🇿")) {
            return "CZ";
        } else if (emoji.contains("🇩🇰")) {
            return "DK";
        } else if (emoji.contains("🇳🇱")) {
            return "NL";
        } else if (emoji.contains("🇺🇸")) {
            return "US";
        } else if (emoji.contains("🇬🇧")) {
            return "GB";
        } else if (emoji.contains("🇪🇪")) {
            return "EE";
        } else if (emoji.contains("🇫🇮")) {
            return "FI";
        } else if (emoji.contains("🇫🇷")) {
            return "FR";
        } else if (emoji.contains("🇩🇪")) {
            return "DE";
        } else if (emoji.contains("🇬🇷")) {
            return "GR";
        } else if (emoji.contains("🇭🇺")) {
            return "HU";
        } else if (emoji.contains("🇮🇹")) {
            return "IT";
        } else if (emoji.contains("🇯🇵")) {
            return "JP";
        } else if (emoji.contains("🇱🇻")) {
            return "LV";
        } else if (emoji.contains("🇱🇹")) {
            return "LT";
        } else if (emoji.contains("🇵🇱")) {
            return "PL";
        } else if (emoji.contains("🇵🇹")) {
            return "PT";
        } else if (emoji.contains("🇧🇷")) {
            return "BR";
        } else if (emoji.contains("🇷🇴")) {
            return "RO";
        } else if (emoji.contains("🇷🇺")) {
            return "RU";
        } else if (emoji.contains("🇸🇰")) {
            return "SK";
        } else if (emoji.contains("🇸🇮")) {
            return "SI";
        } else if (emoji.contains("🇪🇸")) {
            return "ES";
        } else if (emoji.contains("🇸🇪")) {
            return "SE";
        } else {
            if (reactionEvent) {
                return "auto";
            } else {
                return input;
            }
        }
    }
}
