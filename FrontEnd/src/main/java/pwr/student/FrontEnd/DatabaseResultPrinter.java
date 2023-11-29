package pwr.student.FrontEnd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DatabaseResultPrinter {
    public static void printTableResult(ResultSet rs,String[] columns) throws Exception {
        ArrayList<String> col = buildColumnsNameArray(columns);

        ArrayList<MyRow> rows = getListOfRows(rs,col);

        Map<String,Integer> fieldWidth = getSizeOfColumns(rows,col);

        printResizedRows(rows,fieldWidth,col);
    }

    private static void printResizedRows(ArrayList<MyRow> rows, Map<String,Integer> fieldWidth,ArrayList<String> columns){
        rows.forEach(row -> {
            StringBuilder rowString = new StringBuilder();
            columns.forEach(column -> {
                try {
                    rowString.append(resize(row.getString(column),fieldWidth.get(column)));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println(rowString);
        });
    }
    private static ArrayList<String> buildColumnsNameArray(String[] columns){
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
        return col;
    }
    private static Map<String, Integer> getSizeOfColumns(ArrayList<MyRow> rows, ArrayList<String> columns){
            Map<String, Integer> sizes = new HashMap<>();
            rows.forEach(row ->
                columns.forEach(colName -> {
                        String value = row.getString(colName);
                        if(sizes.containsKey(colName))
                            sizes.put(colName, sizes.get(colName) > value.length() ? sizes.get(colName) : value.length());
                        else
                            sizes.put(colName,value.length());
                })
            );
        return sizes;
    }

    private static ArrayList<MyRow> getListOfRows(ResultSet rs,ArrayList<String> columns) throws SQLException {
        ArrayList<MyRow> copy = new ArrayList<>();

        MyRow title = new MyRow();
        columns.forEach(column -> title.put(column,column));
        copy.add(title);

        while (rs.next()){
            MyRow row = new MyRow();

            for(String colName : columns)
                if(rs.findColumn(colName)>-1)
                    row.put(colName,rs.getString(colName));

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
