package pwr.student.FrontEnd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseResultPrinter {
    public static void printTableResult(ResultSet rs,String[] columns) throws SQLException {
        ArrayList<String> col = new ArrayList<>(Arrays.asList(columns));
        if(col.contains("*")) {
            col.remove("*");
            col.add("component");
            col.add("person");
            col.add("priority");
            col.add("date");
            col.add("id");
            col.add("description");
        }
        String title = "";
        for (String column:col) {
            title+=column+"\t";
        }
        System.out.println(title);
        while (rs.next()) {
            String row = "";
            for (String column:col) {
                row+=rs.getString(column)+"\t";
            }
            System.out.println(row);
        }
    }
}
