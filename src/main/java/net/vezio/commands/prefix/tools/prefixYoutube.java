package net.vezio.commands.prefix.tools;

import java.awt.Color;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.annotation.Nullable;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.vezio.tools.BotLog;
import net.vezio.tools.SearchTools;
import net.vezio.tools.YouTubeController;

public class prefixYoutube {
    public static void youtube(Message message, String[] args) {
        if (message.getContentRaw().length() >= args[0].length() + 1) {
            String search = message.getContentRaw().substring(args[0].length());
            try {
                List<SearchResult> results = YouTubeController.searchBar(search, type(args));
                int count = results.size();
                if (count == 0 ) {
                    message.reply("No results.").mentionRepliedUser(false).queue();
                } else {
                    message.replyEmbeds(resultsEmbed(results, 0).build()).mentionRepliedUser(false).queue();
                }
                /*switch (count) {
                    case 0 :
                    message.reply("No results.").mentionRepliedUser(false).queue();
                    break;
                    case 1 :
                    message.replyEmbeds(resultsEmbed(results, 0).build()).mentionRepliedUser(false).queue();
                    break;
                    case 2 :
                    message.replyEmbeds(resultsEmbed(results, 0).build(), resultsEmbed(results, 1).build()).mentionRepliedUser(false).queue();
                    break;
                    case 3 :
                    message.replyEmbeds(resultsEmbed(results, 0).build(), resultsEmbed(results, 1).build(), resultsEmbed(results, 2).build()).mentionRepliedUser(false).queue();
                    break;
                    case 4 :
                    message.replyEmbeds(resultsEmbed(results, 0).build(), resultsEmbed(results, 1).build(), resultsEmbed(results, 2).build(), resultsEmbed(results, 3).build()).mentionRepliedUser(false).queue();
                    break;
                }
                if (count >= 5) {
                    message.replyEmbeds(resultsEmbed(results, 0).build(), resultsEmbed(results, 1).build(), resultsEmbed(results, 2).build(), resultsEmbed(results, 3).build(), resultsEmbed(results, 4).build())
                    .mentionRepliedUser(false).queue();
                }*/
            } catch (GeneralSecurityException | IOException e) {
                BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "YouTube", 4);
            }
        }
    }
    private static EmbedBuilder resultsEmbed(List<SearchResult> results, int index) throws IOException {
        SearchResult searchResult = results.get(index);
        SearchResultSnippet snippet = searchResult.getSnippet();
        String prettyString = searchResult.toPrettyString();
        //BotLog.log(prettyString, "test", 3);
        String resultTitle = snippet.getTitle().replace("&#39;", "'");
        //String resultLength = SearchTools.lineSearch3("duration", prettyString);
        String resultChannel = snippet.getChannelTitle();
        String resultImageURL = snippet.getThumbnails().getDefault().getUrl();
        String videoID = SearchTools.lineSearch3("videoId", prettyString).replace("videoId", "").replace(":", "").replace("\"", "").replace(" ", "");
        String channelID = SearchTools.lineSearch3("channelId", prettyString).replace("channelId", "").replace(":", "").replace("\"", "").replace(",", "").replace(" ", "");
        String channelLink = "https://www.youtube.com/channel/" + channelID;
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.setThumbnail(resultImageURL);
        if (!videoID.equals("?")) {
            String resultLink = "https://youtu.be/" + videoID;
            embed.setDescription("**[" + resultTitle + "](" + resultLink + ")**\r\nfrom [" + resultChannel + "](" + channelLink + ")");
            return embed;
        } else {
            embed.setDescription("**[" + resultTitle + "](" + channelLink + ")**");
            return embed;
        } 
    }
    @Nullable
    private static String type(String[] args) {
        String type;
        if (args[1].equals("video")) {
            return type = "video";
        } else if (args[1].equals("channel")) {
            return type = "channel";
        } else {
            return null;
        }
    }
}
