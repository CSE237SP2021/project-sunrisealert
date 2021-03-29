import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import api.NewsApiClient;
import api.WeatherApiClient;

public class SunriseAlert {
    public static void main(String args[]) throws Exception {
        NewsApiClient news = new NewsApiClient();
        WeatherApiClient weather = new WeatherApiClient();
        String[] headlines = news.getTopHeadlines();
        for (int i = 0; i < headlines.length; i++) {
            System.out.println(headlines[i]);
        }
        if (args[0] == null) {

        } else {
            //args[0] holds the location passed by the user
            System.out.println(weather.getWeather("63130"));
        }

    }
}
