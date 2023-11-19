package pwr.student.BackEnd;

import java.sql.ResultSet;

public class BackEndGate {
    public static String Main(String[] args){
        return args[0];
    }
    private static ResultSet handleInput(String[] args) throws Exception {
        String sql = SQLBuilder.buildSelect("decisions",args);
        SQLExecutor sqlExecutor = new SQLExecutor();
        return sqlExecutor.select(sql);
    }
}
