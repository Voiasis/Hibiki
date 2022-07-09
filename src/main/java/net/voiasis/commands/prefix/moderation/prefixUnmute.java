package net.voiasis.commands.prefix.moderation;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;

public class prefixUnmute {
    public static void unmute(Member member, Message message, String[] args) {
        if (member.hasPermission(Permission.MODERATE_MEMBERS)) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            if (message.getMentions().getMembers().toArray().length == 1) {
                Member mentioned = message.getGuild().getMember(message.getMentions().getMembers().get(0));
                if (mentioned.isTimedOut()) {
                    mentioned.removeTimeout().queue();
                    embed.addField("User Unmuted", mentioned.getAsMention() + " has been unmuted.", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                }
            } else if (message.getContentRaw().length() >= args[0].length() + 1){
                UserSnowflake snowflake = User.fromId(args[1]);
                Member mentioned = message.getGuild().getMemberById(snowflake.getId());
                if (mentioned.isTimedOut()) {
                    mentioned.removeTimeout().queue();
                    embed.addField("User Unmuted", mentioned.getAsMention() + " has been unmuted.", false);
                    message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
                }
            }
        }
    }
}
