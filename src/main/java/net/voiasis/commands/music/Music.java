package net.voiasis.commands.music;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.voiasis.tools.music.AudioPlayerThread;

public class Music {
	public static boolean joined;
	public static void joinVoiceChannel(Message message, Guild guild) {
		if (joined) {
			return;
		}
		String channelName = message.getChannel().getName();
		VoiceChannel voiceChannel = guild.getVoiceChannels().stream().filter(vChan -> vChan.getName().equalsIgnoreCase(channelName)).findFirst().orElse(null);

		if(voiceChannel == null) {
			message.reply("Voice channel not found: " + channelName).mentionRepliedUser(false).queue();
			return;
		}

		//joinVoiceChannel(channel);
	}
	public static void joinVoiceChannel(Member member, Message message) {
		Guild guild = member.getGuild();
		if (joined) {
			return;
		}
		AudioChannelUnion voiceChannel = member.getVoiceState().getChannel();
		if(voiceChannel == null) {
			joinVoiceChannel(voiceChannel, guild, message);
			return;
		}
		joinVoiceChannel(voiceChannel, guild, message);
	}
	public static void joinVoiceChannel(Member member, InteractionHook interaction) {
		Guild guild = member.getGuild();
		if (joined) {
			return;
		}
		AudioChannelUnion voiceChannel = member.getVoiceState().getChannel();
		if(voiceChannel == null) {
			joinVoiceChannel(voiceChannel, guild, interaction);
			return;
		}
		joinVoiceChannel(voiceChannel, guild, interaction);
	}
	public static void joinVoiceChannel(AudioChannelUnion voiceChannel, Guild guild, Message message) {
		if (joined) {
			return;
		}
		if(voiceChannel == null) {
			return;
		}
		try {
			guild.getAudioManager().openAudioConnection(voiceChannel);
			joined = true;
		} catch(Exception e) {
			//Utils.printException(e);
			message.reply("Failed to join voice channel: " + voiceChannel + "\n"
					+ "Do I have the permission to join it?").mentionRepliedUser(false).queue();
		}
	}
	public static void joinVoiceChannel(AudioChannelUnion voiceChannel, Guild guild, InteractionHook interaction) {
		if (joined) {
			return;
		}
		if(voiceChannel == null) {
			return;
		}
		try {
			guild.getAudioManager().openAudioConnection(voiceChannel);
			joined = true;
		} catch(Exception e) {
			//Utils.printException(e);
			interaction.editOriginal("Failed to join voice channel: " + voiceChannel + "\n"
					+ "Do I have the permission to join it?").mentionRepliedUser(false).queue();
		}
	}
	static void leaveVoiceChannel(Guild guild) {
		guild.getAudioManager().closeAudioConnection();
		joined = false;
	}
	public static void onMessageReceived(MessageReceivedEvent event) {
		String messageStr = event.getMessage().getContentDisplay();
		MessageChannel channel = event.getChannel();
		Member author = event.getMember();
		Guild guild = event.getGuild();
		JDA jda = event.getJDA();
		Message message = event.getMessage();
		MessageChannel controlChannel = event.getChannel();
		if ( (channel == controlChannel || channel.getType() == ChannelType.PRIVATE) && messageStr.startsWith(Command.getPrefix()) && (!author.getId().equals(jda.getSelfUser().getId())) ) {
			//Log.debug("Got command from {}: {}", author.getName(), message);
			//BotLog.log("Got command from: " + author.getUser().getAsTag(), "Music", 1);
			String[] cmdarg = messageStr.substring(Command.getPrefix().length()).split(" ", 2);
			String cmd = cmdarg[0];
			String arg;
			try {
				arg = cmdarg[1];
			} catch (IndexOutOfBoundsException e) {
				arg = null;
			}
			boolean commandFound = false;
			for(Command command : Command.commands) {
				if (command.compare(cmd)) {
					commandFound = true;

					if(command.hasPermission(author)) {
						command.execute(arg, event.getMember(), guild, message);
					} else {
						message.reply(author.getAsMention() + " ``You are not allowed to use this command.``").mentionRepliedUser(false).queue();
					}
					break;
				}
			}
			if(!commandFound) {
				message.reply(author.getAsMention() + " ``Unknown command``").mentionRepliedUser(false).queue();
			}
		}
	}
	public static void stopPlayer(Guild guild) {
		AudioPlayerThread.stop();
		leaveVoiceChannel(guild);
		AudioPlayerThread.skipping = false;
		AudioPlayerThread.getMusicManager().scheduler.clear();
		AudioPlayerThread.setPaused(false);
	}
}