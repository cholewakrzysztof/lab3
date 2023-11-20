package pwr.student.FrontEnd;

import pwr.student.BackEnd.BackGate;
import pwr.student.BackEnd.Respond;
import pwr.student.BackEnd.SQLBuilder;
import pwr.student.BackEnd.SQLExecutor;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        SQLExecutor sqlExecutor = new SQLExecutor();
        SQLBuilder sqlBuilder = new SQLBuilder();
        String sql;

        sqlBuilder.choseTable("decision");

        //INSERT
        sql = sqlBuilder.buildInsert(new Date(1),"ExampleComponent","Krzysztof",1,"Example_description");
        sqlExecutor.executeSQL(sql);

        //DELETE
        sql = sqlBuilder.buildDelete(0,20);
        //sqlExecutor.executeSQL(sql);

        String[] columns = new String[] {"*"};
        //SELECT
        sql = sqlBuilder.buildSelect(columns);
        ResultSet rs = sqlExecutor.select(sql);
        DatabaseResultPrinter.printTableResult(rs,columns);

        //SEARCHSELECT
        Map<String,String> conditions = Map.ofEntries(
          Map.entry("id","1")
        );
        sql = sqlBuilder.buildSearchSelect(columns,conditions);
        rs = sqlExecutor.select(sql);
        DatabaseResultPrinter.printTableResult(rs,columns);

        sqlExecutor.close();

        FrontGate frontGate = new FrontGate();
        BackGate backGate = new BackGate();

        try {
            //s działa
            for (int i=0; i<5; i++){
                frontGate.sendRequest(backGate,RequestBuilder.buildRequest());
                Respond respond = frontGate.getRespond(backGate);
                DatabaseResultPrinter.printTableResult(respond.getResult(),respond.getColumns());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
