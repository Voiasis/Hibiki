package net.voiasis.commands.prefix.moderation;

import java.awt.Color;
import java.util.EnumSet;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

public class prefixLock {
    public static void lock(Message message, Member member) {
        if (member.hasPermission(Permission.MANAGE_CHANNEL)) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            message.getTextChannel().getManager().putPermissionOverride(message.getGuild().getPublicRole(), null, EnumSet.of(Permission.MESSAGE_SEND)).queue();
            embed.addField("Lock Updated", "🔒 Channel has been locked.", false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
        }
    }
}