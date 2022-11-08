package net.voiasis.tools.music;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;
import org.json.JSONTokener;

import net.voiasis.tools.BotLog;

public class Config {
	public static final String CONTROL_CHANNEL = "CONTROL_CHANNEL";
	public static final String VOICE_CHANNEL = "VOICE_CHANNEL";
	public static final String BOT_TOKEN = "BOT_TOKEN";
	public static final String COMMAND_PREFIX = "COMMAND_PREFIX";
	public static final String DISPLAY_SONG_AS_GAME = "DISPLAY_SONG_AS_GAME";
	public static final String UPDATE_CHECK_INTERVAL_HOURS = "UPDATE_CHECK_INTERVAL_HOURS";
	public static final String ADMINS_ROLE = "ADMINS_ROLE";
	public static final String ALLOW_CUSTOM_VOLUME = "ALLOW_CUSTOM_VOLUME";
	public static final String STARTING_VOLUME = "STARTING_VOLUME";
	public static final String ENABLE_MEDIA_CONTROL_KEYS = "ENABLE_MEDIA_CONTROL_KEYS";
	public static final String AUTO_RECONNECT = "AUTO_RECONNECT";
	public static final String USE_NATIVE_AUDIO_SYSTEM = "USE_NATIVE_AUDIO_SYSTEM";
	private static File APP_DIR = null;
	private static File DEFAULT_CONFIG = null;
	private static JSONObject defaults;
	private static File file;
	private static JSONObject json;
	private static boolean initialized;
	public static boolean isInitialized() {
		return initialized;
	}
	public static boolean init(File configFile) {
		initialized = false;
		file = configFile;
		defaults = new JSONObject();
		defaults.put(CONTROL_CHANNEL, "bot");
		defaults.put(COMMAND_PREFIX, "!");
		defaults.put(VOICE_CHANNEL, "Music");
		defaults.put(DISPLAY_SONG_AS_GAME, "true");
		defaults.put(UPDATE_CHECK_INTERVAL_HOURS, "24 #set to 0 to disable");
		defaults.put(ALLOW_CUSTOM_VOLUME, "false");
		defaults.put(STARTING_VOLUME, "100");
		defaults.put(ENABLE_MEDIA_CONTROL_KEYS, "false");
		defaults.put(AUTO_RECONNECT, "true");
		JSONObject read;
		try {
			read = new JSONObject(new JSONTokener(new FileReader(file)));
		} catch (Exception e) {
			// Failed to load config. Maybe it doesn't exist (already).
			// Create an empty one
			read = new JSONObject();
		}
		json = read;
		ArrayList<String> toAdd = null;
		Iterator<String> keys = defaults.keys();
		while(keys.hasNext()) {
			String key = keys.next();
			if(!read.has(key)) {
				if(toAdd == null) {
					toAdd = new ArrayList<>();
				}
				toAdd.add(key);
			}
		}
		if(toAdd == null) {
			initialized = true;
		} else {
			if(generate(toAdd)) {
				BotLog.log("Config file got generated or updated.", "Music | Config", 3);
			} else {
				BotLog.log("Failed to generate config. (Do you have write access here?)", "Music | Config", 4);
			}
		}
		return initialized;
	}
	public static String get(String key) {
		BotLog.log("Reading config key: " + key, "Music | Config", 1);
		return toValue(json.getString(key));
	}
	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(get(key));
	}
	public static void set(String key, String value) {
		setRaw(key, toValue(value));
	}
	public static void set(String key, int value) {
		set(key, String.valueOf(value));
	}
	private static void setRaw(String key, String value) {
		json.put(key, value);
		BotLog.log("Config key set: " + key, "Music | Config", 1);
	}
	private static boolean generate(ArrayList<String> toAdd) {
		for(String key : toAdd) {
			setRaw(key, defaults.getString(key));
		}
		return save();
	}
	public static boolean save() {
		try {
			json.write(new FileWriter(file), 2, 0).close();
			BotLog.log("Config saved", "Music | Config", 1);
			return true;
		} catch (Exception e) {
			BotLog.log("Failed to save config file", "Music | Config", 4);
			return false;
		}
	}
	private static String toValue(String v) {
		try {
			return v.split(Values.CONFIG_COMMENT)[0].trim();
		} catch (Exception e) {
			BotLog.log("Invalid config value", "Music | Config", 1);
		}
		return "";
	}
	public static File getAppDir() {
		if(APP_DIR != null) {
			return APP_DIR;
		}
		switch(Utils.getOS()) {
		case Values.OS_LINUX:
			APP_DIR = new File(System.getProperty("user.home"), ("." + Values.BOT_NAME.toLowerCase()));
			break;
		case Values.OS_WINDOWS:
			APP_DIR = new File(System.getenv("APPDATA"), Values.BOT_NAME);
			break;
		default:
			APP_DIR = new File(System.getProperty("user.dir"), Values.BOT_NAME.toLowerCase());
			break;
		}
		if(!APP_DIR.exists()) {
			if(!APP_DIR.mkdir()) {
			}
		}
		return APP_DIR;
	}
	public static File getDefaultConfig() {
		if(DEFAULT_CONFIG != null) {
			return DEFAULT_CONFIG;
		}
		DEFAULT_CONFIG = new File(getAppDir(), Values.DEFAULT_CONFIG_FILENAME);
		return DEFAULT_CONFIG;
	}
}