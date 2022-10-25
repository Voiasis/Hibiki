package net.vezio.events;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.vezio.commands.slash.handler.SlashCommands;

public class onSlashCommandInteractionEvent extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        SlashCommands.slashCommand(event);
    }
}
