package net.vezio.commands.music;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.vezio.tools.music.AudioPlayerThread;

class Pause extends Command {
	@Override
	public String getName() {
		return "pause";
	}
	@Override
	public boolean isAdminOnly() {
		return true;
	}
	@Override
	public void execute(String arg, Member member, Guild guild, Message message) {
		if(AudioPlayerThread.isPaused()) {
			message.reply("``Resumed.``").mentionRepliedUser(false).queue();
			AudioPlayerThread.setPaused(false);
		} else {
			AudioPlayerThread.setPaused(true);
			message.reply("``Paused.\n"
					+ "Type this command again to resume.``").mentionRepliedUser(false).queue();
		}
	}
}