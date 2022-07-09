package net.voiasis.commands.prefix.tools;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;

public class prefixServerinfo {
    public static void serverinfo(Guild guild, Message message) {
        String members = Integer.toString(guild.getMemberCount());
        String month = Integer.toString(guild.getTimeCreated().getMonthValue());
        String day = Integer.toString(guild.getTimeCreated().getDayOfMonth());
        String year = Integer.toString(guild.getTimeCreated().getYear());
        String creation = month + "/" + day + "/" + year;
        String owner = guild.getOwner().getAsMention();
        String boosts = Integer.toString(guild.getBoostCount());
        long onlineL = guild.getMembers().stream().filter(u -> u.getOnlineStatus() == OnlineStatus.ONLINE).count();
        long dndL = guild.getMembers().stream().filter(u -> u.getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB).count();
        long idleL = guild.getMembers().stream().filter(u -> u.getOnlineStatus() == OnlineStatus.IDLE).count();
        long onlineTotal = onlineL + dndL + idleL;
        String online = Long.toString(onlineL);
        String dnd = Long.toString(dndL);
        String idle = Long.toString(idleL);
        String offline = Long.toString(guild.getMemberCount() - onlineTotal);
        String bots = Long.toString(guild.getMembers().stream().filter(m -> m.getUser().isBot()).count());
        String text = Long.toString(guild.getTextChannelCache().size());
        String voice = Long.toString(guild.getVoiceChannelCache().size());
        String roles = Long.toString(guild.getRoleCache().size());
        String categories = Long.toString(guild.getCategoryCache().size());
        String emojis = Long.toString(guild.getEmojiCache().size());
        String threads = Long.toString(guild.getThreadChannelCache().size());
        String bans = Integer.toString(guild.retrieveBanList().complete().size());
        String region = guild.getVoiceChannels().get(0).getRegion().getEmoji() + " " + guild.getVoiceChannels().get(0).getRegion().getName();
        String stickers = Integer.toString(guild.getStickers().size());
            
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.setTitle(guild.getName(), null);
        embed.setThumbnail(guild.getIconUrl() + "?size=1024");
       
        embed.addField("Server ID", "<:idblock:994818038983561236> " + guild.getId(), false);
        embed.addField("Owner", owner, true).addField("Created", "<:time:994820769282531450> " + creation, true).addField("Voice Region", region, true);
        embed.addField("Members", "<:members:994818077948641344> " + members + " Total | <:settings:994843742567673896> " + bots + " Bots | <:online:995013089613328434> " + online + " | <:dnd:995012472954171402> " + dnd + " | <:idle:995012370411823104> " + idle + " | <:offline:995012909669285998> " + offline, false);
        embed.addField("Channels", "<:textchannels:994817869445603418> " + text + " Text | <:voicechannels:994817966027845732> " + voice + " Voice | <:threads:994817908318412850> " + threads + " Thread | <:categories:994817805167894619> " + categories + " Category", false);
        embed.addField("Customs", "<:roles:994820876631556187> " + roles + " Roles | <:reaction:994820690328944731> " + emojis + " Emojis | <:stickers:994820803285762148> " + stickers + " Stickers", false);
        embed.addField("Other", "<:bans:994817734959431851> " + bans + " Bans | <:boosts:994818140804489228> " + boosts + " Boosts", false);
        
        embed.addField("Banner", "", false);
        embed.setImage(guild.getBannerUrl());

        message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
    }
}
