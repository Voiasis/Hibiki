package net.voiasis.auto;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hc.core5.http.ParseException;

import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.voiasis.tools.BotLog;
import net.voiasis.tools.SearchTools;
import net.voiasis.tools.SpotifyController;
import net.voiasis.tools.YouTubeController;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class MusicTranslator {
    final static String regexChecker = "(youtu.*be.*)\\/(watch\\?v=|embed\\/|v|shorts|)(.*?((?=[&#?])|$))";
    public static void musicTranslator(Message message) {
        //if (message.getChannel().getId().equals("954853266162843688")) {
            String[] args = message.getContentRaw().split("\\s+");
            if (SearchTools.booleanRegexSearch(regexChecker, message.getContentRaw())) {
                message.addReaction(Emoji.fromCustom("typing", Long.parseLong("995510544813543545"), true)).queue();
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
            }/* else if (message.getContentRaw().contains("https://open.spotify.com/track/")) {
                int count = args.length;
                while (count > 0) {
                    if (args[count - 1].contains("https://open.spotify.com/track/")) {
                    
                    }
                }

            } */
       // }
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
        //BotLog.log(normalized, "test", 3);
        String[] split = normalized.split("\\s+");
        int size = split.length;
        Track[] tracks = SpotifyController.searchTrack(normalized);
        int amount = tracks.length;
        while (amount == 0) {
            split = normalized.split("\\s+");
            size = split.length;
            normalized  = normalized.replace(split[size - 1], "");
            //BotLog.log(normalized, "test", 3);
            tracks = SpotifyController.searchTrack(normalized);
            amount = tracks.length;
            if (amount >= 1) {
                break;
            }
        }
        if (amount >= 1) {
            message.removeReaction(Emoji.fromCustom("typing", Long.parseLong("995510544813543545"), true)).queue();
            message.addReaction(Emoji.fromCustom("approved", Long.parseLong("956621420560011354"), true)).queue();
            ExternalUrl trackURLs = tracks[0].getExternalUrls();
            String trackLink = trackURLs.toString().replace("ExternalUrl(externalUrls={spotify=", "").replace("})", "");
            message.reply(trackLink).mentionRepliedUser(false).queue();
            return true;
        } else {
            message.removeReaction(Emoji.fromCustom("typing", Long.parseLong("995510544813543545"), true)).queue();
            message.addReaction(Emoji.fromCustom("rejected", Long.parseLong("956621588084695120"), true)).queue();
            return false;
        }
    }
}
