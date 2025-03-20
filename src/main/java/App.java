import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class App {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";

    // TASK 1
    public String getAllUsers() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users"))
                .build();

        return processResponse(request);
    }

    // TASK 1
    public String getUserById(int id) {
        if (id < 1) {
            return "Invalid id. Id must be greater then zero";
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + id))
                .build();

        return processResponse(request);
    }

    // TASK 1
    public String getUsersByName(String username) {
        if (username == null || username.isEmpty()) {
            return "Invalid username. Username must be not empty string";
        }

        String encodedValue = URLEncoder.encode(username, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users?username=" + encodedValue))
                .build();

        return processResponse(request);
    }

    // TASK 1
    public String createNewUser(String data) {
        if (data == null || data.isEmpty()) {
            return "Invalid data";
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users"))
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        return processResponse(request);
    }

    // TASK 1
    public String updateUser(int id, String data) {
        if (id < 1) {
            return "Invalid id. Id must be greater then zero";
        }
        if (data == null || data.isEmpty()) {
            return "Invalid data";
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + id))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(data))
                .build();

        return processResponse(request);
    }

    // TASK 1
    public String deleteUser(int id) {
        if (id < 1) {
            return "Invalid id. Id must be greater then zero";
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + id))
                .DELETE()
                .build();

        return processResponse(request);
    }

    // TASK 2
    public String saveAllCommentsToUsersLastPost(int id) {
        if (id < 1) {
            return "Invalid id. Id must be greater then zero";
        }

        // Step 1: get posts array
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + id + "/posts"))
                .build();

        String response = processResponse(request);
        if (response.equals("Error getting data!")) return response;

        // Step 2: get max postId
        Gson gson = new Gson();
        JsonArray array = gson.fromJson(response, JsonArray.class);

        if (array.isEmpty()) return "No data to write to the file!";

        int maxPostId = -1;
        for (JsonElement element : array) {
            JsonObject post = element.getAsJsonObject();
            int postId = post.get("id").getAsInt();

            if (postId > maxPostId) {
                maxPostId = postId;
            }
        }

        // Step 3: get comments to max postId
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/posts/" + id + "/comments"))
                .build();

        response = processResponse(request);
        if (response.equals("Error getting data!")) {
            return response;
        }

        // Step 4: write to the file
        String fileName = "user-" + id + "-post-" + maxPostId + "-comments.json";
        if (!writeFile(fileName, response)) {
            return "Error writing data to the file!";
        }

        return "Data is successfully written to the file " + fileName;
    }

    // TASK 3
    public String getAllTasksByUser(int id) {
        if (id < 1) {
            return "Invalid id. Id must be greater then zero";
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + id + "/todos"))
                .build();

        return processResponse(request);
    }

    // TASK 3
    public String getAllTasksByUser(int id, boolean isOpen) {
        if (id < 1) {
            return "Invalid id. Id must be greater then zero";
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + id + "/todos?completed=" + isOpen))
                .build();

        return processResponse(request);
    }

    private String processResponse(HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode >= 200 && statusCode < 300) {
                return response.body();
            } else {
                throw new InterruptedException("Error getting data!");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error getting data!";
        }
    }

    private boolean writeFile(String fileName, String text) {
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
