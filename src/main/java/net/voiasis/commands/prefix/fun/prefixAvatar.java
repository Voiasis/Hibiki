package net.voiasis.commands.prefix.fun;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class prefixAvatar {
    public static void avatar(Message message, User author) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        if(message.getMentions().getMembers().size() == 1) {
            Member mentioned = message.getMentions().getMembers().get(0);
            embed.setTitle("Avatar of " + mentioned.getUser().getAsTag());
            embed.setDescription("[Image Link]("+ mentioned.getUser().getAvatarUrl() + "?size=1024)");
            embed.setImage(mentioned.getUser().getAvatarUrl() + "?size=1024");
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
        } else {
            embed.setTitle("Avatar of " + author.getAsTag());
            embed.setDescription("[Image Link]("+ author.getAvatarUrl() + "?size=1024)");
            embed.setImage(author.getAvatarUrl() + "?size=1024");
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
        }
    }
}
