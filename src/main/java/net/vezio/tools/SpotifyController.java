package net.vezio.tools;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

public class SpotifyController extends ListenerAdapter {
    public static Track[] searchTrack(String search) throws ParseException, SpotifyWebApiException, IOException {
        Track[] tracks = getService().searchTracks(search).build().execute().getItems();
        return tracks;
    }
    public static PlaylistSimplified[] searchPlaylist(String search) throws ParseException, SpotifyWebApiException, IOException {
        PlaylistSimplified[] playlists = getService().searchPlaylists(search).build().execute().getItems();
        return playlists;
    }
    public static Track[] searchTrackId(String id) throws ParseException, SpotifyWebApiException, IOException {
        Track[] tracks = getService().getSeveralTracks(id).build().execute();
        return tracks;
    }
    public static Playlist searchPlaylistId(String id) throws ParseException, SpotifyWebApiException, IOException {
        Playlist playlist = getService().getPlaylist(id).build().execute();
        return playlist;
    }
    public static SpotifyApi getService() throws ParseException, SpotifyWebApiException, IOException {
        SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(BotConfig.get("SPOTIFYID")).setClientSecret(BotConfig.get("SPOTIFYSECRET")).build();
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        ClientCredentials clientCredentials = clientCredentialsRequest.execute();
        spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        return spotifyApi;
    }
}
