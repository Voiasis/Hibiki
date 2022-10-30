package net.vezio.commands.music;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.vezio.tools.music.AudioPlayerThread;
import net.vezio.tools.music.Values;

class Search extends Command {
	@Override
	public String getName() {
		return "search";
	}
	@Override
	public boolean isAdminOnly() {
		return false;
	}
	@Override
	public void execute(String arg, Member member, Guild guild, Message message) {
		if(arg == null) {
			message.reply("``Please specify a video title. Put it behind this command.``").mentionRepliedUser(false).queue();
			return;
		}
		message.reply("``Loading`` <a:typing:1010953714560933969>").mentionRepliedUser(false).queue(q -> {
			Music.joinVoiceChannel(member, q);
			AudioPlayerThread.playDirect(message, (Values.SEARCH_PREFIX_YOUTUBE + arg), false, member.getVoiceState().getChannel());
		});
	}
}