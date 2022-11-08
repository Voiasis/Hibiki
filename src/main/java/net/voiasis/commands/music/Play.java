package net.voiasis.commands.music;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.voiasis.tools.music.AudioPlayerThread;

class Play extends Command {
	@Override
	public String getName() {
		return "play";
	}
	@Override
	public boolean isAdminOnly() {
		return true;
	}
	@Override
	public void execute(String arg, Member member, Guild guild, Message message) {
		if(arg == null) {
			message.reply("``Please specify what I should play. Put it behind this command.``").mentionRepliedUser(false).queue();
			return;
		}
		message.reply("``Loading`` <a:typing:1010953714560933969>").mentionRepliedUser(false).queue(q -> {
			Music.joinVoiceChannel(member, q);
			AudioPlayerThread.playDirect(message, arg, false, member.getVoiceState().getChannel());
		});
	}
}