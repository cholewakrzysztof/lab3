package pwr.student.FrontEnd;

import pwr.student.BackEnd.MyDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseResultPrinter {
    public static void printTableResult(ResultSet rs,String[] col) throws SQLException {
        ArrayList<String> rows = new ArrayList<>();
        ArrayList<String> columns = (ArrayList<String>) Arrays.stream(col).toList();
        /*TODO
        * Zrobić cały system budowania tabeli
        * */
        while (rs.next()) {
            if(columns.contains("id"))
            rows.add(rs.getInt("id")
                    + "\t" + rs.getString("person") + "\t"
                    + MyDate.getRepresentation(rs.getDate("date")) + "\t"
                    + rs.getString("component") + "\t"
                    + rs.getInt("priority") + "\t"
                    + rs.getString("description"));
        }
    }
}
