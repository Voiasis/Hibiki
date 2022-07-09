package net.voiasis.commands.prefix;

import net.voiasis.commands.prefix.moderation.prefixBan;
import net.voiasis.commands.prefix.moderation.prefixKick;
import net.voiasis.commands.prefix.moderation.prefixLock;
import net.voiasis.commands.prefix.moderation.prefixMute;
import net.voiasis.commands.prefix.moderation.prefixNsfw;
import net.voiasis.commands.prefix.moderation.prefixPurge;
import net.voiasis.commands.prefix.moderation.prefixSlowmode;
import net.voiasis.commands.prefix.moderation.prefixUnban;
import net.voiasis.commands.prefix.moderation.prefixUnlock;
import net.voiasis.commands.prefix.moderation.prefixUnmute;
import net.voiasis.commands.prefix.tools.prefixActivity;
import net.voiasis.commands.prefix.tools.prefixAvatar;
import net.voiasis.commands.prefix.tools.prefixDMreader;
import net.voiasis.commands.prefix.tools.prefixHelp;
import net.voiasis.commands.prefix.tools.prefixJumbo;
import net.voiasis.commands.prefix.tools.prefixNickname;
import net.voiasis.commands.prefix.tools.prefixPing;
import net.voiasis.commands.prefix.tools.prefixServerinfo;
import net.voiasis.commands.prefix.tools.prefixStatus;
import net.voiasis.commands.prefix.tools.prefixSteal;
import net.voiasis.commands.prefix.tools.prefixStop;
import net.voiasis.commands.prefix.tools.prefixUptime;
import net.voiasis.commands.prefix.tools.prefixUserinfo;
import net.voiasis.commands.slash.prefixCreateTemp;
import net.voiasis.tools.BotConfig;
import net.voiasis.translation.langTranslate;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class EditCommands {
    public static void commands(Message message, User author, Member member, Guild guild) {
        String[] args = message.getContentRaw().split("\\s+"); //split message into separate words
        String prefix = BotConfig.get("PREFIX"); //get bot prefix from config
        String command = args[0].toLowerCase().replace("+", "");
        //bot commands
        if (args[0].startsWith(prefix)) {
            switch (command) {
                case "help" :
                prefixHelp.help(message);
                break;
                case "config":
                BotConfig.config(message, author, guild, args);
                break;
                case "test" :
                prefixTest.test(message, args);
                break;
                case "translate" :
                langTranslate.prefixTranslate(message, author, args);
                break;
                case "steal" :
                prefixSteal.steal(guild, member, message, args);
                break;
                case "purge" :
                prefixPurge.purge(member, guild, message, args);
                break;
                case "clear" :
                //command to remove any bots messages with a input value for how far up to check TODO
                break;
                case "create" : //temp TODO
                prefixCreateTemp.create(member, message);
                break;
                case "uptime" :
                prefixUptime.uptime(message);
                break;
                case "stop" :
                prefixStop.stop(member);
                break;
                case "nickname" :
                prefixNickname.nickname(member, message, guild, args);
                break;
                case "dms" :
                prefixDMreader.dms(member, message, args);
                break;
                case "avatar" :
                prefixAvatar.avatar(message, author);
                break;
                case "lock" :
                prefixLock.lock(message, member);
                break;
                case "unlock" :
                prefixUnlock.unlock(message, member);
                break;
                case "slowmode" :
                prefixSlowmode.slowmode(member, message, args);
                break;
                case "ping" :
                prefixPing.pingEdit(message);
                break;
                case "userinfo" :
                prefixUserinfo.userinfo(message, member, args);
                break;
                case "serverinfo" :
                prefixServerinfo.serverinfo(guild, message);
                break;
                case "status" :
                prefixStatus.status(member, message, args);
                break;
                case "activity" :
                prefixActivity.activity(member, message, args);
                break;
                case "jumbo" :
                prefixJumbo.jumbo(message);
                break;
                case "nsfw" :
                prefixNsfw.nsfw(member, message);
                break;
                case "mute" :
                prefixMute.mute(member, message, args);
                break;
                case "unmute" :
                prefixUnmute.unmute(member, message, args);
                break;
                case "kick" :
                prefixKick.kick(member, message, args);
                break;
                case "ban" :
                prefixBan.ban(member, message, args);
                break;
                case "unban" :
                prefixUnban.unban(member, message, args);
                break;
            }
        }
    }
}
