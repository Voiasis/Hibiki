package net.voiasis.commands.slash.music;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.voiasis.commands.slash.handler.CommandBuilder;
import net.voiasis.tools.music.AudioPlayerThread;
import net.voiasis.tools.music.Utils;

public class slashMusicSeek extends CommandBuilder {
    public slashMusicSeek() {
        setCommandData(Commands.slash("music-seek", "Seek to the specified position.").addOption(OptionType.STRING, "time", "hours:minutes:seconds", true));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        OptionMapping timeOpt = event.getOption("");
        String time = timeOpt == null ? null : timeOpt.getAsString();
        Member member = event.getMember();
        if(!AudioPlayerThread.isPlaying()) {
			event.reply("``Currently I'm not playing.``").mentionRepliedUser(false).queue();
			return;
		}
		long ms = -1; // invalid by default
		try {
			int c = time.length() - time.replace(":", "").length();
			if(c == 2) {
				// hours, minutes and seconds
				String[] split = time.split(":");
				ms = Utils.timeToMS(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
			} else if(c == 1) {
				// minutes and seconds
				String[] split = time.split(":");
				ms = Utils.timeToMS(0, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
			} else if(c == 0) {
				// only seconds
				ms = Utils.timeToMS(0, 0, Integer.parseInt(time));
			}
			if(ms < 0) {
				throw new NumberFormatException();
			}
		} catch(Exception e) {
			event.reply(member.getAsMention() +  " ``Invalid time``").mentionRepliedUser(false).queue();
			return;
		}
		AudioPlayerThread.getMusicManager().player.getPlayingTrack().setPosition(ms);
    }
}
