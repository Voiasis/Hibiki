package net.realvezio.commands.prefix;

import net.dv8tion.jda.api.entities.Message;
import net.realvezio.commands.prefix.meOnly.prefixActivity;
import net.realvezio.commands.prefix.meOnly.prefixNickname;
import net.realvezio.commands.prefix.meOnly.prefixStatus;
import net.realvezio.commands.prefix.meOnly.prefixStop;
import net.realvezio.commands.prefix.moderation.prefixBan;
import net.realvezio.commands.prefix.moderation.prefixKick;
import net.realvezio.commands.prefix.moderation.prefixLock;
import net.realvezio.commands.prefix.moderation.prefixMute;
import net.realvezio.commands.prefix.moderation.prefixNsfw;
import net.realvezio.commands.prefix.moderation.prefixPurge;
import net.realvezio.commands.prefix.moderation.prefixSlowmode;
import net.realvezio.commands.prefix.moderation.prefixSteal;
import net.realvezio.commands.prefix.moderation.prefixUnban;
import net.realvezio.commands.prefix.moderation.prefixUnlock;
import net.realvezio.commands.prefix.moderation.prefixUnmute;
import net.realvezio.commands.prefix.moderation.prefixWebhook;
import net.realvezio.commands.prefix.tools.prefixActinfo;
import net.realvezio.commands.prefix.tools.prefixAvatar;
import net.realvezio.commands.prefix.tools.prefixBanner;
import net.realvezio.commands.prefix.tools.prefixChannelinfo;
import net.realvezio.commands.prefix.tools.prefixDMreader;
import net.realvezio.commands.prefix.tools.prefixHelp;
import net.realvezio.commands.prefix.tools.prefixJumbo;
import net.realvezio.commands.prefix.tools.prefixPing;
import net.realvezio.commands.prefix.tools.prefixServerinfo;
import net.realvezio.commands.prefix.tools.prefixUptime;
import net.realvezio.commands.prefix.tools.prefixUserinfo;
import net.realvezio.commands.slash.prefixCreateTemp;
import net.realvezio.tools.BotConfig;
import net.realvezio.translation.langTranslate;

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
            }
        }
    }
}
