package meegoo.nameExplorer.common;

import meegoo.nameExplorer.mojangapi.UUIDSFromName;
import meegoo.nameExplorer.util.CustomList;

import java.util.*;

public class UUIDSLoop {

    private static int currentKey;
    private static CustomList<UUID> uuids;

    public static List<UUID> getUUIDS(String name) {
        currentKey = 0;
        uuids = null;
        uuids = new CustomList<UUID>();
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.YEAR) <= 2014 ||
                (calendar.get(Calendar.YEAR) == 2015 && calendar.get(Calendar.MONTH) == Calendar.JANUARY)) {
            new ProgramError("Fix your system date");
            return null;

        } else {


            if (calendar.get(Calendar.DATE) != 1) {
                uuids.addIfNotPresent(UUIDSFromName.getUUIDFromName(name, calendar));
                calendar.set(Calendar.DATE, 1);
            }
            while (calendar.get(Calendar.MONTH) != Calendar.JANUARY && calendar.YEAR != 2015) {
                uuids.addIfNotPresent(UUIDSFromName.getUUIDFromName(name, calendar));
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            }
            return uuids;
        }


    }
}
