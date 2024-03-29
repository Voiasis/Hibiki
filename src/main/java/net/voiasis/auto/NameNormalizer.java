package net.voiasis.auto;

import java.text.Normalizer;

import com.github.javafaker.Faker;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.voiasis.tools.BadWordChecker;
import net.voiasis.tools.BotLog;
import net.voiasis.tools.SearchTools;

public class NameNormalizer {
    public static void normalizer(Member member) {
        try {
            if (!member.hasPermission(Permission.ADMINISTRATOR)) {
                Faker faker = new Faker();
                String randomName = faker.funnyName().name();
                String currentUsername = member.getEffectiveName();
                String normalizedUsername = Normalizer.normalize(currentUsername, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                int count1 = SearchTools.countChar(normalizedUsername, '!');
                int count2 = SearchTools.countChar(normalizedUsername, '#');
                int count3 = SearchTools.countChar(normalizedUsername, '$');
                int count4 = SearchTools.countChar(normalizedUsername, '(');
                int count5 = SearchTools.countChar(normalizedUsername, ')');
                int count6 = SearchTools.countChar(normalizedUsername, '*');
                int count7 = SearchTools.countChar(normalizedUsername, '-');
                int count8 = SearchTools.countChar(normalizedUsername, '+');
                int count9 = SearchTools.countChar(normalizedUsername, '.');
                while (count1 + count2 + count3 + count4 + count5 + count6 + count7 + count8 + count9  > 0) {
                    if (normalizedUsername.startsWith("!")) {
                        normalizedUsername = normalizedUsername.substring(1);
                        count1--;
                    } else if (normalizedUsername.startsWith("#")) {
                        normalizedUsername = normalizedUsername.substring(1);
                        count2--;
                    } else if (normalizedUsername.startsWith("$")) {
                        normalizedUsername = normalizedUsername.substring(1);
                        count3--;
                    } else if (normalizedUsername.startsWith("(")) {
                        normalizedUsername = normalizedUsername.substring(1);
                        count4--;
                    } else if (normalizedUsername.startsWith(")")) {
                        normalizedUsername = normalizedUsername.substring(1);
                        count5--;
                    } else if (normalizedUsername.startsWith("*")) {
                        normalizedUsername = normalizedUsername.substring(1);
                        count6--;
                    } else if (normalizedUsername.startsWith("-")) {
                        normalizedUsername = normalizedUsername.substring(1);
                        count7--;
                    } else if (normalizedUsername.startsWith("+")) {
                        normalizedUsername = normalizedUsername.substring(1);
                        count8--;
                    } else if (normalizedUsername.startsWith(".")) {
                        normalizedUsername = normalizedUsername.substring(1);
                        count9--;
                    } else {
                        count1--;
                        count2--;
                        count3--;
                        count4--;
                        count5--;
                        count6--;
                        count7--;
                        count8--;
                        count9--;
                    }
                }
                if (normalizedUsername.length() < 3) {
                    member.modifyNickname(randomName).queue();
                } else {
                    if (BadWordChecker.badWordCheck(normalizedUsername)) {
                        //member.timeoutFor(5, TimeUnit.MINUTES).queue();
                        member.modifyNickname(randomName).queue();
                    } else {
                        member.modifyNickname(normalizedUsername).queue();
                    }
                }
            }
        } catch (Exception e) {
            BotLog.log(BotLog.getStackTraceString(e, member.getJDA()), "NameNormalizer", 4);
        }
        if (member.getId().equals("626397784169381888")) {
            member.modifyNickname("Transman Apple").queue();
        }
    }
}
