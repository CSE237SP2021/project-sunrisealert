package api;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewsApiClient {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String QUERY = "top-headlines?country=us&apiKey=";
    private static final String API_KEY = "2a7f891e21c64fe59f2114971a77e92d";
    private String[] headlines;
    private String[] urls;

    public String[] getHeadlines() throws Exception {
        if (this.headlines == null) {
            this.parseResponse();
        }
        return this.headlines;
    }

    public String[] getUrls() throws Exception {
        if (this.urls == null) {
            this.parseResponse();
        }
        return this.urls;
    }

    public void parseResponse() throws Exception {
        String jsonResponse = requestHeadlines();
        jsonResponse = jsonResponse.substring(jsonResponse.indexOf("articles"));
        this.headlines = new String[20];
        this.urls = new String[20];
        for (int i = 0; i < headlines.length; i++) {
            jsonResponse = jsonResponse.substring(jsonResponse.indexOf("source") + 1);
            headlines[i] = jsonResponse.substring(jsonResponse.indexOf("title") + 7, jsonResponse.indexOf("description") - 2);
            urls[i] = jsonResponse.substring(jsonResponse.indexOf("url") + 6, jsonResponse.indexOf("urlToImage") - 3);
        }
    }

    public static String requestHeadlines() throws Exception {
        String error = new String();
        URL urlObj = new URL(BASE_URL + QUERY + API_KEY);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader inputReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = inputReader.readLine()) != null) {
                response.append(inputLine);
            }

            inputReader.close();

            return response.toString();
        }

        return error;
    }
}
