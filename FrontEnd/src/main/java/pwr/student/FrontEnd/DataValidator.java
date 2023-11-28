package pwr.student.FrontEnd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
            return Integer.parseInt(params.get("start")) > -1 && Integer.parseInt(params.get("end")) > -1;
        }catch (Exception e){
            return false;
        }
    }
    public static String validInsertParams(HashMap<String,String> params){
        String date = params.get("date");
        if(!date.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (isNotValidDate(date, sdf))
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
    private static boolean isNotValidDate(String input, SimpleDateFormat format) {
        try {
            format.parse(input);
            return false;
        }
        catch(ParseException e){
            return true;
        }
    }
    public static String validSearchParams(HashMap<String,String> params){
        if(!new HashSet<>(Arrays.stream(knownColumns).toList()).containsAll(params.keySet()))
            return "Wrong column name";

        Set<String> keySet = params.keySet();

        if(keySet.contains("date")) {
            String date = params.get("date");
            if (!date.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (isNotValidDate(date, sdf))
                    return "Date should be in format yyyy-MM-dd HH:mm:ss";
            }
        }

        if(keySet.contains("person")) {
            String person = params.get("person");
            if (person.matches("[0-9]"))
                return "Person can't have number in name";
        }

        if(keySet.contains("component")) {
            String component = params.get("component");
            if (component.length() > 50)
                return "Component name too long, max 50 chars";
        }

        if(keySet.contains("priority")) {
            String priority = params.get("priority");
            if (priority.matches("[a-z]|[A-Z]"))
                return "Priority should be integer";
        }

        if(keySet.contains("description")) {
            String description = params.get("description");
            if (description.length() > 255)
                return "Description too long, max 255 chars";
        }




        return "true";
    }
    public static HashMap<String,String> rebuildParams(HashMap<String,String> params){
        Set<String> keySet = params.keySet();
        if(keySet.contains("description"))
            params.put("description","'"+ params.get("description") +"'");
        if(keySet.contains("person"))
            params.put("person","'"+params.get("person")+"'");
        if(keySet.contains("component"))
            params.put("component","'"+params.get("component")+"'");
        return params;
    }


}
