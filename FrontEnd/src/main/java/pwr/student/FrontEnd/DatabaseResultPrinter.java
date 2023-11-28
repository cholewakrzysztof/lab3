package pwr.student.FrontEnd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DatabaseResultPrinter {
    public static void printTableResult(ResultSet rs,String[] columns) throws Exception {
        ArrayList<String> col = new ArrayList<>(Arrays.asList(columns));
        if(col.contains("*")) {
            col.remove("*");
            col.add("id");
            col.add("person");
            col.add("component");
            col.add("priority");
            col.add("date");
            col.add("description");
        }
        Map<String,Integer> fieldWidth = getSizeOfColumns(rs,col);

        //TODO read result set twice!
        for (String key : fieldWidth.keySet())
            System.out.println(key+" "+fieldWidth.get(key).toString());

        StringBuilder title = new StringBuilder();
        for (String column:col) {
            title.append(column).append("\t");
        }
        System.out.println(title);
        while (rs.next()) {
            StringBuilder row = new StringBuilder();
            for (String column:col) {
                row.append(rs.getString(column)).append("\t");
            }
            System.out.println(row);
        }
    }

    private static Map<String, Integer> getSizeOfColumns(ResultSet rs, ArrayList<String> columns) throws Exception{
            Map<String, Integer> sizes = new HashMap<>();
            while (rs.next()) {
                columns.forEach(colName -> {
                    String value = null;
                    try {
                        value = rs.getString(colName);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    if (sizes.containsKey(colName))
                        sizes.put(colName, sizes.get(colName) > value.length() ? sizes.get(colName) : value.length());
                    else
                        sizes.put(colName,0);
                });
            }
            rs.beforeFirst();

        return sizes;
    }
}
