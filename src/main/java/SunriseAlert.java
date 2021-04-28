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

/**
 * The main class for the SunriseAlerts application.
 * Handles user inputs, makes calls to API clients,
 * and prints news and weather data to the console.
 *
 * @author Alex Eaton, Elijah Pena, Daniel Ruffing
 */
public class SunriseAlert {

    public static void printHeadlines(String[] headlines) {

        System.out.println("");
        System.out.println("------Your headlines for the day------");

        for (int i = 0; i < headlines.length; i++) {
            System.out.println("" + (i + 1) + ": " + headlines[i]);
        }
    }

    /**
     * Writes headlines and urls to text file 'links.txt'.
     *
     * @param headlines  parsed headlines from NewsApiClient.
     * @param urls  parsed urls from NewsApiClient.
     */
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

    /**
     * Lets the user open a selected article in their default web browser.
     *
     * @param urls  parsed urls from NewsApiClient.
     */
    public static void promptLinks(String[] urls) throws URISyntaxException {

        if (Desktop.isDesktopSupported()) {
            System.out.println("");
            System.out.println("Type number of desired story to visit, or type 'exit' to quit");

            parseUserInput(urls);
        }
        else {
            System.out.println("");
            System.out.print("Your current desktop is not supported for link visiting ");
            System.out.println("(i.e. you are on Windows Subsytem for Linux). ");
            System.out.println("For desktop link support, please run project on Windows or an IDE such as Eclipse.");
        }
    }

    /**
     * Helper function for promptLinks. 
     * <P>
     * Let's the user select an article by typing a number.
     * 
     * @param urls  parsed urls from NewsApiClient.
     */
    public static void parseUserInput(String[] urls) throws URISyntaxException {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }
            else {
                try {
                    int articleNum = Integer.parseInt(input);

                    if (articleNum >= 1 && articleNum <= urls.length) {
                        openLink(urls, articleNum);
                    }
                    else {
                        System.out.println("Please enter a valid news story number");
                    }
                }
                catch(Exception e) {
                    System.out.println("Please enter a valid news story number");
                }
            }
        }

        scanner.close();
    }

    /**
     * Helper function for promptLinks. 
     * <P>
     * Opens the selected link in the user's default web browser.
     * 
     * @param urls  parsed urls from NewsApiClient.
     * @param articleNum  the article the user selected.
     */
    public static boolean openLink(String[] urls,
            int articleNum) throws URISyntaxException {

        URI url = new URI(urls[articleNum - 1]);
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
        String[] headlines;
        String[] urls;
        if (args.length < 1) {
            System.out.println("No arguments from config detected, running with default values");
            headlines = news.getHeadlines("20");
            urls = news.getUrls();
        }
        else {
            headlines = news.getHeadlines(args[0]);
            urls = news.getUrls();
        }

        printHeadlines(headlines);

        provideLinks(headlines, urls);

        if (args.length == 2) {
            System.out.println("");
            System.out.println("------Today's Weather------");
            System.out.println(weather.getWeather(args[1]));
        } else {
            System.out.println("");
            System.out.println("------Today's Weather------");
            System.out.println(weather.getWeather(""));
        }

        promptLinks(urls);
    }
}
