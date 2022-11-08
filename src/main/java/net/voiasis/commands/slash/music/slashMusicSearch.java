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
import net.voiasis.tools.music.Values;

public class slashMusicSearch extends CommandBuilder {
    public slashMusicSearch() {
        setCommandData(Commands.slash("music-search", "Search and play top YouTube result.").addOption(OptionType.STRING, "search", "YouTube video title.", true));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        OptionMapping searchOpt = event.getOption("search");
        String search = searchOpt == null ? null : searchOpt.getAsString();
        Member member = event.getMember();
        event.reply("``Loading`` <a:typing:1010953714560933969>").mentionRepliedUser(false).queue(q -> {
			Music.joinVoiceChannel(member, q);
			AudioPlayerThread.playDirect(q, (Values.SEARCH_PREFIX_YOUTUBE + search), false, member.getVoiceState().getChannel());
		});
    }
}
