package net.voiasis.commands.slash.music;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.voiasis.commands.slash.handler.CommandBuilder;
import net.voiasis.tools.music.AudioPlayerThread;

public class slashMusicShuffle extends CommandBuilder {
    public slashMusicShuffle() {
        setCommandData(Commands.slash("music-shuffle", "Shuffles the playback queue."));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        AudioPlayerThread.getMusicManager().scheduler.shuffle();
		event.reply("``The playlist got shuffled.``").mentionRepliedUser(false).queue();
    }
}
