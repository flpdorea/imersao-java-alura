import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Connect to the API and get the top 250 movies
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        HttpClient client = HttpClient.newHttpClient();

        URI uri = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();

        // Retrieve only the data that interests us (title, poster and rating)
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> movies = jsonParser.parse(body);

        // Print and manipulate the results
        for (Map<String, String> movie : movies) {
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));
            System.out.println();
        }
    }
}
