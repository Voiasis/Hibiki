package net.voiasis.commands.music;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.ChannelType;

class Help extends Command {
	@Override
	public String getName() {
		return "help";
	}
	@Override
	public boolean isAdminOnly() {
		return false;
	}
	@Override
	public void execute(String arg, Member member, Guild guild, Message message) {
		message.reply("**Music Commands:**\n"
				+ "```"
				+ getPrefix() + "list                           (Show the playlist)\n"
				+ getPrefix() + "play <link>            (Play given track now)\n"
				+ getPrefix() + "add <link>     (Add given track to playlist)\n"
				+ getPrefix() + "search <youtube video title>   (Plays the first video that was found)\n"
				+ getPrefix() + "pause                          (Pause or resume the current track)\n"
				+ getPrefix() + "stop                           (Stop the playback and clear the playlist)\n"
				+ getPrefix() + "volume                         (Change the playback volume)\n"
				+ getPrefix() + "next (<how many songs>)        (Skip one or more songs from the playlist)\n"
				+ getPrefix() + "seek <hours:minutes:seconds>   (Seek to the specified position)\n"
				+ getPrefix() + "jump (<how many seconds>)      (Jump forward in the current track)\n"
				+ getPrefix() + "repeat (<how many times>)      (Repeat the current playlist)\n"
				+ getPrefix() + "shuffle                        (Randomize the track order)\n"
				+ getPrefix() + "loop                           (Re add played track to the end of the playlist)\n"
				+ getPrefix() + "playtime                       (Time in currently playing song)\n"
				+ "```"
				+ (message.getChannel().getType() == ChannelType.PRIVATE ? ("\n**Guild:** " + guild.getName()) : "") ).mentionRepliedUser(false).queue();
	}
}