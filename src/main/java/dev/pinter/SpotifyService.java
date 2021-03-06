package dev.pinter;

import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SpotifyService {
    private final SpotifyAPI spotifyAPI = new SpotifyAPI();

    public AccessTokenResponse getAccesssToken() {
        String d = Base64.getEncoder()
                .encodeToString("4c13d223b3fd459d8cd39564e9309589:18eef649a5f04e2b948b3981f7eb5349".getBytes());

        return spotifyAPI.getAccessToken(d);
    }

    public Album getAlbum(String authToken, String id) {
        return spotifyAPI.getAlbum(authToken, id);
    }

    public Artist getArtist(String authToken, String id) {
        return spotifyAPI.getArtist(authToken, id);
    }

    public ArtistAlbumsRoot getArtistAlbums(String authToken, String id) {
        HashMap<String, List<String>> hash = new HashMap<>(1000);
        hash.put("limit", Collections.singletonList("50"));

        return spotifyAPI.getArtistAlbums(authToken, id, hash);
    }

    public SearchRoot searchArtist(String query, String authtoken) {
        HashMap<String, List<String>> hash = new HashMap<>();
        hash.put("q", Collections.singletonList(query));
        hash.put("type", Collections.singletonList("artist"));
        return spotifyAPI.request(SearchRoot.class,
                "https://api.spotify.com/v1/search",
                authtoken,
                false,
                hash);
    }
}
