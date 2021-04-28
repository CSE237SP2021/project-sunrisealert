package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A class that handles API calls to 'https://wttr.in/'.
 * <P>
 * This class is used by our main class, SunriseAlert.java
 * to pull current weather data based on a user's location.
 *
 * @author Alex Eaton, Elijah Pena, Daniel Ruffing
 */

public class WeatherApiClient {

    /** For API calls. */
    private static final String BASE_URL = "https://wttr.in/";
    private static final String REQUEST_FORMAT = "?format=\"%l:+%C+%t\"";

    /** 
     * Handles requests for weather data from other Java classes.
     * 
     * @param location  the location queried in the API call.
     * 
     * @return parsed headlines.
     */
    public static String getWeather(String location) throws Exception{

        String error = new String();

        URL urlObj = new URL(BASE_URL + location + REQUEST_FORMAT);
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
