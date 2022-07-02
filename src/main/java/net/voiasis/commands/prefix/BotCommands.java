package net.voiasis.commands.prefix;

import net.voiasis.commands.prefix.fun.prefixAvatar;
import net.voiasis.commands.prefix.fun.prefixDMreader;
import net.voiasis.commands.prefix.fun.prefixNickname;
import net.voiasis.commands.prefix.moderation.prefixPurge;
import net.voiasis.commands.prefix.tools.prefixInactive;
import net.voiasis.commands.prefix.tools.prefixSteal;
import net.voiasis.commands.prefix.tools.prefixStop;
import net.voiasis.commands.prefix.tools.prefixUptime;
import net.voiasis.commands.slash.prefixCreateTemp;
import net.voiasis.tools.BotConfig;
import net.voiasis.translation.langTranslate;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class BotCommands {
    public static void commands(Message message, User author, Member member, Guild guild) {
        String[] args = message.getContentRaw().split(" "); //split message into separate words
        String prefix = BotConfig.get("PREFIX"); //get bot prefix from config
        String command = args[0].toLowerCase().replace("+", "");
        //bot commands
        if (args[0].startsWith(prefix)) {
            switch (command) {
                case "help" :
                //TODO
                break;
                case "config":
                BotConfig.config(message, author, guild, args);
                break;
                case "inactive" :
                prefixInactive.inactive(message, author);
                break;
                case "test" :
                prefixTest.test(message, author, args);
                break;
                case "translate" :
                langTranslate.prefixTranslate(message, author, args);
                break;
                case "steal" :
                //prefixSteal.steal(guild, member, message, args); reanble when carl gets kicked TODO
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
                //prefixAvatar.avatar(message, author); reanble when carl gets kicked TODO
                break;
            }
        }
    }
}
