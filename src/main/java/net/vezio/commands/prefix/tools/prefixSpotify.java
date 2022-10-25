package net.vezio.commands.prefix.tools;

import java.awt.Color;
import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.vezio.tools.BotLog;
import net.vezio.tools.SpotifyController;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.ExternalUrl;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class prefixSpotify {
    public static void spotify(Message message, String[] args) {
        if (message.getContentRaw().length() >= args[0].length() + 1) {
            String search = message.getContentRaw().substring(args[0].length());
            try {
                Track[] tracks = SpotifyController.searchTrack(search);
                int count = tracks.length;
                switch (count) {
                    case 0 :
                    message.reply("No results.").mentionRepliedUser(false).queue();
                    break;
                    case 1 :
                    message.replyEmbeds(trackEmbed(tracks, 0).build()).mentionRepliedUser(false).queue();
                    break;
                    case 2 :
                    message.replyEmbeds(trackEmbed(tracks, 0).build(), trackEmbed(tracks, 1).build()).mentionRepliedUser(false).queue();
                    break;
                    case 3 :
                    message.replyEmbeds(trackEmbed(tracks, 0).build(), trackEmbed(tracks, 1).build(), trackEmbed(tracks, 2).build()).mentionRepliedUser(false).queue();
                    break;
                    case 4 :
                    message.replyEmbeds(trackEmbed(tracks, 0).build(), trackEmbed(tracks, 1).build(), trackEmbed(tracks, 2).build(), trackEmbed(tracks, 3).build()).mentionRepliedUser(false).queue();
                    break;
                }
                if (count >= 5) {
                    message.replyEmbeds(trackEmbed(tracks, 0).build(), trackEmbed(tracks, 1).build(), trackEmbed(tracks, 2).build(), trackEmbed(tracks, 3).build(), trackEmbed(tracks, 4).build()).mentionRepliedUser(false).queue();
                }
            } catch (ParseException | SpotifyWebApiException | IOException e) {
                BotLog.log(BotLog.getStackTraceString(e, message.getJDA()), "Spotify", 4);
            }
        }
    }
    private static EmbedBuilder trackEmbed(Track[] tracks, int index) {
        String trackName = tracks[index].getName();
        ArtistSimplified[] trackArtistsList = tracks[index].getArtists();
        String trackArtists = trackArtistsList[0].getName();
        String trackAlbum = tracks[index].getAlbum().getName();
        ExternalUrl trackURLs = tracks[index].getExternalUrls();
        String trackLink = trackURLs.toString().replace("ExternalUrl(externalUrls={spotify=", "").replace("})", "");
        Image[] trackImage = tracks[index].getAlbum().getImages();
        String imageURL = trackImage[0].getUrl();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.RED);
        embed.setThumbnail(imageURL);
        embed.setDescription("**[" + trackName + "](" + trackLink + ")**\r\nby " + trackArtists + "\r\non " + trackAlbum);
        return embed;
    }
}
