package net.vezio.commands.prefix.moderation;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class prefixUnban {
    public static void unban(Message message, String[] args) {
        if (message.isFromGuild()) {
            if (message.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.RED);
                User user = message.getJDA().retrieveUserById(args[1]).complete();
                message.getGuild().unban(user).queue();
                embed.addField("User Unbanned", "<:bans:994817734959431851> " + user.getAsMention() + " has been unbanned.", false);
                message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            } 
        } 
    }
}