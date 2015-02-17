package meegoo.nameExplorer.common;

import meegoo.nameExplorer.mojangapi.NamesFromUUID;
import meegoo.nameExplorer.mojangapi.UUIDSFromName;
import meegoo.nameExplorer.storage.Player;
import meegoo.nameExplorer.util.CustomList;
import org.json.simple.JSONArray;

import java.util.List;
import java.util.UUID;

public class API {
    //Getting List<UUID> from nickname

    /**
     *
     * @param name Nickname to get UUIDS List from
     * @return List<UUID> with UUID that used this nickname
     */
    public static List<UUID> getUUIDSFromName(String name) {
        List<UUID> output = UUIDSLoop.getUUIDS(name);
        if (UUIDSFromName.wrongNickname) new ProgramError("Nickname not found");
        return output;
    }


    //Getting JSONArray of names with change time from UUID

    /**
     *
     * @param uuid UUID String
     * @return JSONArray with nickname history
     */
    public static JSONArray getJSONFromUUID(String uuid) {
        JSONArray output = NamesFromUUID.getJSONFromUUID(uuid);
        if (NamesFromUUID.wrongUUID) new ProgramError("UUID not found");
        return output;
    }

    /**
     *
     * @param uuid UUID Object
     * @return JSONArray with nickname history
     */
    public static JSONArray getJSONFromUUID(UUID uuid) {
        JSONArray output = NamesFromUUID.getJSONFromUUID(uuid);
        if (NamesFromUUID.wrongUUID) new ProgramError("UUID not found");
        return output;
    }

    public static List<Player> getPlayers(String name) {
        CustomList<Player> players = new CustomList<>();
        for (UUID uuid : getUUIDSFromName(name)) {
            players.addIfNotPresent(new Player(getJSONFromUUID(uuid), uuid));
        }

        return players;
    }



}
