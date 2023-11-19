package pwr.student.FrontEnd;

import pwr.student.BackEnd.SQLBuilder;
import pwr.student.BackEnd.SQLExecutor;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        SQLExecutor sqlExecutor = new SQLExecutor();
        String sql;

        //INSERT
        sql = SQLBuilder.buildInsert("decision", new Date(1),"ExampleComponent","Krzysztof",1,"Example_description");
        sqlExecutor.executeSQL(sql);

        //DELETE
        sql = SQLBuilder.buildDelete("decision",0,20);
        //sqlExecutor.executeSQL(sql);

        String[] columns = new String[] {"*"};
        //SELECT
        sql = SQLBuilder.buildSelect("decision",columns);
        ResultSet rs = sqlExecutor.select(sql);
        DatabaseResultPrinter.printTableResult(rs,columns);

        //SEARCHSELECT
        Map<String,String> conditions = Map.ofEntries(
          Map.entry("id","1")
        );
        sql = SQLBuilder.buildSearchSelect("decision",columns,conditions);
        rs = sqlExecutor.select(sql);
        DatabaseResultPrinter.printTableResult(rs,columns);

        sqlExecutor.close();
    }
}
