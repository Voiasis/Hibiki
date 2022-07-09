package net.voiasis.commands.prefix.tools;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;

public class prefixUserinfo {
    public static void userinfo(Message message, Member member, String[] args) {
        if (message.getMentions().getMembers().toArray().length == 1) {
            send(message, message.getMentions().getMembers().get(0));
        } else if (message.getContentRaw().length() > args[0].length()) {
            try {
                UserSnowflake snowflake = User.fromId(args[1]);
                send(message, message.getGuild().getMemberById(snowflake.getId()));
            } catch (Exception e) {
                message.reply("Invaild user ID provided.").mentionRepliedUser(false).queue();
            }
        } else {
            send(message, member);
        }
    }
    private static void send(Message message, Member userMember) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        LocalDateTime now = LocalDateTime.now();
        String jsMonth = Integer.toString(userMember.getTimeJoined().getMonth().getValue());
        String jsDay = Integer.toString(userMember.getTimeJoined().getDayOfMonth());
        String jsYear = Integer.toString(userMember.getTimeJoined().getYear());
        String joinedServer = jsMonth + "/" + jsDay + "/" + jsYear;
        LocalDateTime jsTime = userMember.getTimeJoined().toLocalDateTime();
        long jsDiffL = ChronoUnit.DAYS.between(jsTime, now);
        String jsDiffS = Long.toString(jsDiffL);
        String jdMonth = Integer.toString(userMember.getTimeCreated().getMonth().getValue());
        String jdDay = Integer.toString(userMember.getTimeCreated().getDayOfMonth());
        String jdYear = Integer.toString(userMember.getTimeCreated().getYear());
        String joinedDiscord = jdMonth + "/" + jdDay + "/" + jdYear;
        LocalDateTime jdTime = userMember.getTimeCreated().toLocalDateTime();
        long jdDiffL = ChronoUnit.DAYS.between(jdTime, now);
        String jdDiffS = Long.toString(jdDiffL);
        embed.setTitle(userMember.getUser().getAsTag());
        embed.addField("User ID", "<:idblock:994818038983561236> " + userMember.getId(), false);
        embed.addField("Joined Discord", "<:discord:995010267400376391> " + joinedDiscord + " (" + jdDiffS + " days ago)", false);
        embed.addField("Joined Server", "<:time:994820769282531450> " + joinedServer + " (" + jsDiffS + " days ago)", false);
        embed.setThumbnail(userMember.getUser().getAvatarUrl());
        String status = userMember.getOnlineStatus().name();
        switch (status) {
            case "ONLINE" :
            embed.setDescription("<:online:995013089613328434> " + userMember.getAsMention());
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "OFFLINE" :
            embed.setDescription("<:offline:995012909669285998> " + userMember.getAsMention());
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "IDLE" :
            embed.setDescription("<:idle:995012370411823104> " + userMember.getAsMention());
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
            case "DO_NOT_DISTURB" :
            embed.setDescription("<:dnd:995012472954171402> " + userMember.getAsMention());
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            break;
        }
    }
}
