package net.vezio.commands.prefix.moderation;

import java.util.List;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.vezio.tools.BotLog;

public class prefixPurge {
    public static void purge(Message message, String[] args) {
        if (message.isFromGuild()) {
            if (message.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                if (message.getContentRaw().length() >= args[0].length() + 1) { 
                    int values = Integer.parseInt(args[1]) - 1;
                    if (message.getContentRaw().length() >= args[0].length() + args[1].length() + 2) {
                        if (message.getMentions().getUsers().toArray().length == 1) {
                        purge(message, values, message.getMentions().getUsers().get(0)); 
                        } else {
                            try {
                                User user = message.getJDA().retrieveUserById(args[2]).complete();
                                purge(message, values, user);
                            } catch (Exception e) {
                                BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "Purge", 4);
                                message.reply("Invaild user ID provided or can't find that user.").mentionRepliedUser(false).queue();
                            }
                        }
                    } else {
                    purge(message, values, null);
                    }
                }
            }
        }
    }
    private static void purge(Message message, int values, @Nullable User user) {
        if (user != null) {
            try {
                List<Message> messages = threadFix(message, values);
                int amount = messages.size();
                while (amount > 0) {
                    Message msg = messages.get(amount - 1);
                    if (!msg.getAuthor().equals(user)) {
                        messages.remove(amount - 1);
                        amount--;
                    } else {
                        amount--;
                    }
                }
                if (messages.size() > 1) {
                    if (message.getChannelType().name().contains("THREAD")) {
                        message.getChannel().asThreadChannel().deleteMessages(messages).queue();
                    } else {
                        message.getChannel().asTextChannel().deleteMessages(messages).queue();
                    }
                } else {
                    Message msg = messages.get(0);
                    msg.delete().queue();
                }
            } catch (Exception ex) {
                BotLog.log(BotLog.getStackTraceString(ex, message.getJDA()), "Purge", 4);
            } 
        } else {
            try {
                List<Message> messages = threadFix(message, values);
                if (messages.size() > 1) {
                    if (message.getChannelType().name().contains("THREAD")) {
                        message.getChannel().asThreadChannel().deleteMessages(messages).queue();
                    } else {
                        message.getChannel().asTextChannel().deleteMessages(messages).queue();
                    }
                } else {
                    Message msg = messages.get(0);
                    msg.delete().queue();
                }
            } catch (Exception ex) {
                BotLog.log(BotLog.getStackTraceString(ex, message.getJDA()), "Purge", 4);
            } 
        }     
    }
    private static List<Message> threadFix(Message message, int values) {
        if (message.getChannelType().name().contains("THREAD")) {
            List<Message> messages = message.getChannel().asThreadChannel().getHistory().retrievePast(values + 1).complete();
            return messages;
        } else {
            List<Message> messages = message.getChannel().asTextChannel().getHistory().retrievePast(values + 1).complete();
            return messages;
        }
    }
}
