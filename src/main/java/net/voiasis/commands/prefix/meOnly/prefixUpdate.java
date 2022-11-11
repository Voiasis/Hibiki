package net.voiasis.commands.prefix.meOnly;

import net.dv8tion.jda.api.entities.Message;

public class prefixUpdate {
    public static void update(Message message) {
        message.getGuild().updateCommands().queue();
    }
}
