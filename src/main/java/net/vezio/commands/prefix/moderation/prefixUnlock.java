package net.vezio.commands.prefix.moderation;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;

public class prefixUnlock {
    public static void unlock(Message message) {
        if (message.getMember().hasPermission(Permission.MANAGE_CHANNEL)) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            message.getChannel().asTextChannel().getManager().removePermissionOverride(message.getGuild().getPublicRole()).queue();
            embed.addField("Lock Updated", "🔓 Channel has been unlocked.", false);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
        }
    }
}