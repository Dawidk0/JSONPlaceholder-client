package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import model.Post;
import java.io.File;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor()
public class JsonFileRepository {

    @NonNull String storagePath;
    @NonNull ObjectMapper objectMapper;

    public void saveAll(String data) {
        try {
            List<Post> posts = objectMapper.readValue(data, new TypeReference<List<Post>>() {});
            for (Post post : posts) {
                objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValue(new File(storagePath + "/" + post.getId() + ".json"), post);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
