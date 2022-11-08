package net.voiasis.tools.music;

import java.util.List;

import com.github.topisenpai.lavasrc.applemusic.AppleMusicSourceManager;
import com.github.topisenpai.lavasrc.deezer.DeezerAudioSourceManager;
import com.github.topisenpai.lavasrc.spotify.SpotifySourceManager;
import com.github.topisenpai.lavasrc.yandexmusic.YandexMusicSourceManager;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.voiasis.commands.music.Music;
import net.voiasis.tools.BotConfig;
import net.voiasis.tools.BotLog;

public class AudioPlayerThread {
	// currently everything for only 1 server (static)
	public static boolean skipping;
	public static boolean loop;
	private static AudioPlayerManager playerManager;
	private static GuildMusicManager musicManager;
	public static void init(JDA jda) {
		playerManager = new DefaultAudioPlayerManager();
		playerManager.registerSourceManager(new SpotifySourceManager(null, BotConfig.get("SPOTIFYID"), BotConfig.get("SPOTIFYSECRET"), "us", playerManager));
		playerManager.registerSourceManager(new AppleMusicSourceManager(null, "us", playerManager));
		playerManager.registerSourceManager(new DeezerAudioSourceManager("..."));
		playerManager.registerSourceManager(new YandexMusicSourceManager("..."));
		AudioSourceManagers.registerRemoteSources(playerManager);
		AudioSourceManagers.registerLocalSource(playerManager);
		Guild guild = jda.getGuildCache().getElementById("1010933101939130462");
		initGuildAudioPlayer(guild);
		BotLog.log("Initialized audio player.", "AudioPlayerThread", 1);
	}
	public static GuildMusicManager getMusicManager() {
		return musicManager;
	}
	public static AudioPlayer getPlayer() {
		return getMusicManager().player;
	}
	public static AudioTrack getCurrentTrack() {
		return getPlayer().getPlayingTrack();
	}
	private static synchronized void initGuildAudioPlayer(Guild guild) {
		if (musicManager == null) {
			musicManager = new GuildMusicManager(playerManager);
		}
		guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());
		// try to set the starting volume
		setVolume("75");
	}
	private static void loadAndPlay(final Message message, final String trackUrl, boolean direct, boolean quiet) {
		BotLog.log("Loading track ... play direct: " + direct + " URL: " + trackUrl, "AudioPlayerThread", 1);
		playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
			@Override
			public void trackLoaded(AudioTrack track) {
				if(direct) {
					if(track.getSourceManager().getSourceName().equals("youtube")) {
						playDirect(track, getYouTubeStartTimeMS(trackUrl));
					} else {
						playDirect(track, 0);
					}
					// quiet check not needed, since there will be never more than one track / message
					message.editMessage("Now playing: ``" + Utils.getTrackName(track) + "``").mentionRepliedUser(false).queue();
				} else {
					addToPlaylist(track);
					if(!quiet) {
						message.editMessage("Added track to queue: ``" + Utils.getTrackName(track) + "``").mentionRepliedUser(false).queue();
					}
				}
			}
			@Override
			public void playlistLoaded(AudioPlaylist playlist) {
				if(direct) {
					List<AudioTrack> tracks = playlist.getTracks();
					AudioTrack directTrack = tracks.get(0);
					playDirect(directTrack, 0);
					message.editMessage("Now playing: ``" + Utils.getTrackName(directTrack) + "``").mentionRepliedUser(false).queue();
					// Add all the other tracks of the playlist too
					int trackcount = tracks.size();
					if(trackcount > 1) {
						for(int i = 1; i < trackcount; i++) {
							AudioTrack track = tracks.get(i);
							addToPlaylist(track);
						}
					}
				} else {
					if(trackUrl.startsWith(Values.SEARCH_PREFIX_YOUTUBE)) {

						AudioTrack firstSearchResult = playlist.getTracks().get(0);
						addToPlaylist(firstSearchResult);
						if(!quiet) {
							message.editMessage("Added track to queue: ``" + Utils.getTrackName(firstSearchResult) + "``").mentionRepliedUser(false).queue();
						}
					} else {
						for (AudioTrack track : playlist.getTracks()) {
							addToPlaylist(track);
						}
						if(!quiet) {
							message.editMessage("Added playlist to queue: ``" + playlist.getName() + "``").mentionRepliedUser(false).queue();
						}
					}
				}
			}
			@Override
			public void noMatches() {
				if(trackUrl.startsWith(Values.SEARCH_PREFIX_YOUTUBE)) {
					message.editMessage("No search results for: ``" + trackUrl.substring(Values.SEARCH_PREFIX_YOUTUBE.length()) + "``").mentionRepliedUser(false).queue();
					return;
				}
				message.editMessage("Nothing found by: ``" + trackUrl + "``").mentionRepliedUser(false).queue();
			}
			@Override
			public void loadFailed(FriendlyException exception) {
				if(!quiet) {
					message.editMessage("Could not play: ``" + exception.getMessage() + "``").mentionRepliedUser(false).queue();
				}
			}
		});
	}
	private static void loadAndPlay(final InteractionHook interaction, final String trackUrl, boolean direct, boolean quiet) {
		BotLog.log("Loading track ... play direct: " + direct + " URL: " + trackUrl, "AudioPlayerThread", 1);
		playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
			@Override
			public void trackLoaded(AudioTrack track) {
				if(direct) {
					if(track.getSourceManager().getSourceName().equals("youtube")) {
						playDirect(track, getYouTubeStartTimeMS(trackUrl));
					} else {
						playDirect(track, 0);
					}
					// quiet check not needed, since there will be never more than one track / message
					interaction.editOriginal("Now playing: ``" + Utils.getTrackName(track) + "``").mentionRepliedUser(false).queue();
				} else {
					addToPlaylist(track);
					if(!quiet) {
						interaction.editOriginal("Added track to queue: ``" + Utils.getTrackName(track) + "``").mentionRepliedUser(false).queue();
					}
				}
			}
			@Override
			public void playlistLoaded(AudioPlaylist playlist) {
				if(direct) {
					List<AudioTrack> tracks = playlist.getTracks();
					AudioTrack directTrack = tracks.get(0);
					playDirect(directTrack, 0);
					interaction.editOriginal("Now playing: ``" + Utils.getTrackName(directTrack) + "``").mentionRepliedUser(false).queue();
					// Add all the other tracks of the playlist too
					int trackcount = tracks.size();
					if(trackcount > 1) {
						for(int i = 1; i < trackcount; i++) {
							AudioTrack track = tracks.get(i);
							addToPlaylist(track);
						}
					}
				} else {
					if(trackUrl.startsWith(Values.SEARCH_PREFIX_YOUTUBE)) {

						AudioTrack firstSearchResult = playlist.getTracks().get(0);
						addToPlaylist(firstSearchResult);
						if(!quiet) {
							interaction.editOriginal("Added track to queue: ``" + Utils.getTrackName(firstSearchResult) + "``").mentionRepliedUser(false).queue();
						}
					} else {
						for (AudioTrack track : playlist.getTracks()) {
							addToPlaylist(track);
						}
						if(!quiet) {
							interaction.editOriginal("Added playlist to queue: ``" + playlist.getName() + "``").mentionRepliedUser(false).queue();
						}
					}
				}
			}
			@Override
			public void noMatches() {
				if(trackUrl.startsWith(Values.SEARCH_PREFIX_YOUTUBE)) {
					interaction.editOriginal("No search results for: ``" + trackUrl.substring(Values.SEARCH_PREFIX_YOUTUBE.length()) + "``").mentionRepliedUser(false).queue();
					return;
				}
				interaction.editOriginal("Nothing found by: ``" + trackUrl + "``").mentionRepliedUser(false).queue();
			}
			@Override
			public void loadFailed(FriendlyException exception) {
				if(!quiet) {
					interaction.editOriginal("Could not play: ``" + exception.getMessage() + "``").mentionRepliedUser(false).queue();
				}
			}
		});
	}
	public static void playDirect(InteractionHook q, String arg, boolean quiet, AudioChannelUnion voiceChannel) {
		Guild guild = voiceChannel.getGuild();
		Music.joinVoiceChannel(voiceChannel, guild, q);
		if(!Music.joined) {
			return;
		}
		loadAndPlay(q, arg, true, quiet);
    }
	public static void playDirect(Message message, String arg, boolean quiet, AudioChannelUnion voiceChannel) {
		Guild guild = voiceChannel.getGuild();
		Music.joinVoiceChannel(voiceChannel, guild, message);
		if(!Music.joined) {
			return;
		}
		loadAndPlay(message, arg, true, quiet);
	}
	private static long getYouTubeStartTimeMS(String trackUrl) {
		if(trackUrl.contains("youtu.be")) {
			trackUrl = trackUrl.replace("?t=", "&t="); // "?" used in youtu.be links
		}
		if (trackUrl.indexOf("&t=") == -1) {
			return 0;
		}
		int ms = 0;
		trackUrl = trackUrl.substring(trackUrl.indexOf("&t=") + 3);
		int ti = 0;
		for (int i = 0; i < trackUrl.length(); i++) {
			String sub = trackUrl.substring(i, i+1);
			if (sub.equalsIgnoreCase("h")) {
				ms += ti * 360000;
				ti = 0;
			} else if (sub.equalsIgnoreCase("m")) {
				ms += ti * 60000;
				ti = 0;
			} else if (sub.equalsIgnoreCase("s")) {
				ms += ti * 1000;
				ti = 0;
			} else {
				ti *= 10;
				ti += Integer.parseInt(sub);
			}
		}
		return ms;
	}
	public static void addToPlaylist(AudioTrack track) {
		//connectToFirstVoiceChannel(guild.getAudioManager());
		getMusicManager().scheduler.queue(track);
	}
	public static void addToPlaylist(String arg, boolean quiet, Message message) {
		loadAndPlay(message, arg, false, quiet);
	}
	public static void addToPlaylist(String arg, boolean quiet, InteractionHook interaction) {
		loadAndPlay(interaction, arg, false, quiet);
	}
	static void playDirect(AudioTrack track, long startingPositionMS) {
		//connectToFirstVoiceChannel(guild.getAudioManager());
		getPlayer().startTrack(track, false);

		if(startingPositionMS > 0) {
			getPlayer().getPlayingTrack().setPosition(startingPositionMS);
		}
	}
	public static void stop() {
		getPlayer().stopTrack();
	}
	public static void setPaused(boolean p) {
		getPlayer().setPaused(p);
	}
	public static boolean isPaused() {
		return getPlayer().isPaused();
	}
	public static void togglePause() {
		setPaused(!isPaused());
	}
	public static boolean isPlaying() {
		return getPlayer().getPlayingTrack() != null;
	}
	public static int setVolume(String v) {
		if(!Config.getBoolean(Config.ALLOW_CUSTOM_VOLUME)) {
			return Values.SET_VOLUME_ERROR_CUSTOM_VOLUME_NOT_ALLOWED;
		}
		int volume;
		try {
			volume = Integer.parseInt(v);

			if(volume > Values.MAX_VOLUME || volume < 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			//Log.debug("Invalid volume: {}", v);
			BotLog.log("Invalid volume: " + v, "AudioPlayerThread", 1);
			return Values.SET_VOLUME_ERROR_INVALID_NUMBER;
		}
		getPlayer().setVolume(volume);
		//Log.debug("Player volume set to: {}", musicManager.player.getVolume());
		BotLog.log("Player volume set to: " + musicManager.player.getVolume(), "AudioPlayerThread", 1);
		return Values.SET_VOLUME_SUCCESSFULLY;
	}
	public static int getVolume() {
		return getPlayer().getVolume();
	}
}