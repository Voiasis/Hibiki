package net.vezio;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.vezio.commands.slash.handler.CommandsList;
import net.vezio.events.onButtonInteractionEvent;
import net.vezio.events.onChannelCreateEvent;
import net.vezio.events.onGuildMemberJoinEvent;
import net.vezio.events.onGuildMemberRemoveEvent;
import net.vezio.events.onGuildMemberUpdateNicknameEvent;
import net.vezio.events.onMessageBulkDeleteEvent;
import net.vezio.events.onMessageDeleteEvent;
import net.vezio.events.onMessageReactionAddEvent;
import net.vezio.events.onMessageReactionRemoveEvent;
import net.vezio.events.onMessageReceivedEvent;
import net.vezio.events.onMessageUpdateEvent;
import net.vezio.events.onSlashCommandInteractionEvent;
import net.vezio.tools.BotConfig;
import net.vezio.tools.BotLog;

public class main {
    public static void main(String[] args) throws LoginException, IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("taskkill /F /IM chromedriver.exe");
        BotLog.delete();
        BotLog.log("Logging in.", "BotStartup", 1);
        JDA jda = JDABuilder.createDefault(BotConfig.get("TOKEN"))
        .setStatus(OnlineStatus.ONLINE)
        .setActivity(Activity.watching("+help | JDA5.A22"))
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
        BotLog.log("Login Success.", "BotStartup", 1);
        try {
            Thread.sleep(10000);
            CommandsList.registerCommands(jda, "1010933101939130462");
        } catch (InterruptedException e) {
            BotLog.log(BotLog.getStackTraceString(e, jda), "main", 4);
        }
    }
}