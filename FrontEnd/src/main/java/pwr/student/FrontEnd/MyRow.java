package pwr.student.FrontEnd;

import java.util.HashMap;

public class MyRow {
    HashMap<String,String> fields = new HashMap<>();
    public String getString(String key) { return fields.getOrDefault(key, "no key"); }
    public void put(String key, String value){
        fields.put(key,value);
    }
}
