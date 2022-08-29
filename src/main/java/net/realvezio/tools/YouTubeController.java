package net.realvezio.tools;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.annotation.Nullable;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

public class YouTubeController {
    public static List<SearchResult> searchBar(String search, @Nullable String type) throws GeneralSecurityException, IOException {
        YouTube youtubeService = getService();
        YouTube.Search.List request = youtubeService.search().list("snippet, id");
        request.setKey(BotConfig.get("YOUTUBEAPI"));
        request.setMaxResults(25L);
        request.setQ(search);
        if (type != null) {
            if (type.equals("video")) {
                request.setType("video");
                SearchListResponse searchResponse = request.execute();
                List<SearchResult> searchList = searchResponse.getItems();
                return searchList;
            } else if (type.equals("channel")) {
                request.setType("channel");
                SearchListResponse searchResponse = request.execute();
                List<SearchResult> searchList = searchResponse.getItems();
                return searchList;
            } else {
                SearchListResponse searchResponse = request.execute();
                List<SearchResult> searchList = searchResponse.getItems();
                return searchList;
            }
        } else {
            SearchListResponse searchResponse = request.execute();
            List<SearchResult> searchList = searchResponse.getItems();
            return searchList;
        }
    }
    public static List<Video> videoID(String videoID) throws GeneralSecurityException, IOException {
        YouTube youtube = getService();
        YouTube.Videos.List videoRequest = youtube.videos().list("snippet,statistics,contentDetails");
        videoRequest.setKey(BotConfig.get("YOUTUBEAPI"));
        videoRequest.setId(videoID);
        VideoListResponse listResponse = videoRequest.execute();
        List<Video> videoList = listResponse.getItems();
        return videoList;
    }
    public static YouTube getService() throws GeneralSecurityException, IOException {
        return new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer(){
            public void initialize(HttpRequest request) throws IOException {/* */}}).setApplicationName("Vez Bot").build();
    }
}
