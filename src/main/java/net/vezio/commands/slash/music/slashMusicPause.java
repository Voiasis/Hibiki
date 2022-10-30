package net.vezio.commands.slash.music;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.vezio.commands.slash.handler.CommandBuilder;
import net.vezio.tools.music.AudioPlayerThread;

public class slashMusicPause extends CommandBuilder {
    public slashMusicPause() {
        setCommandData(Commands.slash("music-pause", "Pauses playback."));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        if(AudioPlayerThread.isPaused()) {
			event.reply("``Resumed.``").mentionRepliedUser(false).queue();
			AudioPlayerThread.setPaused(false);
		} else {
			AudioPlayerThread.setPaused(true);
			event.reply("``Paused.\n"
					+ "Type this command again to resume.``").mentionRepliedUser(false).queue();
		}
    }
}
