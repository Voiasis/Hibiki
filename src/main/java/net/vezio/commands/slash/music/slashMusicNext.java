package net.vezio.commands.slash.music;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.vezio.commands.slash.handler.CommandBuilder;
import net.vezio.tools.music.AudioPlayerThread;
import net.vezio.tools.music.Utils;

public class slashMusicNext extends CommandBuilder {
    public slashMusicNext() {
        setCommandData(Commands.slash("music-next", "Skips tracks.").addOption(OptionType.INTEGER, "amount", "Number of tracks to skip.", false));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        OptionMapping amountOpt = event.getOption("");
        String amount = amountOpt == null ? null : amountOpt.getAsString();
        if(!AudioPlayerThread.isPlaying()) {
			event.reply("``Currently I'm not playing.``").mentionRepliedUser(false).queue();
			return;
		}
		int skips;
		if (amount == null) {
			skips = 1;
		} else {
			try {
				skips = Integer.parseInt(amount);
				if (skips < 1) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				event.reply("``Invalid number``").mentionRepliedUser(false).queue();
				return;
			}
		}
		AudioPlayerThread.getMusicManager().scheduler.nextTrack(skips);
		event.reply("Now playing: ``" + Utils.getTrackName(AudioPlayerThread.getCurrentTrack()) + "``").mentionRepliedUser(false).queue();
    }
}
