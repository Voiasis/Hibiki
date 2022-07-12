package net.voiasis.commands.prefix.tools;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.voiasis.auto.Grammar;

public class prefixServerinfo {
    public static void serverinfo(Message message) {
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
        embed.addField("Server ID", "<:idblock:994818038983561236> " + guild.getId(), true).addField("Owner", "<:mention:995754056519778477> " + owner, true);
        embed.addField("Created", "<:time:994820769282531450> " + creation + " (" + createDiff + " " + Grammar.grammar("day", createDiff) + " ago)", false);
        embed.addField("Members", "<:members:994818077948641344> " + humans + " Total | <:online:995013089613328434> " + onlineTotal + " | <:offline:995012909669285998> " + offline, true);
        embed.addField("Bots", "<:settings:994843742567673896> " + bots + " Total | <:online:995013089613328434> " + onlineTotalBots + " | <:offline:995012909669285998> " + offlineBots, true);
        embed.addField("Channels", "<:textchannels:994817869445603418> " + text + " Text | <:voicechannels:994817966027845732> " + voice + " Voice | <:threads:994817908318412850> " + threads + " " + Grammar.grammar("Thread", threads) + " | <:categories:994817805167894619> " + categories + " " + Grammar.grammarY("Category", categories), false);
        embed.addField("Customs", "<:roles:994820876631556187> " + roles + " " + Grammar.grammar("Role", roles) + " | <:reaction:994820690328944731> " + emojis + " " + Grammar.grammar("Emoji", emojis) + " | <:stickers:994820803285762148> " + stickers + " " + Grammar.grammar("Sticker", stickers), false);
        embed.addField("Other", "<:bans:994817734959431851> " + bans + " " + Grammar.grammar("Ban", bans) + " | <:boosts:994818140804489228> " + boosts + " " + Grammar.grammar("Boost", boosts), true);
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
