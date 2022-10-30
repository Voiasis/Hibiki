package net.vezio.commands.slash.music;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.vezio.commands.slash.handler.CommandBuilder;
import net.vezio.tools.music.AudioPlayerThread;
import net.vezio.tools.music.Utils;
import net.vezio.tools.music.Values;

public class slashMusicQueue extends CommandBuilder {
    public slashMusicQueue() {
        setCommandData(Commands.slash("music-queue", "Shows the playback queue."));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        if(AudioPlayerThread.isPlaying()) {
			StringBuilder toSend = new StringBuilder(""
					+ " Currently playing:"
					+ "\n"
					+ "```"
					+ Utils.getTrackName(AudioPlayerThread.getMusicManager().player.getPlayingTrack())
					+ "```"
					+ "\n");
			if(AudioPlayerThread.getMusicManager().scheduler.getList().size() > 0) {
				toSend.append("Upcoming songs:"
						+ "\n"
						+ "```");
				ArrayList<AudioTrack> list = AudioPlayerThread.getMusicManager().scheduler.getList();
				for(int i = 0; i < list.size(); i++) {
					toSend.append("\n" + Utils.getTrackName(list.get(i)));
				}
				toSend.append("```");
			} else {
				toSend.append("``There are no upcoming songs.``");
			}
			if(toSend.length() > Values.MAX_MESSAGE_LENGTH) {
				final String ending = "...```";
				toSend.setLength((Values.MAX_MESSAGE_LENGTH - ending.length()));
				toSend.append(ending);
			}
			event.reply(toSend.toString()).mentionRepliedUser(false).queue();
		} else {
			// because gets cleared on stop
			// isPlaying() -> else:
			event.reply("The playlist is empty.").mentionRepliedUser(false).queue();
		}
    }
}
