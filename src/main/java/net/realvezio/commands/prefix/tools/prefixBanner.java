package net.realvezio.commands.prefix.tools;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class prefixBanner {
    public static void banner(Message message, String[] args) {
        try {
            if (message.getAuthor().retrieveProfile().complete().getBanner() != null) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.RED);
                if(message.getMentions().getUsers().size() == 1) {
                    User mentioned = message.getMentions().getUsers().get(0);
                    embed.setTitle("Banner of " + mentioned.getAsTag());
                    embed.setDescription("[Image Link]("+ mentioned.retrieveProfile().complete().getBannerUrl() + "?size=1024)");
                    embed.setImage(mentioned.retrieveProfile().complete().getBannerUrl() + "?size=1024");
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else if (message.getContentRaw().length() >= args[0].length() + 1) {
                    User user = message.getJDA().retrieveUserById(args[1]).complete();
                    embed.setTitle("Banner of " + user.getAsTag());
                    embed.setDescription("[Image Link]("+ user.retrieveProfile().complete().getBannerUrl() + "?size=1024)");
                    embed.setImage(user.retrieveProfile().complete().getBannerUrl() + "?size=1024");
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                } else {
                    embed.setTitle("Banner of " + message.getAuthor().getAsTag());
                    embed.setDescription("[Image Link]("+ message.getAuthor().retrieveProfile().complete().getBannerUrl() + "?size=1024)");
                    embed.setImage(message.getAuthor().retrieveProfile().complete().getBannerUrl() + "?size=1024");
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                }
            } else {
                message.reply("This user does not have a banner.").mentionRepliedUser(false).queue();
            }
        } catch (NullPointerException e) {
            message.reply("Invalid ID or can't find that user.").mentionRepliedUser(false).queue();
        }
    }
}
