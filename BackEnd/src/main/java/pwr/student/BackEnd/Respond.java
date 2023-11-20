package pwr.student.BackEnd;

import java.sql.ResultSet;

public class Respond {
    private ResultSet resultSet;
    private String[] columns;

    public ResultSet getResult(){
        return resultSet;
    };
    public String[] getColumns(){
        return columns;
    };
    public void setColumns(String[] columns) { this.columns = columns; }
    public void setResultSet(ResultSet resultSet) { this.resultSet = resultSet; }
}
