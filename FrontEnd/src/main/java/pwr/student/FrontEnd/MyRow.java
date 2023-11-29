package pwr.student.FrontEnd;

import java.util.HashMap;

public class MyRow {

    HashMap<String,String> fields = new HashMap<>();
    public String getString(String key) throws Exception {
        if(fields.containsKey(key))
            return fields.get(key);
        else
            throw new Exception("Row don't contain key:"+key);
    }
    public void put(String key, String value){
        fields.put(key,value);
    }
}
