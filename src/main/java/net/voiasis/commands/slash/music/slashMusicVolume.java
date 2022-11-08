package net.voiasis.commands.slash.music;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.voiasis.commands.slash.handler.CommandBuilder;
import net.voiasis.tools.music.AudioPlayerThread;
import net.voiasis.tools.music.Values;

public class slashMusicVolume extends CommandBuilder {
    public slashMusicVolume() {
        setCommandData(Commands.slash("music-volume", "Change playback volume.").addOption(OptionType.INTEGER, "percentage", "Volume percentage. (1-100)", true));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        OptionMapping percentageOpt = event.getOption("percentage");
        String percentage = percentageOpt == null ? null : percentageOpt.getAsString();
        if(percentage == null) {
			event.reply("``Current volume: " + AudioPlayerThread.getVolume() + " %``\n"
					+ "``If you want to change the volume, please write the new volume behind this command.``").mentionRepliedUser(false).queue();
			return;
		}
		int r = AudioPlayerThread.setVolume(percentage);
		switch(r) {
		case Values.SET_VOLUME_SUCCESSFULLY:
			event.reply("``Volume set to: " + percentage + " %``").mentionRepliedUser(false).queue();
			break;
		case Values.SET_VOLUME_ERROR_CUSTOM_VOLUME_NOT_ALLOWED:
            event.reply("*Allow custom volume* is disabled in your configuration.").mentionRepliedUser(false).queue();
			break;
		case Values.SET_VOLUME_ERROR_INVALID_NUMBER:
            event.reply("``Invalid input. Only numbers between 0 and 100 are allowed.``").mentionRepliedUser(false).queue();
			break;
		}
    }
}
