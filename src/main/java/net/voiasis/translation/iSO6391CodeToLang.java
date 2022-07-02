package net.voiasis.translation;

import net.voiasis.tools.BotLog;

public class iSO6391CodeToLang {
    static String translator(String input) {
        BotLog.log("iSO6391CodeToLangInput: " + input, "LangChange", 3);
        String word;
        if (input.equals("BG")) {
            return word = "bulgarian";
        } else if (input.equals("CN")) {
            return word = "chinese";
        } else if (input.equals("CZ")) {
            return word = "czech";
        } else if (input.equals("DK")) {
            return word = "danish";
        } else if (input.equals("NL")) {
            return word = "dutch";
        } else if (input.equals("US")) {
            return word = "english";
        } else if (input.equals("GB")) {
            return word = "british";
        } else if (input.equals("FI")) {
            return word = "estonian";
        } else if (input.equals("FR")) { //https://cdn.discordapp.com/emojis/855474693208014879.webp
            return word = "french";
        } else if (input.equals("DE")) {
            return word = "german";
        } else if (input.equals("GR")) {
            return word = "greek";
        } else if (input.equals("HU")) {
            return word = "hungarian";
        } else if (input.equals("IT")) {
            return word = "italian";
        } else if (input.equals("JP")) {
            return word = "japanese";
        } else if (input.equals("LV")) {
            return word = "latvian";
        } else if (input.equals("LT")) {
            return word = "lithuanian";
        } else if (input.equals("PL")) {
            return word = "polish";
        } else if (input.equals("PT")) {
            return word = "portuguese";
        } else if (input.equals("BR")) {
            return word = "brazilian";
        } else if (input.equals("RO")) {
            return word = "romanian";
        } else if (input.equals("RU")) {
            return word = "russian";
        } else if (input.equals("SK")) {
            return word = "slovak";
        } else if (input.equals("SI")) {
            return word = "slovenian";
        } else if (input.equals("ES")) {
            return word = "spanish";
        } else if (input.equals("SE")) {
            return word = "swedish";
        } else {
            return input;
        }
    }
}
