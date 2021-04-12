package api;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiClient {
    private static final String BASE_URL = "https://wttr.in/";
    private static final String REQUEST_FORMAT = "?format=\"%l:+%C+%t\"";

    public static String getWeather(String location) throws Exception{
        URL urlObj = new URL(BASE_URL + location + REQUEST_FORMAT);
        System.out.println(urlObj);
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
            System.out.println("----------------------");
            System.out.println(response.toString());
            System.out.println("----------------------");
            return response.toString();

        }
        return "Failed";
    }
}