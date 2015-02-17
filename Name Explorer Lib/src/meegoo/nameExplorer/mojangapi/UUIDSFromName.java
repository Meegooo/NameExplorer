package meegoo.nameExplorer.mojangapi;

import meegoo.nameExplorer.common.ProgramError;
import meegoo.nameExplorer.util.JSONHelper;
import org.json.simple.*;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.UUID;

public class UUIDSFromName {

    private static InputStream getRequestOutput;
    private static HttpURLConnection connection;
    private static JSONObject output;
    private static JSONObject playerInfo;
    private static String stringUUID;

    public static boolean wrongNickname = false;
    private static boolean connectionProblem = false;

    public static JSONObject getJSONFromName(String name) {
        return getJSONFromName(name, System.currentTimeMillis() / 1000);
    }

    public static JSONObject getJSONFromName(String name, Calendar calendar) {
        long unixtime = calendar.getTimeInMillis() / 1000;
        return getJSONFromName(name, unixtime);
    }

    public static JSONObject getJSONFromName(String name, long unixtime) {
        connection = null;
        output = null;
        getRequestOutput = null;

        if (name.equals("")) {
            wrongNickname = true;
            return null;
        } else {
            String url = "https://api.mojang.com/users/profiles/minecraft/" + name + "?at=" + unixtime;
            try {
                connection = Connection.connect(url);
                getRequestOutput = connection.getInputStream();
                System.out.println("Attempting connection to " + url);

                if (connection.getResponseCode() == 200) {
                    output = (JSONObject) JSONHelper.getJsonFromString(new InputStreamReader(getRequestOutput));
                    wrongNickname = false;
                } else if (connection.getResponseCode() == 204) {
                    wrongNickname = true;
                }
                return output;
            } catch (IOException e) {
                e.printStackTrace();

                if (!connectionProblem) {
                    new ProgramError("There is a problem with your connection.");
                    connectionProblem = !connectionProblem;
                }
                return null;
            } catch (NullPointerException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static UUID getUUIDFromName(String name) {
        playerInfo = getJSONFromName(name);
        if (playerInfo != null) {
            stringUUID = (String) playerInfo.get("id");
            return UUID.fromString(stringUUID.substring(0, 8) + "-" + stringUUID.substring(8, 12) + "-" + stringUUID.substring(12, 16) + "-" + stringUUID.substring(16, 20) + "-" + stringUUID.substring(20, 32));
        }
        return null;
    }

    public static UUID getUUIDFromName(String name, long unixtime) {
        playerInfo = getJSONFromName(name, unixtime);
        if (playerInfo != null) {
            stringUUID = (String) playerInfo.get("id");
            return UUID.fromString(stringUUID.substring(0, 8) + "-" + stringUUID.substring(8, 12) + "-" + stringUUID.substring(12, 16) + "-" + stringUUID.substring(16, 20) + "-" + stringUUID.substring(20, 32));
        }
        return null;
    }

    public static UUID getUUIDFromName(String name, Calendar calendar) {
        playerInfo = getJSONFromName(name, calendar);
        if (playerInfo != null) {
            stringUUID = (String) playerInfo.get("id");
            return UUID.fromString(stringUUID.substring(0, 8) + "-" + stringUUID.substring(8, 12) + "-" + stringUUID.substring(12, 16) + "-" + stringUUID.substring(16, 20) + "-" + stringUUID.substring(20, 32));
        }
        return null;
    }
}
