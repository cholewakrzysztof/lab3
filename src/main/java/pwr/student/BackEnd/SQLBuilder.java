package pwr.student.BackEnd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLBuilder {
    public SQLBuilder(){

    }
    public static String buildInsert(String table, String date, String component, String person, Integer priority, String description) {
        String sql = "INSERT INTO "
                +table
                +"(date,component,person,priority,description)"
                +" VALUES('"
                +date+"','"
                +component+"','"
                +person+"',"
                +priority+",'"
                +description+"')";
        return sql;
    }
    public static String buildSelect(String table,String[] columns){
        String sql = "SELECT ";
        for (String col : columns){
            sql+=col+",";
        }
        sql = sql.substring(0,sql.length()-1);
        sql += " FROM "+table+";";
        return sql;
    }
    
}
