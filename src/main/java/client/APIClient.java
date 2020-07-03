package client;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@AllArgsConstructor()
public class APIClient {

    @NonNull HttpClient client;
    @NonNull String postsUrl;

    public HttpResponse<String> getPosts() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(postsUrl))
                .header("accept", "application/json")
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

}
