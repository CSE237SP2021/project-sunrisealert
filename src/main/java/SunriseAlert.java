import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SunriseAlert {

    public static void main(String args[]) throws Exception {

        String API_KEY = "2a7f891e21c64fe59f2114971a77e92d";
        System.out.println("Hello there");
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
            
            System.out.println(json);
            String[] headlines = new String[20];
            for (int i = 0; i < 20; i++) {
                json = json.substring(json.indexOf("source") + 1);
                headlines[i] = json.substring(json.indexOf("title") + 7, json.indexOf("description") - 2);
                System.out.println(headlines[i]);
            }

        }

    }
}
