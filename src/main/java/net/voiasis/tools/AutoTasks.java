package net.voiasis.tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;

public class AutoTasks {
    public static void tasks(Message message, Channel channel, User author, Member member, Guild guild) {
        String msgc = message.getContentRaw();
        String msgl = msgc.toLowerCase();
        String msgs = " " + msgl + " ";
        
        if (author.getId().equals("472899069136601099") && !message.getMentions().getCustomEmojis().isEmpty()) {
            int amount = message.getMentions().getCustomEmojis().size();
            while (amount > 0) {
                Emoji emoji = message.getMentions().getCustomEmojis().get(amount - 1);
                message.addReaction(emoji).queue();
                amount--;
                    
            }
        }
        //jenny emoji auto react
        if (message.getContentRaw().toLowerCase().contains("jenny") && !author.isBot()) {
            try {
                if (BotConfig.botConfig("AUTOREACT")) {
                    message.addReaction(message.getJDA().getEmojiById("942998087406878721")).queue();
                }
            } catch (IOException e) {
                BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "BotReply", 4);
            }
        }
        //auto reply when pinged
        try {
            if (message.getMentions().getUsers().get(0).toString().contains("Voiasis(472899069136601099)")) {
                BotLog.log("Ping detected, checking user and roles.", "BotReply", 2);
                if (author.isBot()) {
                    BotLog.log("Ping was from a bot, ignoring.", "BotReply", 2);
                } else if (!author.getId().equals("472899069136601099") &&
                !member.getRoles().toString().contains("724165923963142224") &&
                !member.getRoles().toString().contains("943188711640805376") &&
                !member.getRoles().toString().contains("931064644234268722") &&
                !member.getRoles().toString().contains("779058811465629746") &&
                !member.getRoles().toString().contains("888455491058036746") && 
                !member.getRoles().toString().contains("846351712368197632") &&
                !member.getRoles().toString().contains("846353111269507132") && 
                !member.getRoles().toString().contains("748957661097361530")) {
                    BotLog.log("User is not a bot or blacklisted. Checking BotConfig.txt.", "BotReply", 3);
                    if (message.getContentRaw().contains("472899069136601099")) {
                        if (BotConfig.botConfig("AUTOPING")) {
                            BotLog.active(message.getJDA());
                            BotLog.log("Sending reply message.", "BotReply", 2);
                            message.reply("https://c.tenor.com/3Xzu0bKWsG0AAAAd/ping-discord.gif").queue(m -> {
                                BotLog.inactive(message.getJDA());
                                m.delete().queueAfter(10, TimeUnit.SECONDS);
                            });
                        }
                    } else {
                        if (BotConfig.botConfig("AUTOREPLY")) {
                            BotLog.active(message.getJDA());
                            BotLog.log("Sending reply message.", "BotReply", 2);
                            message.reply("https://c.tenor.com/kJhT6VC2tzEAAAAS/pings-off-reply-pings-off.gif").queue(m -> {
                                BotLog.inactive(message.getJDA());
                                m.delete().queueAfter(10, TimeUnit.SECONDS);
                            });
                        }
                    }
                } else {
                    BotLog.log("User blacklisted. Ignoring.", "BotReply", 2);
                }
            }
        } catch(Exception ex) {
        }
        if (msgl.contains("retard")) {
            message.delete().queue(q -> message.getMember().timeoutFor(10, TimeUnit.SECONDS).queue());
        }
        if (msgs.contains(" ur ") && !member.hasPermission(Permission.ADMINISTRATOR)) {
            message.delete().queue();
        }
        if (!msgc.contains("voiasis") && !msgc.contains("agent_galactic")) {
        } else {
            if (!author.getId().equals("957675828198658080") && !author.getId().equals("472899069136601099")) {
                BotLog.active(message.getJDA());
                String channelS = channel.getAsMention();
                String authorS = author.getAsTag();
                String authorId = author.getId();
                String messageS = message.getContentRaw();
                String msgLink = message.getJumpUrl();
                message.getJDA().getGuildById("902397621015040020").getTextChannelById("953515276144619601")
                .sendMessage("``" + BotLog.timeAdv() + "`` <@472899069136601099>, " + authorS + " (" + authorId +") is talking about you in " + channelS + " (" + msgLink + ")").queue(m1 -> {
                    if (messageS.length() >= 2000) {
                        String message1 = messageS.substring(0, 2000);
                        String message2 = messageS.substring(2000);
                        m1.reply(message1).queue(m2 -> m2.reply(message2).queue(m3 -> BotLog.inactive(message.getJDA())));
                    } else {
                        m1.reply(message).queue(m2 -> BotLog.inactive(message.getJDA()));
                    }
                });
            }
        }
        //Dad joke
        try {
            if (!author.isBot() && message.getChannel().getId().equals("729671788187091024")) {
                if (BotConfig.botConfig("DADJOKE")) {
                    if (msgs.contains(" i'm ")) {
                        int stuff = msgl.indexOf("i'm");
                        String thing = msgc.substring(stuff + 3);
                        dadjoke(message, thing);
                    } else if (msgs.contains(" im ")) {
                        int stuff = msgl.indexOf("im");
                        String thing = msgc.substring(stuff + 2);
                        dadjoke(message, thing);
                    } else if (msgs.contains(" i am ")) {
                        int stuff = msgl.indexOf("i am");
                        String thing = msgc.substring(stuff + 4);
                        dadjoke(message, thing);
                    } else if (msgs.contains(" ima ")) {
                        int stuff = msgl.indexOf("im");
                        String thing = msgc.substring(stuff + 2);
                        dadjoke(message, thing);
                    } else if (msgs.contains(" imma ")) {
                        int stuff = msgl.indexOf("im");
                        String thing = msgc.substring(stuff + 2);
                        dadjoke(message, thing);
                    } else if (msgs.contains(" i'mma ")) {
                        int stuff = msgl.indexOf("i'm");
                        String thing = msgc.substring(stuff + 3);
                        dadjoke(message, thing);
                    }
                }
            }
        } catch (IOException e) {
        }
    }
    private static void dadjoke(Message message, String thing) {
        String reply = thing.replaceFirst(" ", "");
        if (!reply.isBlank()) {
            message.reply("Hi \"" + reply + "\", I am Ellie.").queue();
        }
    }
}
