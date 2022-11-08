package net.voiasis.translation;

import net.voiasis.tools.BotLog;

public class iSO6391CodeToLang {
    static String translator(String input) {
        BotLog.log("iSO6391CodeToLangInput: " + input, "LangChange", 3);
        if (input.equals("BG")) {
            return "bulgarian";
        } else if (input.equals("CN")) {
            return "chinese";
        } else if (input.equals("CZ")) {
            return "czech";
        } else if (input.equals("DK")) {
            return "danish";
        } else if (input.equals("NL")) {
            return "dutch";
        } else if (input.equals("US")) {
            return "english";
        } else if (input.equals("GB")) {
            return "british";
        } else if (input.equals("FI")) {
            return "estonian";
        } else if (input.equals("FR")) { //https://cdn.discordapp.com/emojis/855474693208014879.webp
            return "french";
        } else if (input.equals("DE")) {
            return "german";
        } else if (input.equals("GR")) {
            return "greek";
        } else if (input.equals("HU")) {
            return "hungarian";
        } else if (input.equals("IT")) {
            return "italian";
        } else if (input.equals("JP")) {
            return "japanese";
        } else if (input.equals("LV")) {
            return "latvian";
        } else if (input.equals("LT")) {
            return "lithuanian";
        } else if (input.equals("PL")) {
            return "polish";
        } else if (input.equals("PT")) {
            return "portuguese";
        } else if (input.equals("BR")) {
            return "brazilian";
        } else if (input.equals("RO")) {
            return "romanian";
        } else if (input.equals("RU")) {
            return "russian";
        } else if (input.equals("SK")) {
            return "slovak";
        } else if (input.equals("SI")) {
            return "slovenian";
        } else if (input.equals("ES")) {
            return "spanish";
        } else if (input.equals("SE")) {
            return "swedish";
        } else {
            return input;
        }
    }
}
