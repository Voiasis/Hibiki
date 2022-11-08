package net.voiasis.commands.prefix.tools;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Invite.Group;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.ForumChannel;
import net.dv8tion.jda.api.entities.channel.concrete.NewsChannel;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.entities.channel.concrete.StageChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.voiasis.auto.Grammar;
import net.voiasis.tools.BotLog;

public class prefixChannelinfo {
    public static void channelinfo(Message message, String[] args) {
        if (message.isFromGuild()) {
            if (message.getMentions().getChannels().toArray().length == 1) {
                Channel channel = message.getMentions().getChannels().get(0);
                send(channel, message);
            } else if (message.getContentRaw().length() > args[0].length()) {
                try {
                    Channel channel = message.getJDA().getGuildChannelById(args[1]);
                    send(channel, message);
                } catch (Exception e) {
                    BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "test", 4);
                    message.reply("Invaild channel ID provided or can't find that channel.").mentionRepliedUser(false).queue();
                }
            } else {
                Channel channel = message.getChannel();
                send(channel, message);
            }
        }
    }
    private static void send(Channel channel, Message message) {
        String typeName = channel.getType().name();
        String channelName = channel.getName();
        String channelId = channel.getId();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime createTime = channel.getTimeCreated().toLocalDateTime();
        String month = Integer.toString(channel.getTimeCreated().getMonthValue());
        String day = Integer.toString(channel.getTimeCreated().getDayOfMonth());
        String year = Integer.toString(channel.getTimeCreated().getYear());
        String creation = month + "/" + day + "/" + year;
        long createDiff = ChronoUnit.DAYS.between(createTime, now);
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.setTitle(channelName);
        embed.addField("Channel Type", "<:settings:1010953881963986944> " + typeName, false);
        embed.addField("Channel ID", "<:idblock:1010954027120480307> " + channelId, false);
        embed.addField("Created", "<:time:1010953759175749683> " + creation + " (" + createDiff + " " + Grammar.grammar("day", createDiff) + " ago)", false);
        //message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
        switch (typeName) {
            case "CATEGORY" :
            Category category = message.getGuild().getCategoryById(channel.getId());
            int forumChannels = category.getForumChannels().size();
            int newsChannels = category.getNewsChannels().size();
            int stageChannels = category.getStageChannels().size();
            int textChannels = category.getTextChannels().size();
            int voiceChannels = category.getVoiceChannels().size();
            embed.addField("Channels", "<:textchannels:1010953804050616342> " + textChannels + " Text | <:voicechannels:1010953694868672622> " + voiceChannels + " Voice\r\n" + forumChannels + " Forum | " + newsChannels + " News | " + stageChannels + " Stage", false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "FORUM" :
            ForumChannel forumChannel = message.getGuild().getForumChannelById(channel.getId());
            int tagAmount = forumChannel.getAvailableTags().size();
            int postAmount = forumChannel.getThreadChannels().size();
            embed.addField("Other Info", postAmount + " Active " + Grammar.grammar("Post", postAmount) + " | " + tagAmount + " " + Grammar.grammar("Tag", tagAmount), false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "GROUP" : //like group dms maybe
            //Group group = 
            break;
            case "GUILD_NEWS_THREAD" :
            ThreadChannel newsThreadChannel = message.getGuild().getThreadChannelById(channel.getId());
            int newsThreadMemberAmount = newsThreadChannel.getMemberCount();
            int newsThreadMessageAmount = newsThreadChannel.getMessageCount();
            Member newsThreadOwner = newsThreadChannel.getOwner();
            embed.addField("Creator", newsThreadOwner.getAsMention(), false);
            embed.addField("Other Info", newsThreadMemberAmount + " " + Grammar.grammar("Member", newsThreadMemberAmount) + " | " + newsThreadMessageAmount + " " + Grammar.grammar("Message", newsThreadMessageAmount), false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "GUILD_PRIVATE_THREAD" :
            ThreadChannel privateThreadChannel = message.getGuild().getThreadChannelById(channel.getId());
            int privateThreadMemberAmount = privateThreadChannel.getMemberCount();
            int privateThreadMessageAmount = privateThreadChannel.getMessageCount();
            Member privateThreadOwner = privateThreadChannel.getOwner();
            embed.addField("Creator", privateThreadOwner.getAsMention(), false);
            embed.addField("Other Info", privateThreadMemberAmount + " " + Grammar.grammar("Member", privateThreadMemberAmount) + " | " + privateThreadMessageAmount + " " + Grammar.grammar("Message", privateThreadMessageAmount), false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "GUILD_PUBLIC_THREAD" :
            ThreadChannel publicThreadChannel = message.getGuild().getThreadChannelById(channel.getId());
            int publicThreadMemberAmount = publicThreadChannel.getMemberCount();
            int publicThreadMessageAmount = publicThreadChannel.getMessageCount();
            Member publicThreadOwner = publicThreadChannel.getOwner();
            embed.addField("Creator", publicThreadOwner.getAsMention(), false);
            embed.addField("Other Info", publicThreadMemberAmount + " " + Grammar.grammar("Member", publicThreadMemberAmount) + " | " + publicThreadMessageAmount + " " + Grammar.grammar("Message", publicThreadMessageAmount), false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "NEWS" :
            NewsChannel newsChannel = message.getGuild().getNewsChannelById(channel.getId());

            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "PRIVATE" : //i think dms
            //PrivateChannel privateChannel = 
            break;
            case "STAGE" :
            StageChannel stageChannel = message.getGuild().getStageChannelById(channel.getId());

            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "TEXT" :
            TextChannel textChannel = message.getGuild().getTextChannelById(channel.getId());
            int textMembers = textChannel.getMembers().size();
            int threadChannels = textChannel.getThreadChannels().size();
            embed.addField("Other Info", textMembers + " " + Grammar.grammar("Member", textMembers) + " | " + threadChannels + " " + Grammar.grammar("Thread", threadChannels), false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "VOICE" :
            VoiceChannel voiceChannel = message.getGuild().getVoiceChannelById(channel.getId());
            String bitrate = Integer.toString(voiceChannel.getBitrate()/1000) + "kbps";
            int connectedMembers = voiceChannel.getMembers().size();
            String RegionEmoji = voiceChannel.getRegion().getEmoji() + " " + voiceChannel.getRegion().getName();
            int userLimit = voiceChannel.getUserLimit();
            embed.addField("Region", RegionEmoji, false).addField("Bitrate", bitrate, false);
            if (userLimit != 0) {
                embed.addField("Other Info", connectedMembers + " Connected | " + userLimit + " User Limit", false);
                message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            } else {
                embed.addField("Other Info", connectedMembers + " Connected | No User Limit", false);
                message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            }
            break;
            case "UNKNOWN" : //new channel jda doesnt know about. probably time to update
            break;
        }
    }
}
