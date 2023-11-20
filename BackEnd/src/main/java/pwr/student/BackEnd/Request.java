package pwr.student.BackEnd;

public class Request {
    private Operation operation;
    private String[] columns;
    public void setOperation(Operation op){ operation = op; }
    public Operation getOperation() { return operation; }
    public void setColumns(String[] s) { columns = s; }
    public String[] getColumns() {return columns; }
}
