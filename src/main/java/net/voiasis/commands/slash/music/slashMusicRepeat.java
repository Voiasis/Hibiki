package net.voiasis.commands.slash.music;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.voiasis.commands.slash.handler.CommandBuilder;
import net.voiasis.tools.music.AudioPlayerThread;

public class slashMusicRepeat extends CommandBuilder {
    public slashMusicRepeat() {
        setCommandData(Commands.slash("music-repeat", "Repeat the current playback queue.").addOption(OptionType.INTEGER, "amount", "Number of times to repeat.", false));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        OptionMapping amountOpt = event.getOption("amount");
        String amount = amountOpt == null ? null : amountOpt.getAsString();
        int repeats;
		if(amount == null) {
			repeats = 1;
		} else {
			try {
				repeats = Integer.parseInt(amount);
				if(repeats < 1) {
					throw new NumberFormatException();
				}
			} catch(NumberFormatException e) {
				event.reply("``Invalid number``").mentionRepliedUser(false).queue();
				return;
			}
		}
		if(AudioPlayerThread.isPlaying()) {

			ArrayList<AudioTrack> songs = new ArrayList<>();
			songs.add(AudioPlayerThread.getMusicManager().player.getPlayingTrack());
			ArrayList<AudioTrack> upcoming = AudioPlayerThread.getMusicManager().scheduler.getList();
			if(!upcoming.isEmpty()) {
				for(int i = 0; i < upcoming.size(); i++) {
					songs.add(upcoming.get(i));
				}
			}
			for(int i = 0; i < repeats; i++) {
				for(int j = 0; j < songs.size(); j++) {
					AudioPlayerThread.addToPlaylist(songs.get(j).makeClone());
				}
			}
			event.reply( "``Repeated the playlist" + (repeats == 1 ? ".``" : (" " + repeats + " times.``") )).mentionRepliedUser(false).queue();
		} else {
			event.reply("``The playlist is empty. There is nothing to repeat.``").mentionRepliedUser(false).queue();
		}
    }
}
