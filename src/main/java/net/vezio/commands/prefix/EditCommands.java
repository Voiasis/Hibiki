package net.vezio.commands.prefix;

import net.dv8tion.jda.api.entities.Message;
import net.vezio.commands.prefix.meOnly.prefixActivity;
import net.vezio.commands.prefix.meOnly.prefixAge;
import net.vezio.commands.prefix.meOnly.prefixDMreader;
import net.vezio.commands.prefix.meOnly.prefixNickname;
import net.vezio.commands.prefix.meOnly.prefixStatus;
import net.vezio.commands.prefix.meOnly.prefixStop;
import net.vezio.commands.prefix.moderation.prefixBan;
import net.vezio.commands.prefix.moderation.prefixKick;
import net.vezio.commands.prefix.moderation.prefixLock;
import net.vezio.commands.prefix.moderation.prefixMute;
import net.vezio.commands.prefix.moderation.prefixNsfw;
import net.vezio.commands.prefix.moderation.prefixPurge;
import net.vezio.commands.prefix.moderation.prefixSlowmode;
import net.vezio.commands.prefix.moderation.prefixSteal;
import net.vezio.commands.prefix.moderation.prefixUnban;
import net.vezio.commands.prefix.moderation.prefixUnlock;
import net.vezio.commands.prefix.moderation.prefixUnmute;
import net.vezio.commands.prefix.moderation.prefixWebhook;
import net.vezio.commands.prefix.tools.prefixActinfo;
import net.vezio.commands.prefix.tools.prefixAvatar;
import net.vezio.commands.prefix.tools.prefixBanner;
import net.vezio.commands.prefix.tools.prefixChannelinfo;
import net.vezio.commands.prefix.tools.prefixHelp;
import net.vezio.commands.prefix.tools.prefixJumbo;
import net.vezio.commands.prefix.tools.prefixLevel;
import net.vezio.commands.prefix.tools.prefixLyrics;
import net.vezio.commands.prefix.tools.prefixPing;
import net.vezio.commands.prefix.tools.prefixServerinfo;
import net.vezio.commands.prefix.tools.prefixUptime;
import net.vezio.commands.prefix.tools.prefixUserinfo;
import net.vezio.commands.slash.prefixCreateTemp;
import net.vezio.tools.BotConfig;
import net.vezio.translation.langTranslate;

public class EditCommands {
    public static void commands(Message message) {
        String[] args = message.getContentRaw().split("\\s+"); //split message into separate words
        String prefix = BotConfig.get("PREFIX"); //get bot prefix from config
        String command = args[0].toLowerCase().replace("+", "");
        //bot commands
        if (args[0].startsWith(prefix) && !message.getAuthor().isBot()) {
            switch (command) {
                case "help" :
                prefixHelp.help(message);
                break;
                case "config":
                BotConfig.config(message, args);
                break;
                case "test" :
                prefixTest.test(message, args);
                break;
                case "translate" :
                langTranslate.prefixTranslate(message, args);
                break;
                case "steal" :
                prefixSteal.steal(message, args);
                break;
                case "purge" :
                prefixPurge.purge(message, args);
                break;
                case "clear" :
                //command to remove any bots messages with a input value for how far up to check TODO
                break;
                case "create" : //temp TODO
                prefixCreateTemp.create(message);
                break;
                case "uptime" :
                prefixUptime.uptime(message);
                break;
                case "stop" :
                prefixStop.stop(message);
                break;
                case "nickname" :
                prefixNickname.nickname(message, args);
                break;
                case "dms" :
                prefixDMreader.dms(message, args);
                break;
                case "avatar" :
                prefixAvatar.avatar(message, args);
                break;
                case "lock" :
                prefixLock.lock(message);
                break;
                case "unlock" :
                prefixUnlock.unlock(message);
                break;
                case "slowmode" :
                prefixSlowmode.slowmode(message, args);
                break;
                case "ping" :
                //prefixPing.pingEdit(message);
                break;
                case "userinfo" :
                prefixUserinfo.userinfo(message, args);
                break;
                case "serverinfo" :
                prefixServerinfo.serverinfo(message);
                break;
                case "stat" :
                prefixStatus.status(message, args);
                break;
                case "act" :
                prefixActivity.activity(message, args);
                break;
                case "jumbo" :
                prefixJumbo.jumbo(message);
                break;
                case "nsfw" :
                prefixNsfw.nsfw(message);
                break;
                case "mute" :
                prefixMute.mute(message, args);
                break;
                case "unmute" :
                prefixUnmute.unmute(message, args);
                break;
                case "kick" :
                prefixKick.kick(message, args);
                break;
                case "ban" :
                prefixBan.ban(message, args);
                break;
                case "unban" :
                prefixUnban.unban(message, args);
                break;
                case "webhook" :
                prefixWebhook.webhook(message, args);
                break;
                case "chaninfo" :
                prefixChannelinfo.channelinfo(message, args);
                break;
                case "banner" :
                prefixBanner.banner(message, args);
                break;
                case "actinfo" :
                prefixActinfo.actinfo(message, args);
                break;
                case "level" :
                //prefixLevel.level(message, args);
                break;
                case "lyrics" :
                prefixLyrics.lyrics(message);
                break;
                case "age" :
                prefixAge.age(message);
                break;
            }
        }
    }
}
