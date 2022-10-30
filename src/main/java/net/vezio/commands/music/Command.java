package net.vezio.commands.music;

import java.util.ArrayList;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.vezio.tools.BotConfig;
import net.vezio.tools.music.Utils;

public abstract class Command {
	public static ArrayList<Command> commands;
	private static String prefix;
	public static void init() {
		prefix = BotConfig.get("PREFIX") + "music ";
		commands = new ArrayList<>();
		commands.add(new Add());
		commands.add(new Help());
		commands.add(new Jump());
		commands.add(new List());
		commands.add(new Next());
		commands.add(new Pause());
		commands.add(new Play());
		commands.add(new Playtime());
		commands.add(new Repeat());
		commands.add(new Search());
		commands.add(new Seek());
		commands.add(new Shuffle());
		commands.add(new Stop());
		commands.add(new Volume());
	}
	public static String getPrefix() {
		return prefix;
	}
	public boolean compare(String cmd) {
		return cmd.equalsIgnoreCase(getName());
	}
	public boolean hasPermission(Member member) {
		if(isAdminOnly()) {
			return Utils.isAdmin(member);
		} else {
			return true;
		}
	}
	public abstract String getName();
	public abstract boolean isAdminOnly();
	public abstract void execute(String arg, Member author, Guild guild, Message message);
}