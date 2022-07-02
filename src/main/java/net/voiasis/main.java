package net.voiasis;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.voiasis.events.onGuildMemberUpdateNicknameEvent;
import net.voiasis.events.onMessageReactionAddEvent;
import net.voiasis.events.onMessageReactionRemoveEvent;
import net.voiasis.events.onMessageReceivedEvent;
import net.voiasis.events.onMessageUpdateEvent;
import net.voiasis.events.onSlashCommandInteractionEvent;
import net.voiasis.tools.BotConfig;
import net.voiasis.tools.BotLog;

public class main {
    public static void main(String[] args) throws LoginException, IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("taskkill /F /IM chromedriver.exe");
        BotLog.delete();
        BotLog.log("Logging in.", "BotStartup", 1);
        JDA jda = JDABuilder.createDefault(BotConfig.get("TOKEN"))
        .addEventListeners(new onMessageReceivedEvent(), new onMessageReactionAddEvent(), new onMessageUpdateEvent(), new onMessageReactionRemoveEvent(), new onSlashCommandInteractionEvent(), new onGuildMemberUpdateNicknameEvent())
        .setChunkingFilter(ChunkingFilter.ALL)
        .setMemberCachePolicy(MemberCachePolicy.ALL)
        .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_BANS, GatewayIntent.GUILD_EMOJIS_AND_STICKERS, GatewayIntent.GUILD_WEBHOOKS, GatewayIntent.GUILD_INVITES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_PRESENCES , GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MESSAGE_TYPING, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.DIRECT_MESSAGE_REACTIONS, GatewayIntent.DIRECT_MESSAGE_TYPING)
        .enableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.EMOJI, CacheFlag.MEMBER_OVERRIDES, CacheFlag.ONLINE_STATUS, CacheFlag.ROLE_TAGS, CacheFlag.VOICE_STATE)
        .build();
        BotLog.inactive(jda);
        BotLog.log("Login Success.", "BotStartup", 1);
    }
}