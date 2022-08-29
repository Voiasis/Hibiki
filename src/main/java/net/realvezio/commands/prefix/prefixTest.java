package net.realvezio.commands.prefix;

import net.dv8tion.jda.api.entities.Message;

public class prefixTest {
    public static void test(Message message, String[] args) {
        if (message.getAuthor().getId().equals("835691330725347379")) {
            message.reply(message.getChannelType().name()).queue();
        }
    }
}
