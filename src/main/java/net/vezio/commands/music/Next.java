package net.vezio.commands.music;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.vezio.tools.music.AudioPlayerThread;
import net.vezio.tools.music.Utils;

class Next extends Command {
	@Override
	public String getName() {
		return "next";
	}
	@Override
	public boolean isAdminOnly() {
		return true;
	}
	@Override
	public void execute(String arg, Member member, Guild guild, Message message) {
		if(!AudioPlayerThread.isPlaying()) {
			message.reply("``Currently I'm not playing.``").mentionRepliedUser(false).queue();
			return;
		}
		int skips;
		if (arg == null) {
			skips = 1;
		} else {
			try {
				skips = Integer.parseInt(arg);
				if (skips < 1) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				message.reply("``Invalid number``").mentionRepliedUser(false).queue();
				return;
			}
		}
		AudioPlayerThread.getMusicManager().scheduler.nextTrack(skips);
		message.reply("Now playing: ``" + Utils.getTrackName(AudioPlayerThread.getCurrentTrack()) + "``").mentionRepliedUser(false).queue();
	}
}