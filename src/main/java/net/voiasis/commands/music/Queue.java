package net.voiasis.commands.music;

import java.util.ArrayList;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.voiasis.tools.music.AudioPlayerThread;
import net.voiasis.tools.music.Utils;
import net.voiasis.tools.music.Values;

class Queue extends Command {
	@Override
	public String getName() {
		return "queue";
	}
	@Override
	public boolean isAdminOnly() {
		return false;
	}
	@Override
	public void execute(String arg, Member member, Guild guild, Message message) {
		if(AudioPlayerThread.isPlaying()) {
			StringBuilder toSend = new StringBuilder(""
					+ " Currently playing:"
					+ "\n"
					+ "```"
					+ Utils.getTrackName(AudioPlayerThread.getMusicManager().player.getPlayingTrack())
					+ "```"
					+ "\n");
			if(AudioPlayerThread.getMusicManager().scheduler.getList().size() > 0) {
				toSend.append("Upcoming songs:"
						+ "\n"
						+ "```");
				ArrayList<AudioTrack> list = AudioPlayerThread.getMusicManager().scheduler.getList();
				for(int i = 0; i < list.size(); i++) {
					toSend.append("\n" + Utils.getTrackName(list.get(i)));
				}
				toSend.append("```");
			} else {
				toSend.append("``There are no upcoming songs.``");
			}
			if(toSend.length() > Values.MAX_MESSAGE_LENGTH) {
				final String ending = "...```";
				toSend.setLength((Values.MAX_MESSAGE_LENGTH - ending.length()));
				toSend.append(ending);
			}
			message.reply(toSend.toString()).mentionRepliedUser(false).queue();
		} else {
			// because gets cleared on stop
			// isPlaying() -> else:
			message.reply("The playlist is empty.").mentionRepliedUser(false).queue();
		}
	}
}