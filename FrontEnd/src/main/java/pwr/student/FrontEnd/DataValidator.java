package pwr.student.FrontEnd;

import java.util.Arrays;

public class DataValidator {
    private static String[] knownColumns = new String[] {"*","date","component","person","priority","description","id"};
    public static boolean validColumns(String[] columns){
        return Arrays.stream(columns).allMatch(col -> Arrays.stream(knownColumns).toList().contains(col));
    }
}
