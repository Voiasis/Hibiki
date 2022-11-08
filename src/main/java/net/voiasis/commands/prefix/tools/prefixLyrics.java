package net.voiasis.commands.prefix.tools;

import java.io.IOException;
import java.util.LinkedList;

import core.GLA;
import genius.SongSearch.Hit;
import net.dv8tion.jda.api.entities.Message;
import net.voiasis.tools.BotLog;

public class prefixLyrics {
    public static void lyrics(Message message) {
        if (message.getChannel().getId().equals("1023705123089813626")) {
            if (message.getContentRaw().length() >= 8) {
                GLA gla = new GLA();
                String search = message.getContentRaw().substring(8).replace("*", "g");
                try {
                    LinkedList<Hit> hits = gla.search(search).getHits();
                    if (hits.size() != 0) {
                        String lyrics = hits.get(0).fetchLyrics().replace("â€™", "'").replace("*", "\\*").replace("nigga", "n\\*\\*\\*\\*").replace("nigger", "n\\*\\*\\*\\*\\*").replace("Nigga", "N\\*\\*\\*\\*").replace("Nigger", "N\\*\\*\\*\\*\\*");
                        String reply;
                        String title = hits.get(0).getTitle().replace("â€™", "'").replace("*", "\\*").replace("nigga", "n\\*\\*\\*\\*").replace("nigger", "n\\*\\*\\*\\*\\*").replace("Nigga", "N\\*\\*\\*\\*").replace("Nigger", "N\\*\\*\\*\\*\\*");
                        //BotLog.log(title, "test", 3);
                        if (lyrics.length() >= 1900) {
                            reply = lyrics.substring(0, 1900);
                            message.reply("**" + hits.get(0).getArtist().getName() + " - " + title + "**\r\n\r\n" + reply).mentionRepliedUser(false).queue();
                            lyrics = lyrics.replace(reply, "");
                            while (lyrics.length() > 0) {
                                if (lyrics.length() >= 1900) {
                                    reply = lyrics.substring(0, 1900);
                                    message.getChannel().sendMessage(reply).queue();
                                    lyrics = lyrics.replace(reply, "");
                                } else {
                                    message.getChannel().sendMessage(lyrics).queue();
                                    lyrics = "";
                                }
                            }
                        } else {
                            message.reply("**" + hits.get(0).getArtist().getName() + " - " + title + "**\r\n\r\n" + hits.get(0).fetchLyrics().replace("*", "\\*").replace("nigga", "n\\*\\*\\*\\*").replace("nigger", "n\\*\\*\\*\\*\\*").replace("Nigga", "N\\*\\*\\*\\*").replace("Nigger", "N\\*\\*\\*\\*\\*")).mentionRepliedUser(false).queue();
                        }
                    } else {
                        message.reply("No results.").mentionRepliedUser(false).queue();
                    }
                } catch (IOException e) {
                    BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "Lyrics", 4);
                }
            }
        }
    }
}
