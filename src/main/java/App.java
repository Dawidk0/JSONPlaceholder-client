import client.APIClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import storage.JsonFileRepository;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class App {
    public static void main(String[] args) {
        final String POSTS_API_URL = "https://jsonplaceholder.typicode.com/posts";
        final String PATH_TO_SAVE_DATA = "data/";
        final int OK_STATUS = 200;
        APIClient client = new APIClient(HttpClient.newHttpClient(), POSTS_API_URL);
        try {
            HttpResponse<String> response = client.getPosts();
            if (response.statusCode() == OK_STATUS) {
                JsonFileRepository storage = new JsonFileRepository(PATH_TO_SAVE_DATA, new ObjectMapper());
                storage.saveAll(response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
