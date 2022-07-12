package net.voiasis.commands.prefix;

import net.voiasis.commands.prefix.meOnly.prefixActivity;
import net.voiasis.commands.prefix.meOnly.prefixStatus;
import net.voiasis.commands.prefix.meOnly.prefixStop;
import net.voiasis.commands.prefix.moderation.prefixBan;
import net.voiasis.commands.prefix.moderation.prefixKick;
import net.voiasis.commands.prefix.moderation.prefixLock;
import net.voiasis.commands.prefix.moderation.prefixMute;
import net.voiasis.commands.prefix.moderation.prefixNickname;
import net.voiasis.commands.prefix.moderation.prefixNsfw;
import net.voiasis.commands.prefix.moderation.prefixPurge;
import net.voiasis.commands.prefix.moderation.prefixSlowmode;
import net.voiasis.commands.prefix.moderation.prefixSteal;
import net.voiasis.commands.prefix.moderation.prefixUnban;
import net.voiasis.commands.prefix.moderation.prefixUnlock;
import net.voiasis.commands.prefix.moderation.prefixUnmute;
import net.voiasis.commands.prefix.moderation.prefixWebhook;
import net.voiasis.commands.prefix.tools.prefixActinfo;
import net.voiasis.commands.prefix.tools.prefixAvatar;
import net.voiasis.commands.prefix.tools.prefixBanner;
import net.voiasis.commands.prefix.tools.prefixChannelinfo;
import net.voiasis.commands.prefix.tools.prefixDMreader;
import net.voiasis.commands.prefix.tools.prefixHelp;
import net.voiasis.commands.prefix.tools.prefixJumbo;
import net.voiasis.commands.prefix.tools.prefixPing;
import net.voiasis.commands.prefix.tools.prefixServerinfo;
import net.voiasis.commands.prefix.tools.prefixUptime;
import net.voiasis.commands.prefix.tools.prefixUserinfo;
import net.voiasis.commands.slash.prefixCreateTemp;
import net.voiasis.tools.BotConfig;
import net.voiasis.translation.langTranslate;
import net.dv8tion.jda.api.entities.Message;

public class BotCommands {
    public static void commands(Message message) {
        String[] args = message.getContentRaw().split("\\s+"); //split message into separate words
        String prefix = BotConfig.get("PREFIX"); //get bot prefix from config
        String command = args[0].toLowerCase().replace("+", "");
        //bot commands
        if (args[0].startsWith(prefix) && !message.getAuthor().isBot()) {
            switch (command) {
                case "help" : //TODO
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
                case "steal" : //breaks outside of guild
                prefixSteal.steal(message, args);
                break;
                case "purge" :
                prefixPurge.purge(message, args);
                break;
                case "clear" : //TODO
                //command to remove any bots messages with a input value for how far up to check
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
                case "nickname" :  //breaks outside of guild
                prefixNickname.nickname(message, args);
                break;
                case "dms" :
                prefixDMreader.dms(message, args);
                break;
                case "avatar" :
                prefixAvatar.avatar(message, args);
                break;
                case "lock" : //breaks outside of guild
                prefixLock.lock(message);
                break;
                case "unlock" : //breaks outside of guild
                prefixUnlock.unlock(message);
                break;
                case "slowmode" : //breaks outside of guild
                prefixSlowmode.slowmode(message, args);
                break;
                case "ping" :
                prefixPing.pingCreate(message);
                break;
                case "userinfo" :
                prefixUserinfo.userinfo(message, args);
                break;
                case "serverinfo" : //breaks outside of guild
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
                case "nsfw" : //breaks outside of guild
                prefixNsfw.nsfw(message);
                break;
                case "mute" : //breaks outside of guild
                prefixMute.mute(message, args);
                break;
                case "unmute" : //breaks outside of guild
                prefixUnmute.unmute(message, args);
                break;
                case "kick" : //breaks outside of guild
                prefixKick.kick(message, args);
                break;
                case "ban" : //breaks outside of guild
                prefixBan.ban(message, args);
                break;
                case "unban" : //breaks outside of guild
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
