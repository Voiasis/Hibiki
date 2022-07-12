package net.voiasis.commands.prefix.moderation;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;

public class prefixUnban {
    public static void unban(Message message, String[] args) {
        if (message.getMember().hasPermission(Permission.BAN_MEMBERS)) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            UserSnowflake mentioned = User.fromId(args[1]);
            message.getGuild().unban(mentioned).queue();
            embed.addField("User Unbanned", "<:bans:994817734959431851> " + mentioned.getAsMention() + " has been unbanned.", false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
        }
    }
}