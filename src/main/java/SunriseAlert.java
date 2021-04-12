import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import api.NewsApiClient;
import api.WeatherApiClient;

public class SunriseAlert {
	
	public static void printHeadlines(String[] headlines) {
        for (int i = 0; i < headlines.length; i++) {
            System.out.println(headlines[i]);
        }
	}
	
    public static void main(String args[]) throws Exception {
        NewsApiClient news = new NewsApiClient();
        WeatherApiClient weather = new WeatherApiClient();
        String[] headlines = news.parseHeadlines();
        printHeadlines(headlines);
        
        if (args.length == 1) {
            System.out.println(weather.getWeather(args[0]));
        } else {
            System.out.println(weather.getWeather(""));
        }
    }
}
