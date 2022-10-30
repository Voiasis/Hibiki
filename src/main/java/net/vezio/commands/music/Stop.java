package net.vezio.commands.music;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

class Stop extends Command {
	@Override
	public String getName() {
		return "stop";
	}
	@Override
	public boolean isAdminOnly() {
		return true;
	}
	@Override
	public void execute(String arg, Member member, Guild guild, Message message) {
		Music.stopPlayer(guild);
		message.reply("``Playback stopped.``").mentionRepliedUser(false).queue();
	}
}