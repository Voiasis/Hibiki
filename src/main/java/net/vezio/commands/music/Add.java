package net.vezio.commands.music;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.vezio.tools.music.AudioPlayerThread;

class Add extends Command {
	@Override
	public String getName() {
		return "add";
	}
	@Override
	public boolean isAdminOnly() {
		return false;
	}
	@Override
	public void execute(String arg, Member member, Guild guild, Message message) {
		if(arg == null) {
			message.reply("Please specify what I should add to the playlist. Put it behind this command.").mentionRepliedUser(false).queue();
			return;
		}
		message.reply("``Loading`` <a:typing:1010953714560933969>").mentionRepliedUser(false).queue(q -> {
			Music.joinVoiceChannel(member, q);
			AudioPlayerThread.addToPlaylist(arg, false, q);
		});
	}
}