package net.vezio.commands.prefix;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;

public class prefixTest {
    public static void test(Message message, String[] args) {
        if (message.getAuthor().getId().equals("835691330725347379")) {
            Emoji emoji = message.getGuild().getEmojiById("1010938746348261397");
            Guild guild = message.getGuild();
            TextChannel textChannel = guild.getTextChannelById("1010933102425681922");
            Message reactMessage = textChannel.getHistory().getMessageById("1033905811593900032");
            reactMessage.addReaction(emoji).queue();
        }
    }
}