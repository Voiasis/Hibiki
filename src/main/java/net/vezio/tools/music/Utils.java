package net.vezio.tools.music;

import java.io.File;
import java.util.concurrent.TimeUnit;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.vezio.tools.BotLog;

public class Utils {
	private static String OS;
	public static String getOS() {
		if(OS != null) {
			return OS;
		}
		String tmp = System.getProperty("os.name").toLowerCase();
		if(tmp.equals(Values.OS_LINUX)) {
			return (OS = Values.OS_LINUX);
		} else if(tmp.startsWith(Values.OS_WINDOWS)) {
			return (OS = Values.OS_WINDOWS);
		} else {
			return (OS = Values.UNKNOWN_OS);
		}
	}
	public static boolean isUnknownOS() {
		return getOS().equals(Values.UNKNOWN_OS);
	}
	public static long timeToMS(int hours, int minutes, int seconds) {
		if(seconds > 59 || seconds < 0) {
			return -1;
		}
		if(minutes > 59 || minutes < 0) {
			return -1;
		}
		long s = (seconds + (60 * (minutes + (hours * 60))));
		return TimeUnit.SECONDS.toMillis(s);
	}
	public static String durationToUpTimeString(long duration) {
		TimeUnit scale = TimeUnit.MILLISECONDS;
		long days = scale.toDays(duration);
		duration -= TimeUnit.DAYS.toMillis(days);
		long hours = scale.toHours(duration);
		duration -= TimeUnit.HOURS.toMillis(hours);
		long minutes = scale.toMinutes(duration);
		duration -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = scale.toSeconds(duration);
		return String.format("%d days, %d hours, %d minutes, %d seconds", days, hours, minutes, seconds);
	}
	public static String durationToTackPosition(long duration) {
		TimeUnit scale = TimeUnit.MILLISECONDS;
		long minutes = scale.toMinutes(duration);
		duration -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = scale.toSeconds(duration);
		return String.format("%d:%s", minutes, String.format("%02d", seconds));
	}
	public static String getTrackName(AudioTrack track) {
		String sourceName = track.getSourceManager().getSourceName();
		if(sourceName.equals("local")) {
			AudioTrackInfo info = track.getInfo();
			String author = info.author;
			String title = info.title;
			if(!author.equals("Unknown artist") && !title.equals("Unknown title")) {
				return author + " - " + title;
			} else {
				return new File(track.getInfo().uri).getName();
			}
		} else if(sourceName.equals("http")) {
			return track.getIdentifier();
		} else {
			return track.getInfo().title;
		}
	}
	public static boolean isAdmin(Member member) {
		if (member.hasPermission(Permission.ADMINISTRATOR)) {
			return true;
		} else {
			return false;
		}
	}
	public static class ArgumentUtils {
		public static int getArgIndex(String[] args, String arg) {
			int ir = -1; // return -1 if args does not contain the argument
			for(int in = 0; in < args.length; in++) {
				if(args[in].equalsIgnoreCase(arg)) {
					ir = in;
					break;
				}
			}
			return ir;
		}
		public static boolean containsArg(String[] args, String arg) {
			return getArgIndex(args, arg) != -1;
		}
		// returns the value behind an argument or null
		public static String getArg(String[] args, String arg) {
			int i = getArgIndex(args, arg);
			if(i != -1) {
				try {
					String result = args[i + 1];
					if(!result.startsWith("--")) {
						return result;
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					BotLog.log("Invalid argument value: " + arg, "Music", 4);
				}
			}
			return null;
		}
	}
}