package net.voiasis.commands.music;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.voiasis.tools.music.AudioPlayerThread;
import net.voiasis.tools.music.Values;

class Volume extends Command {
	@Override
	public String getName() {
		return "volume";
	}
	@Override
	public boolean isAdminOnly() {
		return true;
	}
	@Override
	public void execute(String arg, Member member, Guild guild, Message message) {
		if(arg == null) {
			message.reply("``Current volume: " + AudioPlayerThread.getVolume() + " %``\n"
					+ "``If you want to change the volume, please write the new volume behind this command.``").mentionRepliedUser(false).queue();
			return;
		}
		int r = AudioPlayerThread.setVolume(arg);
		switch(r) {
		case Values.SET_VOLUME_SUCCESSFULLY:
			message.reply("``Volume set to: " + arg + " %``").mentionRepliedUser(false).queue();
			break;
		case Values.SET_VOLUME_ERROR_CUSTOM_VOLUME_NOT_ALLOWED:
			message.reply("*Allow custom volume* is disabled in your configuration.").mentionRepliedUser(false).queue();
			break;
		case Values.SET_VOLUME_ERROR_INVALID_NUMBER:
			message.reply("``Invalid input. Only numbers between 0 and 100 are allowed.``").mentionRepliedUser(false).queue();
			break;
		}
	}
}