package pwr.student.BackEnd;

public class BackGate {
    public Respond respond;
    private final SQLExecutor sqlExecutor;
    private final SQLBuilder sqlBuilder;

    public BackGate() throws Exception {
        this.sqlExecutor = new SQLExecutor();
        this.sqlBuilder = new SQLBuilder();
        sqlBuilder.choseTable("decision");
    }
    public void recieveRequest(Request req) throws Exception {
        respond = new Respond();
        respond.setColumns(req.getColumns());
        String sql;
        switch (req.getOperation()){
            case SELECT -> {
                sql = sqlBuilder.buildSelect(req.getColumns());
                respond.setResultSet(sqlExecutor.select(sql));
            }
            case SEARCH_SELECT,DELETE,INSERT -> throw new Exception("Not implemented");
        }
    }
    public Respond getRespond(){
        return respond;
    }
}
