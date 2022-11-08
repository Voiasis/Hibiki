package net.voiasis.commands.slash.music;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.voiasis.commands.music.Music;
import net.voiasis.commands.slash.handler.CommandBuilder;
import net.voiasis.tools.music.AudioPlayerThread;

public class slashMusicPlay extends CommandBuilder {
    public slashMusicPlay() {
        setCommandData(Commands.slash("music-play", "Play given track or playlist now.").addOption(OptionType.STRING, "url", "URL of the track or playlist.", true));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        OptionMapping urlOpt = event.getOption("url");
        String url = urlOpt == null ? null : urlOpt.getAsString();
        Member member = event.getMember();
        event.reply("``Loading`` <a:typing:1010953714560933969>").mentionRepliedUser(false).queue(q -> {
			Music.joinVoiceChannel(member, q);
			AudioPlayerThread.playDirect(q, url, false, member.getVoiceState().getChannel());
		});
    }
}