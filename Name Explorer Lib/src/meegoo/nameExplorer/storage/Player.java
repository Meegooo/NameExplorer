package meegoo.nameExplorer.storage;

import meegoo.nameExplorer.util.CustomList;
import meegoo.nameExplorer.util.JSONHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.UUID;

public class Player {

    private UUID uuid;
    private byte size;
    private List<JSONObject> list;

    public final static int NAME = 0;
    public final static int TIME = 1;
    public final static int JSONOBJECT = 2;

    private CustomList<Cell> names = new CustomList<>();

    public Player(JSONArray info) {
        list = JSONHelper.divideJSONArrayIntoObjects(info);
        for (JSONObject jsonObject : list) {
            names.addIfNotPresent(new Cell(jsonObject));
        }
        size = (byte) names.size();

    }

    public Player (JSONArray info, UUID uuid) {
        this(info);
        this.uuid = uuid;
    }

    public Player (JSONArray info, String uuid) {
        this(info);
        uuid = uuid.replace("-", "");
        this.uuid = UUID.fromString(uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20, 32));
    }

    public byte getSize() {return size;}
    public void setUUID(UUID uuid) {this.uuid = uuid;}
    public UUID getUUID() {return uuid;}

    /**
     *
     * @param n Number of nickname. 0 is the first, Player.getSize() is the last
     * @param type Player.NAME for name, Player.TIME for time
     * @return Name, time or JSONObject. Returns null if requested type is wrong.
     */

    public Object get(int type, int n) {

        if (type == 0) {
            return names.get(n).getName();
        }else if (type == 1) {
            return names.get(n).getTime();
        }else if (type == 2)  {
            String returnString = "{\"name\":\"" + names.get(n).getName() + "\"" +
                    ((names.get(n).getTime()==0) ? ("}") : (",\"" + "changedToAt:" + (names.get(n).getTime()) + "}"));
            return JSONHelper.getJsonFromString(returnString);
        }else return null;

    }

}