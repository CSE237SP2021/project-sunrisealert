import java.awt.Desktop;
import java.io.BufferedReader;
import java.util.Scanner;

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

import api.NewsApiClient;
import api.WeatherApiClient;

public class SunriseAlert {

    public static void printHeadlines(String[] headlines) {
        for (int i = 0; i < headlines.length; i++) {
            System.out.println("" + (i + 1) + ": " + headlines[i]);
        }
    }

    public static void provideLinks(String[] headlines, String[] urls) {
        try {
            FileWriter writer = new FileWriter("links.txt");
            for (int i = 0; i < headlines.length; i++) {
                writer.write(urls[i] + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot open file for writing");
        }
    }
    
    public static void promptLinks(String[] urls) {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("");
    	System.out.println("Type number of desired story to visit, or type 'exit' to quit");
    	
    	int articleNum = Integer.parseInt(scanner.nextLine());
    	if (articleNum >= 1 && articleNum <= urls.length + 1) {
    		openLink(urls, articleNum);
    	}
    	System.out.println(articleNum);
    }
    
    public static boolean openLink(String[] urls, int articleNum) throws URISyntaxException {
    	URI url = new URI(urls[articleNum + 1]);
    	Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
    			try {
    				desktop.browse(url);
    	            return true;
    			} catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	    }
    		
    	return false;
    }

    public static void main(String args[]) throws Exception {
        NewsApiClient news = new NewsApiClient();
        WeatherApiClient weather = new WeatherApiClient();
        String[] headlines = news.getHeadlines();
        String[] urls = news.getUrls();
        printHeadlines(headlines);
        provideLinks(headlines, urls);
        
        if (args.length == 1) {
            System.out.println(weather.getWeather(args[0]));
        } else {
            System.out.println(weather.getWeather(""));
        }
        
        promptLinks(urls);
    }
}
