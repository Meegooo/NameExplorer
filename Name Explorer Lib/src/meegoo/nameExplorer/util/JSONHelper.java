package meegoo.nameExplorer.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.Reader;

public class JSONHelper {
    private static Object output;

    public static Object getJsonFromString(Reader reader) {

        JSONParser parser = new JSONParser();
        try {

            output =  parser.parse(reader);
        } catch (ParseException pe){
            pe.printStackTrace();
            output = null;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            output = null;
        }
        return output;
    }

    public static Object getJsonFromString(String string) {

        JSONParser parser = new JSONParser();
        try {

            output = parser.parse(string);
        } catch (ParseException pe){
            pe.printStackTrace();
            output = null;
        }
        return output;
    }

    public static CustomList<JSONObject> divideJSONArrayIntoObjects(JSONArray array) {
        CustomList<JSONObject> output = new CustomList<JSONObject>();
        for (Object object : array){
            String objectString = object.toString();
            output.addIfNotPresent((JSONObject)JSONHelper.getJsonFromString(objectString));
        }
        return output;

    }
}
