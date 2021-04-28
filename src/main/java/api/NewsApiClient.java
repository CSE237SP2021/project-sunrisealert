package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A class that handles API calls to 'https://newsapi.org/'.
 * <P>
 * This class is used by our main class, SunriseAlert.java
 * to pull trending headlines from the United States.
 *
 * @author Alex Eaton, Elijah Pena, Daniel Ruffing
 */
public class NewsApiClient {

    /** For API calls. */
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String QUERY = "top-headlines?country=us&pageSize=";
    private static final String API_KEY = "&apiKey=2a7f891e21c64fe59f2114971a77e92d";

    /** Specifies the number of headlines requested from the API */
    private static String numHeadlines;

    /** Stores the parsed headlines returned from the API. */
    public String[] headlines;

    /** Stores the parsed urls returned from the API. */
    public String[] urls;

    /** 
     * Returns the news headlines as a string.
     * 
     * @param numHeadlines  the number of headlines queried in the API call.
     * 
     * @return parsed headlines.
     */
    public String[] getHeadlines(String numHeadlines) throws Exception {

        this.numHeadlines = numHeadlines;

        if (this.headlines == null) {
            this.parseResponse();
        }
        return this.headlines;
    }

    /** 
     * Returns headline urls as a string.
     * 
     * @return parsed urls.
     */
    public String[] getUrls() throws Exception {

        if (this.urls == null) {
            this.parseResponse();
        }
        return this.urls;
    }

    /**
     * Converts the API call's JSON response and 
     * extracts an article's source, title, and url
     * into a string format and stores the value.
     */
    public void parseResponse() throws Exception {

        String jsonResponse = requestHeadlines();
        jsonResponse = jsonResponse.substring(jsonResponse.indexOf("articles"));

        this.headlines = new String[Integer.parseInt(numHeadlines)];
        this.urls = new String[Integer.parseInt(numHeadlines)];

        for (int i = 0; i < headlines.length; i++) {
            jsonResponse = jsonResponse.substring(jsonResponse.indexOf("source") + 1);
            headlines[i] = jsonResponse.substring(jsonResponse.indexOf("title") + 7, 
                jsonResponse.indexOf("description") - 2);
            urls[i] = jsonResponse.substring(jsonResponse.indexOf("url") + 6, 
                jsonResponse.indexOf("urlToImage") - 3);
        }
    }

    /**
     * Handles the GET request made to the API.
     * <P>
     * Returns an error if GET request fails.
     * 
     * @return JSON response as a string.
     */
    public static String requestHeadlines() throws Exception {

        String error = new String();

        URL urlObj = new URL(BASE_URL + QUERY + numHeadlines + API_KEY);
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
