package net.voiasis.tools;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

public class SpotifyController extends ListenerAdapter {
    public static Track[] searchTrack(String search) throws ParseException, SpotifyWebApiException, IOException {
        Track[] tracks = getService().searchTracks(search).build().execute().getItems();
        return tracks;
    }
    public static Track[] searchTrackId(String id) throws ParseException, SpotifyWebApiException, IOException {
        Track[] tracks = getService().getSeveralTracks(id).build().execute();
        return tracks;
    }
    public static Playlist searchPlaylistId(String id) throws ParseException, SpotifyWebApiException, IOException {
        Playlist playlist = getService().getPlaylist(id).build().execute();
        return playlist;
    }
    public static Album searchAlbumId(String id) throws ParseException, SpotifyWebApiException, IOException {
        Album album = getService().getAlbum(id).build().execute();
        return album;
    }
    public static Artist searchArtistId(String id) throws ParseException, SpotifyWebApiException, IOException {
        Artist artist = getService().getArtist(id).build().execute();
        return artist;
    }
    public static SpotifyApi getService() throws ParseException, SpotifyWebApiException, IOException {
        SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(BotConfig.get("SPOTIFYID")).setClientSecret(BotConfig.get("SPOTIFYSECRET")).build();
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        ClientCredentials clientCredentials = clientCredentialsRequest.execute();
        spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        return spotifyApi;
    }
}
