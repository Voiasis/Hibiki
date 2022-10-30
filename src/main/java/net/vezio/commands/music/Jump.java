package net.vezio.commands.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.vezio.tools.music.AudioPlayerThread;

class Jump extends Command {
	@Override
	public String getName() {
		return "jump";
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
		int seconds;
		if(arg == null) {
			seconds = 10;
		} else {
			try {
				seconds = Integer.parseInt(arg);
				if(seconds == 0) {
					throw new NumberFormatException();
				}
			} catch(NumberFormatException e) {
				message.reply( "``Invalid number``").mentionRepliedUser(false).queue();
				return;
			}
		}
		AudioTrack track = AudioPlayerThread.getMusicManager().player.getPlayingTrack();
		track.setPosition(track.getPosition() + (1000*seconds)); // Lavaplayer handles values < 0 or > track length
	}
}