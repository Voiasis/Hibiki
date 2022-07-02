package net.voiasis.commands.prefix;

import com.github.javafaker.Faker;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class prefixTest {
    public static void test(Message message, User author, String[] args) {
        if (author.getId().equals("472899069136601099")) {
            message.getMember().modifyNickname(Faker.instance().funnyName().name()).queue();
        }
    }
} //Faker.instance().funnyName().name()
