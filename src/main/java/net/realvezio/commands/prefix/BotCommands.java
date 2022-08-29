package net.realvezio.commands.prefix;

import net.dv8tion.jda.api.entities.Message;
import net.realvezio.commands.prefix.meOnly.prefixActivity;
import net.realvezio.commands.prefix.meOnly.prefixNickname;
import net.realvezio.commands.prefix.meOnly.prefixSaveHistory;
import net.realvezio.commands.prefix.meOnly.prefixStatus;
import net.realvezio.commands.prefix.meOnly.prefixStop;
import net.realvezio.commands.prefix.meOnly.prefixSupportPanel;
import net.realvezio.commands.prefix.moderation.prefixBan;
import net.realvezio.commands.prefix.moderation.prefixKick;
import net.realvezio.commands.prefix.moderation.prefixLock;
import net.realvezio.commands.prefix.moderation.prefixMute;
import net.realvezio.commands.prefix.moderation.prefixNsfw;
import net.realvezio.commands.prefix.moderation.prefixPurge;
import net.realvezio.commands.prefix.moderation.prefixRules;
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
import net.realvezio.commands.prefix.tools.prefixSpotify;
import net.realvezio.commands.prefix.tools.prefixUptime;
import net.realvezio.commands.prefix.tools.prefixUserinfo;
import net.realvezio.commands.prefix.tools.prefixYoutube;
import net.realvezio.commands.slash.prefixCreateTemp;
import net.realvezio.tools.BotConfig;
import net.realvezio.translation.langTranslate;

public class BotCommands {
    public static void commands(Message message) {
        String[] args = message.getContentRaw().toLowerCase().split("\\s+"); //split message into separate words
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
                //prefixPing.pingCreate(message);
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
                case "spotify" :
                prefixSpotify.spotify(message, args);
                break;
                case "rules" :
                prefixRules.rules(message, args);
                break;
                case "supportpanel" :
                prefixSupportPanel.panel(message);
                break;
                case "savehistory" :
                prefixSaveHistory.save(message);
                break;
                case "youtube" :
                prefixYoutube.youtube(message, args);
                break;
            }
        }
    }
}
