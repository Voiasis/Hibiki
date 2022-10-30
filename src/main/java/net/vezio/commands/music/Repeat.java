package net.vezio.commands.music;

import java.util.ArrayList;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.vezio.tools.music.AudioPlayerThread;

class Repeat extends Command {
	@Override
	public String getName() {
		return "repeat";
	}
	@Override
	public boolean isAdminOnly() {
		return true;
	}
	@Override
	public void execute(String arg, Member member, Guild guild, Message message) {
		int repeats;
		if(arg == null) {
			repeats = 1;
		} else {
			try {
				repeats = Integer.parseInt(arg);
				if(repeats < 1) {
					throw new NumberFormatException();
				}
			} catch(NumberFormatException e) {
				message.reply("``Invalid number``").mentionRepliedUser(false).queue();
				return;
			}
		}
		if(AudioPlayerThread.isPlaying()) {

			ArrayList<AudioTrack> songs = new ArrayList<>();
			songs.add(AudioPlayerThread.getMusicManager().player.getPlayingTrack());
			ArrayList<AudioTrack> upcoming = AudioPlayerThread.getMusicManager().scheduler.getList();
			if(!upcoming.isEmpty()) {
				for(int i = 0; i < upcoming.size(); i++) {
					songs.add(upcoming.get(i));
				}
			}
			for(int i = 0; i < repeats; i++) {
				for(int j = 0; j < songs.size(); j++) {
					AudioPlayerThread.addToPlaylist(songs.get(j).makeClone());
				}
			}
			message.reply( "``Repeated the playlist" + (repeats == 1 ? ".``" : (" " + repeats + " times.``") )).mentionRepliedUser(false).queue();
		} else {
			message.reply("``The playlist is empty. There is nothing to repeat.``").mentionRepliedUser(false).queue();
		}
	}
}