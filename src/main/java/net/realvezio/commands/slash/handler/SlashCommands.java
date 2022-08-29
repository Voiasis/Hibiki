package net.realvezio.commands.slash.handler;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class SlashCommands extends ListenerAdapter {
    public static void slashCommand(@NotNull SlashCommandInteractionEvent event) {
        CommandBuilder command = CommandsList.getCommands().get(event.getName());
        if (command != null) {
            command.executeCommand(event);
        } else {
            event.reply("Command not found").queue();
        }
    }
}
