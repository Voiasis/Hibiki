package net.voiasis.commands.slash.tools;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.ForumChannel;
import net.dv8tion.jda.api.entities.channel.concrete.NewsChannel;
import net.dv8tion.jda.api.entities.channel.concrete.StageChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.voiasis.auto.Grammar;
import net.voiasis.commands.slash.handler.CommandBuilder;

public class slashChannelinfo extends CommandBuilder {
    public slashChannelinfo() {
        setCommandData(Commands.slash("chaninfo", "UwU").addOption(OptionType.CHANNEL, "channel", "UwU", false));
    }
    @Override
    public void executeCommand(@NotNull SlashCommandInteractionEvent event) {
        OptionMapping channelOpt = event.getOption("channel");
        Channel channel = channelOpt == null ? null : channelOpt.getAsChannel();
        if (channel == null) {
            send(event.getChannel(), event);
        } else {
            send(channel, event);
        }
    }
    private static void send(Channel channel, SlashCommandInteractionEvent event) {
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
            Category category = event.getGuild().getCategoryById(channel.getId());
            int forumChannels = category.getForumChannels().size();
            int newsChannels = category.getNewsChannels().size();
            int stageChannels = category.getStageChannels().size();
            int textChannels = category.getTextChannels().size();
            int voiceChannels = category.getVoiceChannels().size();
            embed.addField("Channels", "<:textchannels:1010953804050616342> " + textChannels + " Text | <:voicechannels:1010953694868672622> " + voiceChannels + " Voice\r\n" + forumChannels + " Forum | " + newsChannels + " News | " + stageChannels + " Stage", false);
            event.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "FORUM" :
            ForumChannel forumChannel = event.getGuild().getForumChannelById(channel.getId());
            int tagAmount = forumChannel.getAvailableTags().size();
            int postAmount = forumChannel.getThreadChannels().size();
            embed.addField("Other Info", postAmount + " Active " + Grammar.grammar("Post", postAmount) + " | " + tagAmount + " " + Grammar.grammar("Tag", tagAmount), false);
            event.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "GROUP" : //like group dms maybe
            //Group group = 
            break;
            case "GUILD_NEWS_THREAD" :
            ThreadChannel newsThreadChannel = event.getGuild().getThreadChannelById(channel.getId());
            int newsThreadMemberAmount = newsThreadChannel.getMemberCount();
            int newsThreadMessageAmount = newsThreadChannel.getMessageCount();
            Member newsThreadOwner = newsThreadChannel.getOwner();
            embed.addField("Creator", newsThreadOwner.getAsMention(), false);
            embed.addField("Other Info", newsThreadMemberAmount + " " + Grammar.grammar("Member", newsThreadMemberAmount) + " | " + newsThreadMessageAmount + " " + Grammar.grammar("Message", newsThreadMessageAmount), false);
            event.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "GUILD_PRIVATE_THREAD" :
            ThreadChannel privateThreadChannel = event.getGuild().getThreadChannelById(channel.getId());
            int privateThreadMemberAmount = privateThreadChannel.getMemberCount();
            int privateThreadMessageAmount = privateThreadChannel.getMessageCount();
            Member privateThreadOwner = privateThreadChannel.getOwner();
            embed.addField("Creator", privateThreadOwner.getAsMention(), false);
            embed.addField("Other Info", privateThreadMemberAmount + " " + Grammar.grammar("Member", privateThreadMemberAmount) + " | " + privateThreadMessageAmount + " " + Grammar.grammar("Message", privateThreadMessageAmount), false);
            event.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "GUILD_PUBLIC_THREAD" :
            ThreadChannel publicThreadChannel = event.getGuild().getThreadChannelById(channel.getId());
            int publicThreadMemberAmount = publicThreadChannel.getMemberCount();
            int publicThreadMessageAmount = publicThreadChannel.getMessageCount();
            Member publicThreadOwner = publicThreadChannel.getOwner();
            embed.addField("Creator", publicThreadOwner.getAsMention(), false);
            embed.addField("Other Info", publicThreadMemberAmount + " " + Grammar.grammar("Member", publicThreadMemberAmount) + " | " + publicThreadMessageAmount + " " + Grammar.grammar("Message", publicThreadMessageAmount), false);
            event.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "NEWS" :
            NewsChannel newsChannel = event.getGuild().getNewsChannelById(channel.getId());

            event.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "PRIVATE" : //i think dms
            //PrivateChannel privateChannel = 
            break;
            case "STAGE" :
            StageChannel stageChannel = event.getGuild().getStageChannelById(channel.getId());

            event.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "TEXT" :
            TextChannel textChannel = event.getGuild().getTextChannelById(channel.getId());
            int textMembers = textChannel.getMembers().size();
            int threadChannels = textChannel.getThreadChannels().size();
            embed.addField("Other Info", textMembers + " " + Grammar.grammar("Member", textMembers) + " | " + threadChannels + " " + Grammar.grammar("Thread", threadChannels), false);
            event.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "VOICE" :
            VoiceChannel voiceChannel = event.getGuild().getVoiceChannelById(channel.getId());
            String bitrate = Integer.toString(voiceChannel.getBitrate()/1000) + "kbps";
            int connectedMembers = voiceChannel.getMembers().size();
            String RegionEmoji = voiceChannel.getRegion().getEmoji() + " " + voiceChannel.getRegion().getName();
            int userLimit = voiceChannel.getUserLimit();
            embed.addField("Region", RegionEmoji, false).addField("Bitrate", bitrate, false);
            if (userLimit != 0) {
                embed.addField("Other Info", connectedMembers + " Connected | " + userLimit + " User Limit", false);
                event.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            } else {
                embed.addField("Other Info", connectedMembers + " Connected | No User Limit", false);
                event.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            }
            break;
            case "UNKNOWN" : //new channel jda doesnt know about. probably time to update
            break;
        }
    }
}
