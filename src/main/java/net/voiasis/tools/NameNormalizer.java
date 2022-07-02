package net.voiasis.tools;
import java.text.Normalizer;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class NameNormalizer {
    public static void normalizer(Member member, Guild guild) {
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
                    if (badWordCheck(normalizedUsername)) {
                        member.timeoutFor(5, TimeUnit.HOURS).queue();
                        member.modifyNickname(randomName).queue();
                    } else {
                        member.modifyNickname(normalizedUsername).queue();
                    }
                }
            }
        } catch (Exception e) {
        }
        //if (guild.getId().equals("902397621015040020")) {
            
        //}
    }
    private static boolean badWordCheck(String normalizedUsername) {
        if (normalizedUsername.contains("nigg")) {
            return true;
        } else {
            return false;
        }
    }
}
