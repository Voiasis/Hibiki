package net.vezio.tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class BadWordChecker {
    public static boolean badWordCheck(String input) throws IOException {
        String content = Files.readString(Path.of("badwords.txt"), StandardCharsets.US_ASCII);
        String[] badWords = content.split(System.lineSeparator());
        boolean a = SearchTools.stringContainsItemFromList(input, badWords);
        return a;
    }
}
