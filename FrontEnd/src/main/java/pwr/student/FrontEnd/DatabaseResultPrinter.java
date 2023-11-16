package pwr.student.FrontEnd;

import pwr.student.BackEnd.MyDate;

import java.sql.Array;
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
        while (rs.next()) {
            String row = "";
            for (String column:col) {
                row+=rs.getString(column)+"\t";
            }
            System.out.println(row);
        }
    }
}