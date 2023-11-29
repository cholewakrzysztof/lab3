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

        ArrayList<MyRow> copy = getListOfRows(rs,col);
        MyRow title = new MyRow();

        col.forEach(column -> title.put(column,column));
        copy.add(0,title);

        Map<String,Integer> fieldWidth = getSizeOfColumns(copy,col);

        copy.forEach(row -> {
            StringBuilder rowString = new StringBuilder();
            col.forEach(column -> {
                try {
                    rowString.append(resize(row.getString(column),fieldWidth.get(column)));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println(rowString);
        });
    }

    private static Map<String, Integer> getSizeOfColumns(ArrayList<MyRow> rows, ArrayList<String> columns) throws Exception{
            Map<String, Integer> sizes = new HashMap<>();
            for(MyRow row : rows) {
                columns.forEach(colName -> {
                    String value = null;
                    try {
                        value = row.getString(colName);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    if (sizes.containsKey(colName))
                        sizes.put(colName, sizes.get(colName) > value.length() ? sizes.get(colName) : value.length());
                    else
                        sizes.put(colName,value.length());
                });
            }

        return sizes;
    }

    private static ArrayList<MyRow> getListOfRows(ResultSet rs,ArrayList<String> columns) throws SQLException {
        ArrayList<MyRow> copy = new ArrayList<>();

        while (rs.next()){
            MyRow row = new MyRow();
            columns.forEach(colName -> {
                try {
                    if(rs.findColumn(colName)>-1) {
                        row.put(colName,rs.getString(colName));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            copy.add(row);
        }
        return copy;
    }
    private static String resize(String str, int length){
        StringBuilder strBuilder = new StringBuilder(str);
        while (strBuilder.length()<length+2)
            strBuilder.append(" ");
        return strBuilder.append("|").toString();
    }
}
