package pwr.student.FrontEnd;

import java.util.Arrays;
import java.util.HashMap;

public class DataValidator {
    private static final String[] knownColumns = new String[] {"*","date","component","person","priority","description","id"};
    public static boolean validColumns(String[] columns){
        return Arrays.stream(columns).allMatch(col -> Arrays.stream(knownColumns).toList().contains(col));
    }
    public static boolean validScope(HashMap<String,String> params){
        if(params.size()>2)
            return false;
        try{
            if(Integer.parseInt(params.get("start"))>-1)
                if(Integer.parseInt(params.get("end"))>-1)
                    return true;
            return false;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean validInsertParams(HashMap<String,String> params){
        return true;
    }
    public static boolean validSearchParams(HashMap<String,String> params){
        return true;
    }
}
