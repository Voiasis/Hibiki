package net.vezio.auto;

import java.awt.Color;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hc.core5.http.ParseException;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.vezio.tools.BotLog;
import net.vezio.tools.SearchTools;
import net.vezio.tools.SpotifyController;
import net.vezio.tools.YouTubeController;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class MusicTranslator {
    final static String regexChecker = "(youtu.*be.*)\\/(watch\\?v=|embed\\/|v|shorts|)(.*?((?=[&#?])|$))";
    public static void musicTranslator(Message message) {
        if (!message.getChannel().getId().equals("1010933102564089880") && !message.getChannel().getId().equals("1010947434173694055")) {
            
        } else {
            String[] args = message.getContentRaw().split("\\s+");
            if (SearchTools.booleanRegexSearch(regexChecker, message.getContentRaw())) {
                message.addReaction(Emoji.fromCustom("typing", Long.parseLong("1010953714560933969"), true)).queue();
                int count = args.length;
                while (count > 0) {
                    if (!args[count - 1].contains("youtube.com") && !args[count - 1].contains("youtu.be")) {
                        count--;
                    } else {
                        if (youTube(message, args, count)) {
                            count = 0;
                        } else {
                            count--;
                        }
                    }
                }
            } else if (message.getContentRaw().contains("https://open.spotify.com/track/")) {
                message.addReaction(Emoji.fromCustom("typing", Long.parseLong("1010953714560933969"), true)).queue();
                int count = args.length;
                while (count > 0) {
                    if (args[count - 1].contains("https://open.spotify.com/track/")) {
                        if (spotify(message, args, count)) {
                            count = 0;
                        } else {
                            count--;
                        }
                    } else {
                        count--;
                    }
                }
            }
        }
    }
    private static boolean spotify(Message message, String[] args, int count) {
        String trackId = getSpotifyId(message.getContentRaw());
        //BotLog.log(trackId, "test1", 3);
        try {
            Track[] track = SpotifyController.searchId(trackId);
            String trackName = track[0].getName();
            //BotLog.log(trackName, "test2", 3);
            ArtistSimplified[] trackArtistsList = track[0].getArtists();
            String trackArtists = trackArtistsList[0].getName();
            //BotLog.log(trackArtists, "test3", 3);
            List<SearchResult> results = YouTubeController.searchBar(trackName + " " + trackArtists, "video");
            message.removeReaction(Emoji.fromCustom("typing", Long.parseLong("1010953714560933969"), true)).queue();
            //message.addReaction(Emoji.fromCustom("approved", Long.parseLong("1010938746348261397"), true)).queue();
            message.replyEmbeds(resultsEmbed(results, 0).build()).mentionRepliedUser(false).queue();
            return true;
        } catch (ParseException | SpotifyWebApiException | IOException | GeneralSecurityException e) {
            BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "MusicTranslator", 4);
            message.removeReaction(Emoji.fromCustom("typing", Long.parseLong("1010953714560933969"), true)).queue();
            //message.addReaction(Emoji.fromCustom("rejected", Long.parseLong("1010938961369247874"), true)).queue();
            return false;
        }
    }
    private static EmbedBuilder resultsEmbed(List<SearchResult> results, int index) throws IOException {
        SearchResult searchResult = results.get(index);
        SearchResultSnippet snippet = searchResult.getSnippet();
        String prettyString = searchResult.toPrettyString();
        //BotLog.log(prettyString, "test", 3);
        String resultTitle = snippet.getTitle();
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
    private static boolean youTube(Message message, String[] args, int count) {
        String videoID = getYouTubeId(args[count - 1]);
        try {
            List<Video> videoList = YouTubeController.videoID(videoID);
            Video video = videoList.get(0);
            VideoSnippet snippet = video.getSnippet();
            String videoTitle = snippet.getTitle();
            String videoChannel = snippet.getChannelTitle();
            String normalizedTitle = Normalizer.normalize(videoTitle, Normalizer.Form.NFD)
            .replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9\s]+", "")
            .replace("Original Mix", "")
            .replace("feat", "")
            .replace("NCS Release", "");
            String normalizedChannel = Normalizer.normalize(videoChannel, Normalizer.Form.NFD)
            .replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9\s]+", "");
            if (videoChannel.endsWith("Topic")) {
                String normalized = normalizedChannel.replace("Topic", "") + " " + normalizedTitle;
                return thing(normalized, message);
            } else if (videoChannel.equals("Monstercat Uncaged")) { //slight fix for their older tracks
                String normalized = normalizedTitle.replace("Monstercat Release", "")
                .replace("DnB", "")
                .replace("Dubstep", "")
                .replace("Drumstep", "")
                .replace("EDM", "")
                .replace("Trance", "")
                .replace("House", "")
                .replace("Electro", "")
                .replace("Glitch Hop", "")
                .replace("Hard Dance", "")
                .replace("Nu Disco", "")
                .replace("110BPM", "")
                .replace("or", "");
                return thing(normalized, message);
            } else { //tested working with disciple and nocopyrightsounds
                String normalized = normalizedTitle + " " + normalizedChannel;
                return thing(normalized, message);
            }
             
        } catch (GeneralSecurityException | IOException | ParseException | SpotifyWebApiException e) {
            BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "MusicTranslator", 4);
            return false;
        }
    }
    private static String getSpotifyId(String spotifyUrl) {
        String pattern = "(?<=open.spotify.com/track/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(spotifyUrl);
        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";  
        }
    }
    private static String getYouTubeId(String youTubeUrl) {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";  
        }
    }
    private static boolean thing(String normalized, Message message) throws ParseException, SpotifyWebApiException, IOException {
        //BotLog.log(normalized, "test1", 3);
        String[] split = normalized.split("\\s+");
        int size = split.length;
        Track[] tracks = SpotifyController.searchTrack(normalized.replace("MrSuicideSheep", ""));
        int amount = tracks.length;
        while (amount == 0) {
            split = normalized.split("\\s+");
            size = split.length;
            normalized  = normalized.replace(split[size - 1], "");
            //BotLog.log(normalized, "test2", 3);
            tracks = SpotifyController.searchTrack(normalized);
            amount = tracks.length;
            if (amount >= 1) {
                break;
            }
        }
        if (amount >= 1) {
            message.removeReaction(Emoji.fromCustom("typing", Long.parseLong("1010953714560933969"), true)).queue();
            //message.addReaction(Emoji.fromCustom("approved", Long.parseLong("1010938746348261397"), true)).queue();
            ExternalUrl trackURLs = tracks[0].getExternalUrls();
            String trackLink = trackURLs.toString().replace("ExternalUrl(externalUrls={spotify=", "").replace("})", "");
            //message.reply(trackLink).queue();
            String trackName = tracks[0].getName();
            ArtistSimplified[] trackArtistsList = tracks[0].getArtists();
            String trackArtists = trackArtistsList[0].getName();
            String trackAlbum = tracks[0].getAlbum().getName();
            Image[] trackImage = tracks[0].getAlbum().getImages();
            String imageURL = trackImage[0].getUrl();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.RED);
            embed.setThumbnail(imageURL);
            embed.setDescription("**[" + trackName + "](" + trackLink + ")**\r\nby " + trackArtists + "\r\non " + trackAlbum);
            message.replyEmbeds(embed.build()).mentionRepliedUser(false).queue();
            return true;
        } else {
            message.removeReaction(Emoji.fromCustom("typing", Long.parseLong("1010953714560933969"), true)).queue();
            //message.addReaction(Emoji.fromCustom("rejected", Long.parseLong("1010938961369247874"), true)).queue();
            return false;
        }
    }
}
