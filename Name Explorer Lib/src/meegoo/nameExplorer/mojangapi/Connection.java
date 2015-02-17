package meegoo.nameExplorer.mojangapi;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {

    private static final String USER_AGENT = "Mozzila/5.0";

    protected static HttpURLConnection connect(String url){

        try {
            URL geturl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) geturl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);

            return connection;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
