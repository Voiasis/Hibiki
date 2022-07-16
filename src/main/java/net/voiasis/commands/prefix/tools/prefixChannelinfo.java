package net.voiasis.commands.prefix.tools;

import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.Message;

public class prefixChannelinfo {
    public static void channelinfo(Message message, String[] args) {
        if (message.isFromGuild()) {
            if (message.getMentions().getChannels().toArray().length == 1) {
                Channel channel = message.getMentions().getChannels().get(0);
    
            } else if (message.getContentRaw().length() > args[0].length()) {
                try {
                    Channel channel = message.getJDA().getGuildChannelById(args[1]);
                    Channel pmChannel = message.getJDA().getPrivateChannelById(args[1]);
                    if (channel != null) {
                        if (channel.getType().isThread()) {
                            message.reply("thread").queue();
                        } else if (channel.getType().isAudio()) {
                            message.reply("audio").queue();
                        } else if (channel.getType().isMessage()) {
                            message.reply("message").queue();
                        } else if (channel.getType().isGuild()) {
                            message.reply("guild").queue();
                        } else {
                            message.reply("other").queue();
                        }
                    } else if (pmChannel != null){
                        message.reply("private").queue();
                    } else {
                        message.reply("Invaild channel ID provided or I can't see the channel.").mentionRepliedUser(false).queue();
                    }
                } catch (Exception e) {
                    message.reply("Invaild channel ID provided.").mentionRepliedUser(false).queue();
                }
            }
        }
    }
    private static void send() {

    }
}
