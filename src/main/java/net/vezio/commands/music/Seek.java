package net.vezio.commands.music;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.vezio.tools.music.AudioPlayerThread;
import net.vezio.tools.music.Utils;

class Seek extends Command {
	@Override
	public String getName() {
		return "seek";
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
		if(arg == null) {
			message.reply("``Please specify a time. Put it behind this command. Split hours, minutes and seconds with ':'. Hours and minutes are optional.``").mentionRepliedUser(false).queue();
			return;
		}
		long ms = -1; // invalid by default
		try {
			int c = arg.length() - arg.replace(":", "").length();
			if(c == 2) {
				// hours, minutes and seconds
				String[] split = arg.split(":");
				ms = Utils.timeToMS(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
			} else if(c == 1) {
				// minutes and seconds
				String[] split = arg.split(":");
				ms = Utils.timeToMS(0, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
			} else if(c == 0) {
				// only seconds
				ms = Utils.timeToMS(0, 0, Integer.parseInt(arg));
			}
			if(ms < 0) {
				throw new NumberFormatException();
			}
		} catch(Exception e) {
			message.reply(member.getAsMention() +  " ``Invalid time``").mentionRepliedUser(false).queue();
			return;
		}
		AudioPlayerThread.getMusicManager().player.getPlayingTrack().setPosition(ms);
	}
}