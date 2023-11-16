package pwr.student.BackEnd;
import java.sql.Date;
import java.util.Map;

public class SQLBuilder {
    public static String buildInsert(String table, Date date, String component, String person, Integer priority, String description) {
        return "INSERT INTO "
                +table
                +"(date,component,person,priority,description)"
                +" VALUES('"
                +MyDate.getRepresentation(date)+"','"
                +component+"','"
                +person+"',"
                +priority+",'"
                +description+"')";
    }
    public static String buildSelect(String table,String[] columns){
        StringBuilder sql = new StringBuilder("SELECT ");
        for (String col : columns)
            sql.append(col).append(",");
        sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        sql.append(" FROM ").append(table);
        return sql.toString();
    }

    public static String buildSearchSelect(String table,String[] columns, Map<String,String> conditions){
        StringBuilder sql = new StringBuilder(buildSelect(table,columns));
        sql.append(" WHERE ");

        for (String key : conditions.keySet())
            sql.append(key).append("=").append(conditions.get(key)).append(" AND ");
        sql = new StringBuilder(sql.substring(0, sql.length() - 5));
        return sql.toString();
    }

    public static String buildDelete(String table, String[] indexes){
        StringBuilder sql = new StringBuilder("DELETE from " + table + " WHERE id=");
        for (String id : indexes)
            sql.append(id).append(" OR id=");
        sql = new StringBuilder(sql.substring(0, sql.length() - 6));
        return sql.toString();
    }
    public static String buildDelete(String table, int start, int stop){
        return "DELETE from "+table+" WHERE id BETWEEN "+start+" AND "+stop;
    }
    
}
