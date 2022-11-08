package net.voiasis.commands.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.voiasis.tools.music.AudioPlayerThread;
import net.voiasis.tools.music.Utils;

public class Playtime extends Command {
	@Override
	public String getName() {
		return "playtime";
	}
	@Override
	public boolean isAdminOnly() {
		return false;
	}
	@Override
	public void execute(String arg, Member member, Guild guild, Message message) {
		if(!AudioPlayerThread.isPlaying()) {
			message.reply("No track is playing.").mentionRepliedUser(false).queue();
			return;
		}
		AudioTrack track = AudioPlayerThread.getCurrentTrack();
		long dur = track.getDuration();
		long pos = track.getPosition();
		message.reply("``" + Utils.durationToTackPosition(pos) + " / " + Utils.durationToTackPosition(dur) + "``").mentionRepliedUser(false).queue();
	}
}