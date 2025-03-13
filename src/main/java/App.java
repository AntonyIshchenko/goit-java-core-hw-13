import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class App {
    private HttpClient client = HttpClient.newHttpClient();
    private String baseURL = "https://jsonplaceholder.typicode.com";

    // TASK 1
    public String getAllUsers() {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseURL + "/users")).build();

        return  processResponse(request);
    }

    // TASK 1
    public String getUserById(int id) {
        if ( id<1 ) return "Invalid id. Id must be greater then zero";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + "/users/" + id))
                .build();

        return processResponse(request);
    }

    // TASK 1
    public String getUsersByName(String username) {
        if (username.isEmpty()) return "Invalid username. Username must be not empty string";

        String encodedValue = URLEncoder.encode(username, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + "/users?username=" + encodedValue))
                .build();

        return processResponse(request);
    }

    // TASK 1
    public String createNewUser(String data){
        if (data == null || data.isEmpty()) return "Invalid data";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + "/users"))
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        return processResponse(request);
    }

    // TASK 1
    public String updateUser(int id, String data){
        if ( id<1 ) return "Invalid id. Id must be greater then zero";
        if (data == null || data.isEmpty()) return "Invalid data";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + "/users/"+id))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(data))
                .build();

        return processResponse(request);
    }

    // TASK 1
    public String deleteUser(int id){
        if ( id<1 ) return "Invalid id. Id must be greater then zero";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + "/users/"+id))
                .DELETE()
                .build();

        return processResponse(request);
    }

    private String processResponse(HttpRequest request) {
        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode >= 200 && statusCode <300 ) {
                return response.body();
            } else {
                throw new InterruptedException("Error getting data!");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error getting data!";
        }
    }
}
