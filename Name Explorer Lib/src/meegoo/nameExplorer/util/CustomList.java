package meegoo.nameExplorer.util;

import java.util.ArrayList;

public class CustomList<V> extends ArrayList<V> {

    public void addIfNotPresent(V value) {
        if (!super.contains(value) && value != null) {super.add(value);}
    }
}
