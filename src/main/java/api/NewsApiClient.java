package api;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewsApiClient {
	private static final String BASE_URL = "https://newsapi.org/v2/";
	private static final String QUERY = "top-headlines?country=us&apiKey=";
	private static final String API_KEY = "2a7f891e21c64fe59f2114971a77e92d";

	public static String[] getTopHeadlines() throws Exception {
        String[] error = new String[1];
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

            String json = response.toString();
            //Begin parsing json
            json = json.substring(json.indexOf("articles"));

            String[] headlines = new String[20];
            for (int i = 0; i < 20; i++) {
                json = json.substring(json.indexOf("source") + 1);
                headlines[i] = json.substring(json.indexOf("title") + 7, json.indexOf("description") - 2);
            }
            return headlines;
        }

        return error;
    }
}