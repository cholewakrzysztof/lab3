package pwr.student.FrontEnd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

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
    public static String validInsertParams(HashMap<String,String> params){
        String date = params.get("date");
        if(date.length()>0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!isValidDate(date, sdf))
                return "Date should be in format yyyy-MM-dd HH:mm:ss";
        }

        String person = params.get("person");
        if(person.matches("[0-9]"))
            return "Person can't have number in name";

        String component = params.get("component");
        if(component.length()>50)
            return "Component name too long, max 50 chars";

        String priority = params.get("priority");
        if(priority.matches("[a-z]|[A-Z]"))
            return "Priority should be integer";

        String description = params.get("description");
        if(description.length()>255)
            return "Description too long, max 255 chars";

        return "true";
    }
    private static boolean isValidDate(String input,SimpleDateFormat format) {
        try {
            format.parse(input);
            return true;
        }
        catch(ParseException e){
            return false;
        }
    }
    public static boolean validSearchParams(HashMap<String,String> params){
        if(!params.keySet().stream().allMatch(col -> Arrays.stream(knownColumns).toList().contains(col)))
            return false;

        Set<String> keySet = params.keySet();

        //TODO valid all params
        //if(keySet.contains("id"))

        return true;
    }
    public static HashMap<String,String> rebuildParams(HashMap<String,String> params){
        Set<String> keySet = params.keySet();
        if(keySet.contains("description"))
            params.put("description","'"+params.get("description").toString()+"'");
        if(keySet.contains("person"))
            params.put("person","'"+params.get("person").toString()+"'");
        if(keySet.contains("component"))
            params.put("component","'"+params.get("component").toString()+"'");
        return params;
    }


}
