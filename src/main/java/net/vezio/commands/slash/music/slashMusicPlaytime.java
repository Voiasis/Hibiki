package net.vezio.commands.slash.music;

import org.jetbrains.annotations.NotNull;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.vezio.commands.slash.handler.CommandBuilder;
import net.vezio.tools.music.AudioPlayerThread;
import net.vezio.tools.music.Utils;

public class slashMusicPlaytime extends CommandBuilder {
    public slashMusicPlaytime() {
        setCommandData(Commands.slash("music-playtime", "Time left in currently playing track."));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        AudioTrack track = AudioPlayerThread.getCurrentTrack();
		long dur = track.getDuration();
		long pos = track.getPosition();
		event.reply("``" + Utils.durationToTackPosition(pos) + " / " + Utils.durationToTackPosition(dur) + "``").mentionRepliedUser(false).queue();
    }
}
