package net.voiasis;

import java.io.File;
import java.io.IOException;

import javax.security.auth.login.LoginException;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.voiasis.commands.music.Command;
import net.voiasis.commands.slash.handler.CommandsList;
import net.voiasis.events.onButtonInteractionEvent;
import net.voiasis.events.onChannelCreateEvent;
import net.voiasis.events.onGuildMemberJoinEvent;
import net.voiasis.events.onGuildMemberRemoveEvent;
import net.voiasis.events.onGuildMemberUpdateNicknameEvent;
import net.voiasis.events.onMessageBulkDeleteEvent;
import net.voiasis.events.onMessageDeleteEvent;
import net.voiasis.events.onMessageReactionAddEvent;
import net.voiasis.events.onMessageReactionRemoveEvent;
import net.voiasis.events.onMessageReceivedEvent;
import net.voiasis.events.onMessageUpdateEvent;
import net.voiasis.events.onSlashCommandInteractionEvent;
import net.voiasis.tools.BotConfig;
import net.voiasis.tools.BotLog;
import net.voiasis.tools.music.AudioPlayerThread;
import net.voiasis.tools.music.Config;
import net.voiasis.tools.music.Utils;

public class main {
    private static String version = "JDA5.A22";
    private static File configFile;
    static boolean skipping;
    private static JDA jda;
    public static void main(String[] args) throws LoginException, IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("taskkill /F /IM chromedriver.exe");
        BotLog.delete();
        BotLog.log("Logging in.", "BotStartup", 1);
        jda = JDABuilder.createDefault(BotConfig.get("TOKEN"))
        .setStatus(OnlineStatus.ONLINE)
        .setActivity(Activity.playing(version))
        .addEventListeners(new onButtonInteractionEvent(),
        new onGuildMemberJoinEvent(), new onGuildMemberRemoveEvent(),
        new onGuildMemberUpdateNicknameEvent(),
        new onMessageBulkDeleteEvent(),
        new onMessageDeleteEvent(),
        new onMessageReactionAddEvent(),
        new onMessageReactionRemoveEvent(),
        new onMessageReceivedEvent(),
        new onMessageUpdateEvent(),
        new onSlashCommandInteractionEvent(),
        new onChannelCreateEvent())
        .setChunkingFilter(ChunkingFilter.ALL)
        .setMemberCachePolicy(MemberCachePolicy.ALL)
        .enableIntents(GatewayIntent.GUILD_MEMBERS,
        GatewayIntent.GUILD_BANS,
        GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
        GatewayIntent.GUILD_WEBHOOKS,
        GatewayIntent.GUILD_INVITES,
        GatewayIntent.GUILD_VOICE_STATES,
        GatewayIntent.GUILD_PRESENCES,
        GatewayIntent.GUILD_MESSAGES,
        GatewayIntent.GUILD_MESSAGE_REACTIONS,
        GatewayIntent.GUILD_MESSAGE_TYPING,
        GatewayIntent.DIRECT_MESSAGES,
        GatewayIntent.DIRECT_MESSAGE_REACTIONS,
        GatewayIntent.DIRECT_MESSAGE_TYPING,
        GatewayIntent.MESSAGE_CONTENT)
        .enableCache(CacheFlag.ACTIVITY,
        CacheFlag.CLIENT_STATUS,
        CacheFlag.EMOJI,
        CacheFlag.MEMBER_OVERRIDES,
        CacheFlag.ONLINE_STATUS,
        CacheFlag.ROLE_TAGS,
        CacheFlag.VOICE_STATE)
        .build();
        try {
            jda.awaitReady();
            CommandsList.registerCommandsGlobal(jda);
            //CommandsList.registerCommands(jda, "1010933101939130462");
            if(!Config.isInitialized()) {
                configFile = Config.getDefaultConfig();
                Config.init(configFile);
            }
            AudioPlayerThread.init(jda);
		    Command.init();
        } catch (InterruptedException e) {
            BotLog.log(BotLog.getStackTraceString(e, jda), "main", 4);
        }
        BotLog.log("Login Success.", "BotStartup", 1);
        final int sleepTime = 500; // update game with 2 fps
		final int updatesDelay = 12000; // 12 seconds delay between game updates (Discord only allows to update the game 5 times per minute)
		Activity lastActivity = null;
		int updateDelay = 0;
		while(true) {
			do { // sleep at least one time
				try {
					Thread.sleep(sleepTime);
					if(updateDelay > 0) {
						updateDelay -= sleepTime;
					}
				} catch (InterruptedException e) {
					//e.printStackTrace();
				}
			} while(skipping); // sleep again during song skipping to avoid useless game updates
			if(updateDelay <= 0) { // check if we can update again

				// Update game if needed
				Activity activity;
				AudioTrack currentTrack = AudioPlayerThread.getCurrentTrack();
				if(currentTrack == null) {
					activity = null;
				} else {
					activity = Activity.listening(Utils.getTrackName(currentTrack));
				}
				if(lastActivity == null) {
					if(activity != null) {
						jda.getPresence().setActivity(activity);
						lastActivity = activity;
						updateDelay = updatesDelay;
						//Log.print("UPDATE GAME LGN: " + game.getName());
					}
				} else {
					if(activity == null) {
						jda.getPresence().setActivity(Activity.playing(version));
						lastActivity = null;
						updateDelay = updatesDelay;
						//Log.print("UPDATE GAME NULL");
					} else {
						if(!lastActivity.getName().equals(activity.getName())) {
							jda.getPresence().setActivity(activity);
							lastActivity = activity;
							updateDelay = updatesDelay;
							//Log.print("UPDATE GAME NE: " + game.getName());
						}
					}
				}
			}
		}
    }
    static void setActivity(Activity activity) {
		jda.getPresence().setActivity(activity);
		BotLog.log("Set Activity to: " + activity, "main", 1);
	}
}