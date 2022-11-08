package net.voiasis.commands.music;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.voiasis.tools.music.AudioPlayerThread;

class Shuffle extends Command {
	@Override
	public String getName() {
		return "shuffle";
	}
	@Override
	public boolean isAdminOnly() {
		return true;
	}
	@Override
	public void execute(String arg, Member member, Guild guild, Message message) {
		AudioPlayerThread.getMusicManager().scheduler.shuffle();
		message.reply("``The playlist got shuffled.``").mentionRepliedUser(false).queue();
	}
}