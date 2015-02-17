package meegoo.nameExplorer.storage;

import org.json.simple.JSONObject;

public class Cell {
    private String name;
    private long time;

    public Cell(JSONObject object) {
        if (object != null) {
            this.name = (String) object.get("name");
            if (object.get("changedToAt") != null) this.time = (long) object.get("changedToAt");
            else this.time=0;
        }else {
            this.name=null;
            this.time=0;
        }

    }
    public void setName(String name) {this.name = name;}
    public String getName() {return this.name;}

    public void setTime(long time) {this.time = time;}
    public long getTime() {return this.time;}



}
