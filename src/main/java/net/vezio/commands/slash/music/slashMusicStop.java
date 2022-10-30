package net.vezio.commands.slash.music;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.vezio.commands.music.Music;
import net.vezio.commands.slash.handler.CommandBuilder;

public class slashMusicStop extends CommandBuilder {
    public slashMusicStop() {
        setCommandData(Commands.slash("music-stop", "Stops playback and disconnects."));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        Guild guild = event.getGuild();
        Music.stopPlayer(guild);
		event.reply("``Playback stopped.``").mentionRepliedUser(false).queue();
    }
}
