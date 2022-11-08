package net.voiasis.commands.slash.music;

import org.jetbrains.annotations.NotNull;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.voiasis.commands.slash.handler.CommandBuilder;
import net.voiasis.tools.music.AudioPlayerThread;

public class slashMusicJump extends CommandBuilder {
    public slashMusicJump() {
        setCommandData(Commands.slash("music-jump", "Jump forward in the current track.").addOption(OptionType.INTEGER, "seconds", "Number of seconds to jump.", true));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        OptionMapping argOpt = event.getOption("seconds");
        String arg = argOpt == null ? null : argOpt.getAsString();
        if(!AudioPlayerThread.isPlaying()) {
			event.reply("``Currently I'm not playing.``").mentionRepliedUser(false).queue();
			return;
		}
		int seconds;
		if(arg == null) {
			seconds = 10;
		} else {
			try {
				seconds = Integer.parseInt(arg);
				if(seconds == 0) {
					throw new NumberFormatException();
				}
			} catch(NumberFormatException e) {
				event.reply( "``Invalid number``").mentionRepliedUser(false).queue();
				return;
			}
		}
		AudioTrack track = AudioPlayerThread.getMusicManager().player.getPlayingTrack();
		track.setPosition(track.getPosition() + (1000*seconds)); // Lavaplayer handles values < 0 or > track length
    }
}
