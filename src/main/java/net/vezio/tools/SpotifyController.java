package net.vezio.tools;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

public class SpotifyController extends ListenerAdapter {
    public static Track[] searchTrack(String search) throws ParseException, SpotifyWebApiException, IOException {
        Track[] tracks = getService().searchTracks(search).build().execute().getItems();
        return tracks;
    }
    public static Track[] searchId(String id) throws ParseException, SpotifyWebApiException, IOException {
        Track[] tracks = getService().getSeveralTracks(id).build().execute();
        return tracks;
    }
    public static SpotifyApi getService() throws ParseException, SpotifyWebApiException, IOException {
        SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(BotConfig.get("SPOTIFYID")).setClientSecret(BotConfig.get("SPOTIFYSECRET")).build();
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        ClientCredentials clientCredentials = clientCredentialsRequest.execute();
        spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        return spotifyApi;
    }
}
