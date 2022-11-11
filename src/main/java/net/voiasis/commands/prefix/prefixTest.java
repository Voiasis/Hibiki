package net.voiasis.commands.prefix;

import net.dv8tion.jda.api.entities.Message;
import net.voiasis.commands.slash.handler.CommandsList;

public class prefixTest {
    public static void test(Message message, String[] args) {
        if (message.getAuthor().getId().equals("835691330725347379")) {
            CommandsList.registerCommands(message.getJDA(), message.getGuild().getId());  
        }
    }
}