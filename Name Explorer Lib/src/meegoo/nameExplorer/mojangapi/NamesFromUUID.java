package meegoo.nameExplorer.mojangapi;


import meegoo.nameExplorer.common.ProgramError;
import meegoo.nameExplorer.util.JSONHelper;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.UUID;

public class NamesFromUUID {

    private static HttpURLConnection connection;
    private static JSONArray output;
    private static InputStream getRequestOutput;

    public static boolean wrongUUID = false;
    private static boolean connectionProblem = false;

    public static JSONArray getJSONFromUUID(UUID uuid) {
        return getJSONFromUUID(uuid.toString());
    }
    public static JSONArray getJSONFromUUID(String uuid){
        connection = null;
        output = null;
        getRequestOutput = null;

        if (uuid.equals("")) {
            wrongUUID = true;
            return null;
        }else{
            String url = "https://api.mojang.com/user/profiles/" + uuid.toString().replace("-", "") + "/names";
            System.out.println("Attempting connection to " + url);


            try {
                connection = Connection.connect(url);

                getRequestOutput = connection.getInputStream();
                System.out.println(connection.getResponseCode());
                if (connection.getResponseCode() == 200) {
                    output = (JSONArray) JSONHelper.getJsonFromString(new InputStreamReader(getRequestOutput));
                    wrongUUID = false;
                }else if (connection.getResponseCode() == 204) {
                    wrongUUID = true;
                }

                return output;
            } catch (IOException e) {
                if (!connectionProblem) {
                new ProgramError("There is a problem with your connection.");
                connectionProblem = !connectionProblem;
            }
                e.printStackTrace();
                return null;
            }
        }
    }
}
