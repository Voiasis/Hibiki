package net.vezio.commands.prefix.tools;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.vezio.auto.Grammar;

public class prefixServerinfo {
    public static void serverinfo(Message message) {
        if (message.isFromGuild()) {
            Guild guild = message.getGuild();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime createTime = guild.getTimeCreated().toLocalDateTime();
            String month = Integer.toString(guild.getTimeCreated().getMonthValue());
            String day = Integer.toString(guild.getTimeCreated().getDayOfMonth());
            String year = Integer.toString(guild.getTimeCreated().getYear());
            String creation = month + "/" + day + "/" + year;
            String owner = guild.getOwner().getAsMention();
            String regionEmoji = guild.getVoiceChannels().get(0).getRegion().getEmoji();
            String regionName = guild.getVoiceChannels().get(0).getRegion().getName();
            long createDiff = ChronoUnit.DAYS.between(createTime, now);
            long bots = guild.getMembers().stream().filter(m -> m.getUser().isBot()).count();
            long humans = guild.getMemberCount() - bots;
            long boosts = guild.getBoostCount();
            long online = guild.getMembers().stream().filter(u -> u.getOnlineStatus() == OnlineStatus.ONLINE).count();
            long onlineBots = guild.getMembers().stream().filter(m -> m.getUser().isBot()).filter(u -> u.getOnlineStatus() == OnlineStatus.ONLINE).count();
            long dnd = guild.getMembers().stream().filter(u -> u.getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB).count();
            long dndBots = guild.getMembers().stream().filter(m -> m.getUser().isBot()).filter(u -> u.getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB).count();
            long idle = guild.getMembers().stream().filter(u -> u.getOnlineStatus() == OnlineStatus.IDLE).count();
            long idleBots = guild.getMembers().stream().filter(m -> m.getUser().isBot()).filter(u -> u.getOnlineStatus() == OnlineStatus.IDLE).count();
            long onlineTotal = online + dnd + idle - bots;
            long onlineTotalBots = onlineBots + dndBots + idleBots;
            long offline = guild.getMemberCount() - onlineTotal - bots;
            long offlineBots = guild.getMembers().stream().filter(m -> m.getUser().isBot()).count() - onlineTotalBots;
            long text = guild.getTextChannelCache().size();
            long voice = guild.getVoiceChannelCache().size();
            long roles = guild.getRoleCache().size();
            long categories = guild.getCategoryCache().size();
            long emojis = guild.getEmojiCache().size();
            long threads = guild.getThreadChannelCache().size();
            long bans = guild.retrieveBanList().complete().size();
            long stickers = guild.getStickers().size();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            embed.setTitle(guild.getName(), null);
            embed.setDescription(guild.getDescription());
            embed.addField("Server ID", "<:idblock:1010954027120480307> " + guild.getId(), true).addField("Owner", "<:mention:1010953969155178509> " + owner, true);
            embed.addField("Created", "<:time:1010953759175749683> " + creation + " (" + createDiff + " " + Grammar.grammar("day", createDiff) + " ago)", false);
            embed.addField("Members", "<:members:1010953983361286216> " + humans + " Total | <:online:1010953927157629018> " + onlineTotal + " | <:offline:1010953954672267426> " + offline, true);
            embed.addField("Bots", "<:settings:1010953881963986944> " + bots + " Total | <:online:1010953927157629018> " + onlineTotalBots + " | <:offline:1010953954672267426> " + offlineBots, true);
            embed.addField("Channels", "<:textchannels:1010953804050616342> " + text + " Text | <:voicechannels:1010953694868672622> " + voice + " Voice | <:threads:1010953784786173963> " + threads + " " + Grammar.grammar("Thread", threads) + " | <:categories:1010954067595513939> " + categories + " " + Grammar.grammarY("Category", categories), false);
            embed.addField("Customs", "<:roles:1010953898854469702> " + roles + " " + Grammar.grammar("Role", roles) + " | <:reaction:1010953912444006511> " + emojis + " " + Grammar.grammar("Emoji", emojis) + " | <:stickers:1010953833058410576> " + stickers + " " + Grammar.grammar("Sticker", stickers), false);
            embed.addField("Other", "<:bans:1010954107135217794> " + bans + " " + Grammar.grammar("Ban", bans) + " | <:boosts:1010954093826670727> " + boosts + " " + Grammar.grammar("Boost", boosts), true);
            if (regionEmoji != null) {
                embed.addField("Voice Region", regionEmoji + " " + regionName, true);
                if (guild.getIcon() != null) {
                    embed.setThumbnail(guild.getIconUrl() + "?size=1024");
                    if (guild.getBanner() != null) {
                        embed.addField("Banner", "", false);
                        embed.setImage(guild.getBannerUrl());
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    } else {
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    }
                } else {
                    if (guild.getBanner() != null) {
                        embed.addField("Banner", "", false);
                        embed.setImage(guild.getBannerUrl());
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    } else {
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    }
                }
            } else {
                embed.addField("Voice Region", regionName, true);
                if (guild.getIcon() != null) {
                    embed.setThumbnail(guild.getIconUrl() + "?size=1024");
                    if (guild.getBanner() != null) {
                        embed.addField("Banner", "", false);
                        embed.setImage(guild.getBannerUrl());
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    } else {
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    }
                } else {
                    if (guild.getBanner() != null) {
                        embed.addField("Banner", "", false);
                        embed.setImage(guild.getBannerUrl());
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    } else {
                        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                    }
                }
            }
        } 
    }
}
