package net.voiasis.commands.slash;

import net.dv8tion.jda.api.entities.Message;
import net.voiasis.commands.slash.handler.CommandsList;


public class prefixCreateTemp {
    public static void create(Message message){
        if (message.getAuthor().getId().equals("472899069136601099")) {
            message.delete().queue();
            CommandsList.registerCommands(message.getJDA(), message.getGuild().getId());
        }
    }
}
