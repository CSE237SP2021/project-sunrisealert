import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SunriseAlert {

    public static void main(String args[]) throws Exception {

        String[] headlines = getTopHeadlines();
        for (int i = 0; i < headlines.length; i++) {
            System.out.println(headlines[i]);
        }
        if (args[0] == null) {

        } else {
            //args[0] holds the location passed by the user
            System.out.println(getWeather(args[0]));
        }

    }

    public static String[] getTopHeadlines() throws Exception {
        String[] error = new String[1];
        String API_KEY = "2a7f891e21c64fe59f2114971a77e92d";
        String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + API_KEY;
        URL urlObj = new URL(url);
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

    public static String getWeather(String location) throws Exception {
        String url = "https://wttr.in/" + location + "?format=\"%l:+%C+%t\"";
        URL urlObj = new URL(url);
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
        return "Failed";
    }
}
